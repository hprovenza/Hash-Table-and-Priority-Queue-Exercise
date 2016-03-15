// Hannah Provenza
// Data Structures
// 12 December 2015
// Programming Assignment #3
// This file implements a Record object for use in the hash table class.  The record has fields for an integer key and a Process value.

public class Record{
	private int key;
	private Process value;
	
	public Record(Process p){
		this.key = p.getID();
		this.value = p;
	}
	
	/* @return the key of the Record.
	* Runs in O(1) time.
	*/
	public int getKey(){
		return this.key;
	}
	
	/* @return the value of the Record.
	* Runs in O(1) time.
	*/
	public Process getValue(){
		return this.value;
	}
}