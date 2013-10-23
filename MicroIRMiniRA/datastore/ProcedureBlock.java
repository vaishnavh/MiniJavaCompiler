package datastore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import datastore.Register.Type;

public class ProcedureBlock {
	//Refers to a procedure block
	Vector<Block> blocks = new Vector<Block>();
	Vector<Interval> intervals = new Vector<Interval>();
	HashMap<String, Block> labels = new HashMap<String, Block>();
	HashMap<Block, String> jumps = new HashMap<Block, String>();
	HashMap<Integer, Interval> tempMap = new HashMap<Integer, Interval>();
	int spillIndex;
	int maxSpillIndex;
	public int traverse;
	String label;
	Block currentStatement;
	public String procedureName;
	public int argNo, maxArg;
	public int incSpill(){
		spillIndex++;
		if(spillIndex>maxSpillIndex){
			maxSpillIndex = spillIndex;
		}
		return spillIndex-1;
	}
	private boolean isParseable(String tempName){
		try{
			Integer.parseInt(tempName);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	public Block createNewStatement(){
		Block newStatement = new Block(blocks.size());
		blocks.add(newStatement);

		if(this.label!=null){
			//System.out.println("Beginning label "+label);//COMMENTS
			labels.put(label, newStatement);
			newStatement.label = label;
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
		//System.out.println("Putting "+label+" for "+currentStatement.index+" in "+this.procedureName);
		jumps.put(this.currentStatement, label);
	}

	public void snapFlow(){
		this.currentStatement = null; //When there is a break in the control flow
	}


	public void print(){
		System.out.println(this.procedureName+" >> "+this.argNo);
		for(Block b : this.blocks){
			b.print();

		}
		System.out.println("\n*************\nINTERVALS for "+this.procedureName);
		for(Interval i: this.intervals){
			i.print();
		}
		System.out.println("\n--------------------------------------");
	}

	public void dissolveJumps(){
		for(Block b: jumps.keySet()){
			//System.out.println("Finding label "+jumps.get(b));
			//System.out.println(" for "+b.index);
			//System.out.println(" found "+labels.get(jumps.get(b)).index);
			if(labels.containsKey(jumps.get(b)))
				labels.get(jumps.get(b)).precede(b);
		}
	}

	public void computeGraph(){

		Queue<Block> workSet = new LinkedList<Block>();
		int size = blocks.size();
		//workSet.add(blocks.elementAt(size-1));
		workSet.addAll(blocks);
		while(workSet.size()>0){
			Block work = workSet.poll();
			//System.out.println("Adding outs : "+work.out.size()+" ");
			for(Block successor : work.successors){
				//System.out.println("Successor "+successor.index+" of size "+successor.in.size());
				work.out.addAll(successor.in);				
			}
			//System.out.println(work.out.size());
			int originalSize = work.in.size();
			work.in.addAll(work.out);
			work.in.addAll(work.use);
			work.in.removeAll(work.definitions);
			if(work.in.size()!=originalSize){
				workSet.addAll(work.predecessors);
			}
			// System.out.println("Workset size "+workSet.size());
		}


	}


	public void computeIntervals(){
		for(Block block : blocks){		
			Set<Integer> outSet = new HashSet<Integer>();
			outSet.addAll(block.out);
			outSet.addAll(block.definitions);
			for(Integer outgoingTemp : outSet){
				//System.out.println("Outgoing : "+outgoingTemp);
				if(!tempMap.containsKey(outgoingTemp)){
					//First out
					Interval newInterval = new Interval(block, outgoingTemp);
					tempMap.put(outgoingTemp, newInterval);
					intervals.add(newInterval);
					newInterval.endLine = block;
				}else{ 
					//Already present
					Interval oldInterval = tempMap.get(outgoingTemp);
					oldInterval.endLine = block;
				}				
			}

			for(Integer incomingTemp : block.in){
				Interval oldInterval = tempMap.get(incomingTemp);
				oldInterval.endLine = block;
			}			

		}
	}

	public void registerAllocation(){
		Stack<Register> pool = Register.generatePool();
		Vector<Interval> active = new Vector<Interval>();
		for(Interval i: this.intervals){
			if(i.temp>3 && i.temp<this.argNo){
				//Already in stack
				i.register = new Register(i.temp-4, Type.SPILLED);
			}else{
				expireOldIntervals(i, pool, active);
				if(pool.size() == 0){
					spillAtInterval(i, active);
				}else{
					i.register = pool.pop(); //Remove from register pool
					int j = active.size()-1;
					for(; j>=0; j--){
						if(active.elementAt(j).endLine.before(i.endLine)){
							break;
						}
					}
					active.add(j+1, i); //Add it here

				}
			}
		}
	}

	public void expireOldIntervals(Interval i, Stack<Register> pool, Vector<Interval> active){
		while(active.size()!=0){
			if(i.startLine.before(active.elementAt(0).endLine)){
				return;
			}
			Interval expired = active.remove(0); //Whenever I remove from here
			pool.push(expired.register); //I add it here
		}
	}

	public void spillAtInterval(Interval i, Vector<Interval> active){
		Interval spill = active.elementAt(active.size()-1);
		if(!spill.endLine.before(i.endLine)){
			i.register = spill.register;
			active.remove(active.size()-1); //Remove spill from active
			spill.register = new Register(incSpill(), Register.Type.SPILLED);
			int j = active.size()-1;
			for(; j>=0; j--){
				if(active.elementAt(j).endLine.before(i.endLine)){					
					break;
				}
			}
			active.add(j+1, i); //Add to active
		}else{
			i.register = new Register(incSpill(), Register.Type.SPILLED);
		}
	}

	public void initializeArguments(String number){
		this.argNo = Integer.parseInt(number);
		createNewStatement();		
		this.spillIndex = argNo-3;
		spillIndex = (spillIndex>0) ? spillIndex:0;
		for(int i=0; i<argNo; i++){
			this.currentStatement.definitions.add(i);
		}

	}


	public void clear(){
		this.currentStatement = null;
		this.label = null;
	}
	public Block getNextStatement(){
		if(this.currentStatement==null){
			traverse = 0;

		}else{
			traverse++;
		}
		currentStatement = this.blocks.elementAt(traverse);
		return this.currentStatement;
	}

	public void makeSpecs(){
		for(int temp : this.tempMap.keySet()){
			Register r = this.tempMap.get(temp).register;
			if(r.type == Type.S && !calleeSave.containsKey(r)){
				Register save = new Register(incSpill(), Type.SPILLED);
				this.calleeSave.put(r, save);				
			}
		}
	}
	public void printSpecs(){		

		System.out.println("\n"+this.procedureName+" ["+this.argNo+"]["+this.maxSpillIndex+"]["+this.maxArg+"]");

	}

	public void setMaxArg(int arg){
		if(this.maxArg<arg){
			this.maxArg = arg;
		}
	}

	public String getLabel(){
		return this.blocks.elementAt(this.traverse).label;
	}

	public void movArguments(){
		traverse++;
		int i = 0;
		for(; i<=3 && i<this.argNo; i++){
			if(tempMap.containsKey(i)){
				Register to = this.tempMap.get(i).register;
				Register from = new Register(i, Type.A);
				Register.move(to, from);
			}
		}
	}



	public void saveCallerRegisters(){
		HashMap<Register, Register> callerSave = this.currentStatement.callerSave;
		for(Register r : callerSave.keySet()){
			Register.move(callerSave.get(r), r);
		}
	}


	public void enumerateCallerRegisters(){
		HashMap<Register, Register> callerSave = new HashMap<Register, Register>();
		int initialSpill = spillIndex;
		//System.out.println("Saving Caller . . . ");
		for(Integer temp : this.currentStatement.out){
			//if(!this.currentStatement.definitions.contains(temp)){ //not present in definitons
			Interval tempInterval = this.tempMap.get(temp);
			if(tempInterval.register.type == Type.T){
				Register save  = new Register(incSpill(), Type.SPILLED);
				callerSave.put(tempInterval.register, save);
			}
			//}			
		}
		spillIndex = initialSpill;
		currentStatement.callerSave = callerSave;
	}



	public void loadCallerRegisters(){
		HashMap<Register, Register> callerSave = this.currentStatement.callerSave;
		for(Register r : callerSave.keySet()){
			Register.move(r, callerSave.get(r));
		}
	}

	public HashMap<Register, Register> calleeSave = new HashMap<Register, Register>();

	public void saveCalleeRegisters(){
		for(Register r : calleeSave.keySet()){			
			Register.move(calleeSave.get(r), r);
		}
	}


	public void loadCalleeRegisters(){
		for(Register r : calleeSave.keySet()){
			Register.move(r, calleeSave.get(r));
			//spillIndex--;
		}
		calleeSave.clear();
	}


	public void returnValue(String temp){
		Register v = new Register(0, Type.V);
		Register.move(v, getRegister(temp));
	}


	public  Register getRegister(String temp){
		return this.tempMap.get(Integer.parseInt(temp)).register;
	}

	public Register obtainRegister(String temp, boolean second, boolean toLoad){ //Specifically a register. Not spilled.
		int tempInt = Integer.parseInt(temp);
		if(!tempMap.containsKey(tempInt)){
			return null;
		}
		Register r = this.tempMap.get(tempInt).register;
		if(toLoad){
			Register v;
			if(r.type == Type.SPILLED){
				if(second){
					v = new Register(1, Type.V);
				}else{
					v = new Register(0, Type.V);
				}
				Register.move(v, r);
			}else{
				v = r;
			}
			return v;
		}else{
			return r;
		}
	}
}
