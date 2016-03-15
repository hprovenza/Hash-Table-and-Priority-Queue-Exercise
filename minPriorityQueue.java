// Hannah Provenza
// Data Structures
// 12 December 2015
// Programming Assignment #3
// This file implements a minimum priority queue.

public class minPriorityQueue{
	public int[][] array;
	//A[i][0] IS AN ID
	// A[i][1] IS A PRIORITY	
	public int size;
	
	private int INF = 2147483647;
	
	/*
	* Constructs a new minPriorityQueue object.
	* Runs in O(1) time
	* @param int s the desired size of the priority queue.
	*/
	public minPriorityQueue(int s){
		this.array = new int[s][2];
		this.size = 0;
	}
	
	/*
	* Finds the index of the left child of index a
	* Runs in O(1) time
	* @return the index of the left child
	*/
	private int left(int a){
		return 2*a + 1;
	}
	
	/*
	* Finds the index of the right child of index a
	* Runs in O(1) time
	* @return the index of the right child
	*/
	private int right(int a){
		return 2*a + 2;
	}

	/*
	* Finds the index of the parent of index a
	* Runs in O(1) time
	* @return the index of the parent
	*/	
	private int parent(int index){
		return (index - 1) / 2;
	}
	
	/*
	* Inserts a Process's ID and priority to the priority queue
	* Calls decreaseKey, therefore runs in O(logn) time
	* @param Process that we want to insert
	*/
	public void insert(Process p){
		this.size += 1;
		this.array[this.size - 1][1] = INF;
		this.array[this.size - 1][0] = p.getID();
		this.decreaseKey(this.size-1, p.getPriority());
	}
	
	/*
	* deletes a Process's ID and priority from the priority queue
	* Runs in log(n) time because it calls heapifyDown.
	* @return the ID with minimum priority
	*/	
	public int extractMin(){
		if(this.size < 1){
			System.out.println("error: heap underflow");
			return -1;
		}
		int max = this.array[0][0];
		this.array[0] = this.array[this.size - 1];
		this.size -= 1;
		this.heapifyDown(0);
		return max;
	}
	
	/*
	* Changes the key of a node then moves it to its new place in the tree.
	* Calls heapifyUp, therefore runs in O(logn) time.
	* @param int i the index of the node
	* @param int key the new priority of the node at i.
	*/
	private void decreaseKey(int i, int key){
		if (key > this.array[i][1]){
			System.out.println("error: new key is larger than current key");
			return;
		}
		this.array[i][1] = key;
		heapifyUp(i);
	}
	
	/*
	* heapifyUp takes the node at index i and moves it up to a position in the tree where it satisfies the heap property.
	* In the worst case, traverses the height of the tree of the heap; therefore runs in O(logn) time
	* @param int i the index of the node to be adjusted.
	*/
	private void heapifyUp(int i){
		while(i > 0 && this.array[i][1] < this.array[this.parent(i)][1]){
			int[] temp = this.array[i];
			this.array[i] = this.array[this.parent(i)];
			this.array[this.parent(i)] = temp;
			i = this.parent(i);
		}
	}
	
	/* 
	* heapifyDown adjusts the heap to maintain the heap property
	* In the worst case this algorithm traverses the height of the heap tree, therefore runs in O(logn) time.
	* @param int i the index of the node that needs heapifying.
	*/
	private void heapifyDown(int i){
		int l = left(i);
		int r = right(i);
		int smallest;
		if (l <= this.size && this.array[l][1] < this.array[i][1]){
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= this.size && this.array[r][1] < this.array[smallest][1]){
			smallest = r;
		}
		if (smallest != i){
			int[] temp = this.array[i];
			this.array[i] = this.array[smallest];
			this.array[smallest] = temp;
			this.heapifyDown(smallest);
		}
	}
}