/**
 * 
 * @author Antonella Di Lillo
 * 
 * Provided class file for PA3.
 *
 */
public class Process {
	private int id;
	private int priority;
	private String name;
	
	/**
	 * Constructs a new Process.
	 * @param i The ID of the new Process
	 * @param p The Priority of the new Process
	 * @param n The Name of the new Process
	 */
	public Process(int i, int p, String n){
		this.id = i;
		this.priority = p;
		this.name = n;
	}
	
	/**
	 * Gets the ID of the process
	 * @return the ID of the process
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Gets the Priority of the Process
	 * @return the Priority of the process
	 */
	public int getPriority(){
		return priority;
	}
	
	/**
	 * Gets the name of the Process
	 * @return the name of the process
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * A toString method to allow easy identification and printing for error checking.
	 */
	public String toString(){
		return "NAME: " + name + " PRIORITY: " + priority + " ID: " + id;
		
	}
}
