package datastore;

import java.util.Queue;
import java.util.Stack;

public class Register {
	public enum Type{
		SPILLED, S, T, A, V
	}
	int index;
	public Type type;
	public Register(int index, Type type){
		this.index = index;
		this.type = type;
	}
	
	public Register getNext(){
		if(type == Type.S){
			if(index == 7){
				return new Register(0, Type.T);
			}else{
				return new Register(this.index+1, Type.T);
			}
		}else if(type == Type.T){
			if(index == 9){
				return new Register(0, Type.SPILLED);
			}else{
				return new Register(this.index+1, Type.T);
			}
		}else{
			return new Register(this.index+1, Type.SPILLED);
		}
	}
	
	public static Stack<Register> generatePool(){
		Stack<Register> pool = new Stack<Register>();
		for(int i=9; i>=0; i--){
			pool.add(new Register(i, Type.T));
		}
		for(int i=7; i>=0; i--){
			pool.add(new Register(i, Type.S));
		}
		return pool;
	}
	
	
	public void print(){
		System.out.print(this.toString());
	}
	
	
	public static void move(Register r1, Register r2){
		if(r1.type == Type.SPILLED){
			System.out.print("\nASTORE "); 					
		}else if(r2.type == Type.SPILLED){
			System.out.print("\nALOAD ");
		}else{
			System.out.print("\nMOVE ");
		}
		r1.print();	
		System.out.print(' ');
		r2.print();
		
	}
	
	
	public String toString(){
		if(type == Type.S){
			return ("s"+this.index);
		}else if(type==Type.T){
			return ("t"+this.index);
		}else if(type==Type.A){
			return ("a"+this.index);
		}else if(type == Type.V){
			return ("v"+this.index);
		}else{
			return ("SPILLEDARG "+this.index);
		}
	}
	
	
}
