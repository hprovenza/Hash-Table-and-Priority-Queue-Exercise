/* 
* @author Hannah Provenza
* Data Structures
* 12 December 2015
* Programming Assignment #3
* This file implements a hash table.
*/

public class HashTable{
	public Record[] table;
	public int contains;
	

	/*
	* Constructs a new HashTable.
	* Runs in O(1) time.
	* @param an integer representing the desired size of the hash table.
	*/
	public HashTable(int s){
		this.table = new Record[s];
		this.contains = 0;
	}
	
	/*
	* To find the index of a Record with id p.
	* @param p the key of a Record
	* @return int the index of the Record with key p.
	*/
	public int search(int p){
		if (this.table[this.h1(p)] != null &&  this.table[this.h1(p)].getKey() == p){
			return this.h1(p);
		}	
		if (this.table[this.h2(p)] != null &&  this.table[this.h2(p)].getKey() == p){
			return this.h2(p);
		}
		return -1;
	}
	
	/*
	* This is how we add a Record to the hash table!
	* While it is possible that this method would trigger a rehash or require that every item in the table find a new nest, in which case addRecord would take O(n), this is extremely unlikely. Aside from these particular cases, the method should run in O(1) time. 
	* @param 
	*/
	public boolean addRecord(Process p){
		this.contains += 1;
		if (this.isOverloaded()){  // rehashing if necessary
			this.rehash();
		}
		if (this.search(p.getID()) != -1){  // a process with this ID already exists in the hash table.  Insertion fails.
			return false;
		}
		// main insertion segment
		int target = this.h1(p.getID());
		for(int i = 0; i < this.contains; i++){
			if (!(this.table[target] instanceof Record)){
				this.table[target] = new Record(p);
				return true;
			}
			Record temp = this.table[target];
			this.table[target] = new Record(p);
			p = temp.getValue();
			if (target == this.h1(p.getID())){
				target = this.h2(p.getID());
			} else {
				target = this.h1(p.getID());
			}
		}
		this.rehash();
		return this.addRecord(p);
		
	}
	
	/*
	* Extracts a record from the hash table.
	* @param int p the id of a process to be deleted.
	* @return the process with ID p.
	*/	
	public Process delete(int p){
		if (search(p) == -1){
		System.out.println(p);
			System.out.println("error: tried to delete nonexistent process");
			return new Process(0, 0, "errorprocess");
		}
		Process output = this.table[search(p)].getValue();
		this.table[search(p)] = null;
		return output;
		
	}
	
	/*
	* isOverloaded checks if the hash table has exceeded our desired load factor.
	* returns true if the table is overloaded and false otherwise.
	*/
	private boolean isOverloaded(){
		if(((double) this.contains) / this.table.length > .6){
			return true;
		}
		return false;
	}
	
	/*
	* Primary hash function.
	* Runs in O(1) time, does not touch any data elements in the table.
	* @return an integer representing the index where a process with id p should be inserted.
	*/
	private int h1(int p){
		 return p % table.length;
	}
	
	/*
	* Secondary hash function.
	* Runs in O(1) time, does not touch any data elements in the table.
	* @return an integer representing the index where a process with id p should be inserted.
	*/
	private int h2(int p){
		double A = (Math.sqrt(5) - 1) / 2;
		return (int) Math.abs(this.table.length * ((p * A) - (int)(p * A))) % table.length;
	}
	
	/*
	* Rehash creates a new hash table, inserts all the records from this table to that 
	* table using the hash functions with larger table size, then assigns that table to this one.
	* Runs in O(n) time, touching each item in the array once.
	*/ 
	private void rehash(){
		HashTable that = new HashTable(2 * this.table.length);
		for (int i = 0; i < this.table.length; i++){
			if (this.table[i] != null){
				that.addRecord(this.table[i].getValue());
			}
		}
		this.table = that.table;
		return;
	}
	
	/*
	* toString method to aid in testing.
	* Runs in O(n) time.
	*/
	public String toString(){
		String output = "";
		for (int i = 0; i < this.contains; i++){
			if (this.table[i] != null){
				output = output + this.table[i].getKey() + " " + this.table[i].getValue() + "\n";
			}
		}
		return output;
	}
}