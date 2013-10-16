package datastore;

import java.util.LinkedList;
import java.util.Queue;

public class Register {
	public enum Type{
		SPILLED, S, T
	}
	int index;
	Type type;
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
	
	public static Queue<Register> generatePool(){
		Queue<Register> pool = new LinkedList<Register>();
		for(int i=0; i<=7; i++){
			pool.add(new Register(i, Type.S));
		}
		for(int i=0; i<=9; i++){
			pool.add(new Register(i, Type.T));
		}
		return pool;
	}
	
}
