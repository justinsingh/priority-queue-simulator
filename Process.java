import java.util.*;
/*
 * PROCESS CLASS
 * -------------
 * 
 * Integer key: an integer that's randomly generated upon construction of a process object.
 * String id: a string id which follows the format "pX", where X is the index at which a Process object is stored in a list
 * 
 * This class is used as an object to store as a node in an ArrayList. It represents a computer process, where a higher integer implies that it will be closer to the root of a heap. 
 */
public class Process {
	private int key; // key that represents priority in a heap
	private String id; // id that represents a distinct name for the process
	
	/*
	 * PROCESS CONSTRUCTOR
	 * sets a randomly generated integer for the key 
	 * and an id which is passed to it during construction 
	 * 
	 * @param idParam- string to be used for the id
	 */
	public Process(String idParam) {
		setKey(genKey());
		setId(idParam); 
	}
	
	// generates a random integer used in assigning the Process's key 
	public int genKey() {
		int randomKey;
		int min = 1;
		int max = 999;
		
		Random rand = new Random();
		
		randomKey = min + rand.nextInt(max);
		
		return randomKey;
	}
	
	/*
	 * sets values of another process to the values of this process
	 * 
	 * @param processParam- a Process object used to transfer values from
	 */
	public void assignProcess(Process processParam) {
		setKey(processParam.getKey());
		setId(processParam.getId());
	}
	
	// getter for key. returns integer key
	public int getKey() {
		return key;
	}
	
	
	// getter for id. returns string id
	public String getId() {
		return id;
	}
	
	// setter for key. sets integer key.
	public void setKey(int keyParam) {
		this.key = keyParam;
	}
	
	// setter for id. sets string id.
	public void setId(String idParam) {
		this.id = idParam;
	}
	
	// prints integer key
	public void printKey() {
		System.out.print(key);
	}
	
	// prints integer id.
	public void printId() {
		System.out.print(id);
	}
}
