// Hannah Provenza
// Data Structures
// 12 December 2015
// Programming Assignment #3
// This file contains the main method for PA3.

import java.util.*;
import java.io.*;

public class PA3{

	/*
	* Main method!  Tada!
	* Runs in O(n) time.
	*/
	public static void main (String[] args){
			File f = getFile();
			processFile(f);
	}
	
	/*
	* Gets the file from the user!
	* O(1) - self-evident
	* @return File f that the user asked to open.
	*/
	public static File getFile(){
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		String filename = input.next();
		File f = new File(filename);
		return f;
	}
	
	/*
	* Processes all the lines of text in the file!
	* Runs in O(n) time where n is the number of lines.
	* @param File f the file containing the lines.
	*/
	public static void processFile(File f){
		try {
			Scanner file = new Scanner(f);
			HashTable Hash = new HashTable(2029);
			minPriorityQueue Q = new minPriorityQueue(62887);
		
			while (file.hasNextLine()){
				int id = file.nextInt();
				int priority = file.nextInt();
				String word = file.next();
				Process p = new Process(id, priority, word);
								
				if (Hash.addRecord(p)){
					Q.insert(p);
				}
			}

			while (Q.size > 21){
				Q.extractMin();
			}
			String output = "";
			
			while (Q.size > 0){
				output = Hash.delete(Q.extractMin()).getName() + " " + output;
			}
			
			System.out.println(output);
		} catch(FileNotFoundException ex){
			System.out.println("error: file not found");
		}
	}
}