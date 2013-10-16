package datastore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

public class ProcedureBlock {
	//Refers to a procedure block
	Vector<Block> blocks = new Vector<Block>();
	Vector<Interval> intervals = new Vector<Interval>();
	HashMap<String, Block> labels = new HashMap<String, Block>();
	HashMap<Block, String> jumps = new HashMap<Block, String>();
	int spillIndex;
	String label;
	Block currentStatement;
	private boolean isParseable(String tempName){
		try{
			Integer.parseInt(tempName);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	public Block createNewStatement(){
		Block newStatement = new Block(blocks.size()+1);
		blocks.add(newStatement);
		
		if(this.label!=null){
			System.out.println("Beginning label "+label);
			labels.put(label, newStatement);
		}
		if(currentStatement != null){
			newStatement.precede(currentStatement);
		}
		currentStatement = newStatement;		
		return newStatement;
	}
	public void beginLabel(String label){
		this.label = label;
	}
	
	public void unsetLabel(){
		this.label = null;
	}
	
	public void define(String tempName){
		if(tempName!=null){
			if(this.isParseable(tempName)){
				currentStatement.definitions.add(Integer.parseInt(tempName));
			}			
		}
	}
	
	public void use(String tempName){
		if(tempName!=null){
			if(this.isParseable(tempName)){
				currentStatement.use.add(Integer.parseInt(tempName));
			}
		}
	}
	
	public void jumpTo(String label){
		jumps.put(this.currentStatement, label);
	}
	
	public void snapFlow(){
		this.currentStatement = null; //When there is a break in the control flow
	}
	
	
	public void print(){
		for(Block b : this.blocks){
			b.print();
		}
	}
	
	public void dissolveJumps(){
		for(Block b: jumps.keySet()){
			System.out.println("Finding label"+jumps.get(b));
			labels.get(jumps.get(b)).precede(b);
		}
	}
	
	public void computeGraph(){
		for(Block block : blocks){
			block.in.addAll(block.use);
		}
		Queue<Block> workSet = new LinkedList<Block>();
		int size = blocks.size();
		workSet.add(blocks.elementAt(size-1));
		while(workSet.size()>0){
			Block work = workSet.poll();
			for(Block successor : work.successors){
				work.out.addAll(successor.in);				
			}
		    int originalSize = work.in.size();
		    work.in.addAll(work.out);
		    work.in.removeAll(work.definitions);
		    if(work.in.size()!=originalSize){
		    	workSet.addAll(work.predecessors);
		    }
		}
			
		
	}
	
	
	public void computeIntervals(){
		HashMap<Integer, Interval> live = new HashMap<Integer, Interval>();
		Block prevBlock = null; 
		for(Block block : blocks){
			for(Integer outgoingTemp : block.out){
				if(!block.in.contains(outgoingTemp)){
					//generated
					Interval newInterval = new Interval(block, outgoingTemp);
				    live.put(outgoingTemp, newInterval);
				    intervals.add(newInterval);
				}
				
			}
			for(Integer liveTemp : live.keySet()){
				if(!block.in.contains(liveTemp)){
					live.get(liveTemp).endLine = prevBlock;
				}
			}
			prevBlock = block;
		}
	}
	
	public void registerAllocation(){
		Queue<Register> pool = Register.generatePool();
		Vector<Interval> active = new Vector<Interval>();
		for(Interval i: this.intervals){
			expireOldIntervals(i, pool, active);
			if(pool.size() == 0){
				spillAtInterval(i, active);
			}else{
				i.register = pool.poll();
				for(int j = active.size(); j>=0; j++){
					if(active.elementAt(j).endLine.before(i.endLine)){
						active.add(j+1, i);
					}
				}
			}
		}
	}
	
	public void expireOldIntervals(Interval i, Queue<Register> pool, Vector<Interval> active){
		while(active.size()!=0){
			if(i.startLine.before(active.elementAt(0).endLine)){
				return;
			}
			Interval expired = active.remove(0);
			pool.add(expired.register);
		}
	}
	
	public void spillAtInterval(Interval i, Vector<Interval> active){
		Interval spill = active.elementAt(active.size()-1);
		if(!spill.endLine.before(i.endLine)){
			i.register = spill.register;
			active.remove(active.size()-1);
			spill.register = new Register(spillIndex++, Register.Type.SPILLED);
			for(int j = active.size(); j>=0; j++){
				if(active.elementAt(j).endLine.before(i.endLine)){
					active.add(j+1, i);
				}
			}
		}else{
			i.register = new Register(spillIndex++, Register.Type.SPILLED);
		}
	}
	
	
}
