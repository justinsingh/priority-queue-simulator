import java.util.*;

/*
 * HEAP CLASS
 * ----------
 * 
 * int aryLength: an integer representing the size of the ArrayList used in the heap
 * int heapSize: an integer representing the amount of elements in the ArrayList that are used 
 * 
 * This class can be used to sort a given ArrayList. It can also be used to turn an ArrayList into a max heap, and treat it with priority queue functions such as insert, extract, and modify.
 */
public class Heap {
	private int aryLength; // length of ArrayList
	private int heapSize; // number of elements in ArrayList that are considered part of the heap at a given time
	private int extractCount = 0; // counter for how many times extractions have been made
							     // used for figuring out the next unique id digit of a new process
	
	/*
	 * HEAP CONSTRUCTOR
	 * 
	 * Takes a List of Process objects and sets aryLength and heapSize according to aryParam size.
	 * 
	 * @param List<Process> aryParam- used to figure out the aryLength and heapSize
	 */
	public Heap(List<Process> aryParam) {
		aryLength = aryParam.size(); // number of elements in aryParam starting from 1
		heapSize = aryLength - 1; // number of elements in aryParam starting from 0
	}
	
	/*
	 * specifies the parent of a given index. the parent is understood to represent the node which points at it from a max heap point of view
	 * 
	 * @param int index- index in array you wnat to find parent of
	 */
	public int parent(int index) {
		return (index / 2);
	}
	
	/*
	 * specifies the left child of a given index. the left child is understood to represent the node which points at it from a max heap point of view.
	 */
	public int left(int index) {
		return (2 * index) + 1;
	}
	
	/*
	 * specifies the right child of a given index. the right child is understood to represent the node which points at it from a max heap point of view.
	 */
	public int right(int index) {
		return (2 * index) + 2;
	}
	
	/*
	 * maintains an ArrayList of being a max-heap. assumes left and right children are max heaps, but that children of these may violate max-heap property. 
	 * 
	 * @param List<Process>- List which is mainted of max-heap property
	 * @param int index- index at which to begin max-heapify procedure
	 */
	public void maxHeapify(List<Process> aryParam, int index)
	{ 
		int left = left(index);
		int right = right(index);
		int largest; 
		
		// checks if left child is larger than current index. if so, int largest is set to left.
		if ((left <= heapSize) && (aryParam.get(left).getKey() > aryParam.get(index).getKey()))
			largest = left;
		else largest = index;
		
		// checks if right child is larger than current index. if so, int largest is set to right.
		if ((right <= heapSize) && (aryParam.get(right).getKey() > aryParam.get(largest).getKey())) 
			largest = right;
		
		// if int largest is not the index this function was called on, then processes are moved so that the larger value is moved up and the smaller value moved down.
		if (largest != index) {
			Process temp = new Process("unassigned");
			temp.assignProcess(aryParam.get(index));
			aryParam.get(index).assignProcess(aryParam.get(largest));
			aryParam.get(largest).assignProcess(temp);
			
			maxHeapify(aryParam, largest);
		}
	}
	
	
	/*
	 * goes through the leaves of the tree and performs max-heapify to put them in correct locations
	 * 
	 * @param List<Process> aryParam- List which is built into a max-heap
	 */
	public void buildMaxHeap(List<Process> aryParam) {
		aryLength = aryParam.size();
		heapSize = aryLength - 1;
		
		// runs max-heapify on leaves of tree
		for (int i = ((aryLength - 1) / 2); i >= 0; i--)
			maxHeapify(aryParam, i);
	}
	
	/*
	 * sorts heap least to greatest
	 * 
	 * @param List<Process>aryParam- List of Process objects which is sorted least to greatest
	 */
	public void heapSort(List<Process> aryParam) {
		buildMaxHeap(aryParam); // builds List into max heap
		
		// swaps variables from bottom to top and performes maxHeapify from the top, so that it sorts.
		for (int i = (aryLength - 1); i >= 1; i--) {
			Process temp = new Process("unassigned");
			temp.assignProcess(aryParam.get(0));
			aryParam.get(0).assignProcess(aryParam.get(i));
			aryParam.get(i).assignProcess(temp);
			
			heapSize = heapSize - 1;
			maxHeapify(aryParam, 0);
		}
	}
	
