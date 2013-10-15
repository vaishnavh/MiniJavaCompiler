package datastore;

import java.util.HashMap;
import java.util.Vector;

public class ProcedureBlock {
	//Refers to a procedure block
	Vector<Block> blocks = new Vector<Block>();
	HashMap<String, Block> labels = new HashMap<String, Block>();
	HashMap<Block, String> jumps = new HashMap<Block, String>();
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
}
