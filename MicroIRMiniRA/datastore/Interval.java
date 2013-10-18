package datastore;

import java.util.HashMap;

public class Interval {
	public Block startLine, endLine;
	public int temp;
	public Register register;
	public Interval(Block startLine, int temp){
		this.startLine = startLine;
		this.temp = temp;
	}
	public void print(){
		System.out.print("\nTEMP ");
		System.out.print(temp);
		System.out.print(" "+startLine.index);
		System.out.print(" ==> ");
		System.out.print(endLine.index);
		System.out.print(" :: ");
		register.print();
	}
	
}