	/*
	 * returns the 0 index of the List, which will be the max value when the List is made into a max-heap
	 * 
	 * @param List<Process> aryParam- the list of which to get the 0 index
	 */
	public Process heapMaximum(List<Process> aryParam) {
		return aryParam.get(0);
	}
	
	/*
	 * removes the max value of the heap from the given List, and returns it.
	 * 
	 * @param List<Process> aryParam- given list to remove the maximum value from.
	 */
	public Process heapExtractMax(List<Process> aryParam) throws Exception {
		if (heapSize < 0)
			throw new Exception("Heap underflow"); // error checking to see if heap is underflowing
		
		Process max = new Process("unassigned");
		max.assignProcess(heapMaximum(aryParam)); // assigns max value of heap to max Process
		aryParam.get(0).assignProcess(aryParam.get(heapSize));
		aryParam.remove(0); // remove max value from heap
		heapSize = heapSize - 1; // decrement heap size to show 1 less element is in the heap
		maxHeapify(aryParam, 0); // rebuild heap
		extractCount++; // measurement variable to help calculate unique id name for when making a process, so as to avoid duplicates.
		
		return max;
	}
	
	/*
	 * increases the key attribute of a specified Process.
	 * 
	 * @param List<Process> aryParam- given array to choose index from which gets increased
	 * @param int index- given index of which to increase value of to specified key
	 * @param int key- value which given index's key value gets updated to
	 */
	public void heapIncreaseKey(List<Process> aryParam, int index, int key) throws Exception {
		if (key < aryParam.get(index).getKey()) 
			throw new Exception("New key is smaller than current key"); // error checking to see if new key if smaller than old key
		
		aryParam.get(index).setKey(key); // sets Process's key to update key
		
		// resorts the heap to check if new key of modified index is higher than parent's
		while (index > 0 && aryParam.get(parent(index)).getKey() < aryParam.get(index).getKey()) {
			Process temp = new Process("unassigned");
			temp.assignProcess(aryParam.get(index));
			aryParam.get(index).assignProcess(aryParam.get(parent(index)));
			aryParam.get(parent(index)).assignProcess(temp);
			
			index = parent(index);
		}
	}
	
	/*
	 * inserts a new Process into the heap
	 * 
	 * @param List<Process> aryParam- List that has a new Process object added to it.
	 */
	public void maxHeapInsert(List<Process> aryParam) throws Exception {
		heapSize = heapSize + 1; // increment heapSize now that it has one more node in it.
		int idNum = aryParam.size() + extractCount; // calculate the number in the id attribute of a process by adding extractCount to size, so that it knows the number that corresponds with the index of the Process.
		String idName = "p" + idNum; // id of new process
		Process temp = new Process(idName); // create new Process object that has a randomized key and specified idName
		
		aryParam.add(temp); // add this newly created Process to the ArrayList
	}
	
	/*
	 * prints the key and id of each Process in the heap
	 * 
	 * @param List<Process> aryParam- List that has information on each Process printed
	 */
	public void printHeap(List<Process> aryParam) {
		for (int i = 0; i <= heapSize; i++) {
			aryParam.get(i).printKey();
			System.out.print(" ");
			aryParam.get(i).printId();
			System.out.println();
		}
		System.out.println();
	}
	
	/*
	 * prints the key and id of each Process in the array
	 * 
	 * @param List<Process> aryParam- List that has information on each Process printed
	 */
	public void printAry(List<Process> aryParam) {
		heapSize = aryLength - 1;
		for (int i = 0; i <= heapSize; i++) {
			aryParam.get(i).printKey();
			System.out.print(" ");
			aryParam.get(i).printId();
			System.out.println();
		}
		System.out.println();
	}
}
