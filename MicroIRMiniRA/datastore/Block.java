package datastore;

import java.util.HashSet;
import java.util.Set;

public class Block {
	//A block refers to a line
	int index;
	Block(int index){
		this.index = index;
	}
	Set<Integer> definitions = new HashSet<Integer>();
	Set<Integer> in = new HashSet<Integer>();
	Set<Integer> out = new HashSet<Integer>();
	Set<Integer> use = new HashSet<Integer>();
	Set<Block> predecessors = new HashSet<Block>();
	Set<Block> successors = new HashSet<Block>();
	
	public void precede(Block predecessor){
		this.predecessors.add(predecessor);
		predecessor.succeed(this);
	}
	public void succeed(Block successor){
		this.successors.add(successor);
		
	}
	
	public void print(){
		System.out.println("*****************\nLINE NUMBER : "+index);
		System.out.println("DEFINED : ");
		for(Integer i: this.definitions){
			System.out.println(i);
		}
		System.out.println("USED : ");
		for(Integer i: this.use){
			System.out.println(i);
		}
		System.out.println("PREDECESSORS : ");
		for(Block i: this.predecessors){
			System.out.println(i.index);
		}
		System.out.println("DEFINED : ");
		for(Block i: this.successors){
			System.out.println(i.index);
		}
	}
}
