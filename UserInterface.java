import java.util.*;

/* 
 * USERINTERFACE CLASS
 * 
 * Provides a user interface in the command line. Users can choose to view the list as a heap, view it as sorted least to greatest, insert a process, remove the highest priority process, or to update a process's priority key.
 * 
 * List<Process> pList- an ArrayList that stores Processes
 * Heap heap- a heap object that performs actions on pList
 * 
 */
public class UserInterface {
	static List<Process> pList = new ArrayList<Process>(); // ArrayList to store Process objects
	static Heap heap = new Heap(pList); // Heap that represents pList 
	
	public static void main(String[] args) throws Exception {	
		addDefaultProcesses(pList); // assign 10 Process objects to the heap
		
		printOpening(); // print opening text to the program
		printMenuChoices(); // print menu choices that users can choose from
		
	}
	
	/*
	 * assigns 10 Process objects to the specified List
	 * 
	 * @param List<Process> pList- a list that gets 10 Processes added to it 
	 */
	public static void addDefaultProcesses(List<Process> pList) {
		pList.add(new Process("p0"));
		pList.add(new Process("p1"));
		pList.add(new Process("p2"));
		pList.add(new Process("p3"));
		pList.add(new Process("p4"));
		pList.add(new Process("p5"));
		pList.add(new Process("p6"));
		pList.add(new Process("p7"));
		pList.add(new Process("p8"));
		pList.add(new Process("p9"));
	}
	
	// print opening text
	public static void printOpening() {
		System.out.println("=========================================");
		System.out.println("~~~~~SCHEDULING SOFTWARE APPLICATION~~~~~");
		System.out.println("~~~~~~~~~~MADE BY JUSTIN SINGH~~~~~~~~~~~");
		System.out.println("=========================================");
		System.out.println();
	}
	
	/*
	 * Print and allow functionality for menu choices that users can choose from to manipulate the ArrayList
	 * users can view the ArrayList as a max-heap, insert a process to the List, print it least to great, remove the highest priority index, update a Process's index key, or exit the program.
	 */
	public static void printMenuChoices() throws Exception {
		Scanner reader = new Scanner(System.in);
		boolean isRunning = true; // boolean that is true to keep prompting user options, until user exits and isRunning becomes false
		int usrInput = 0;
		
		// prints the ArrayList's default 10 Processes before any modification has been done to them
		System.out.println("==========DEFAULT PROCESS LIST==========");
		for (int i = 0; i < pList.size(); i++) {
			System.out.println(pList.get(i).getKey() + " " + pList.get(i).getId());
		}
		System.out.println();

		// loops user functionalities
		while (isRunning == true) {
			System.out.println("-----ENTER ONE OF THE BELOW COMMANDS-----");
			System.out.println();
			System.out.println("1.) View list as max heap");
			System.out.println("2.) Insert a process");
			System.out.println("3.) Print sorted list");
			System.out.println("4.) Remove top priority process");
			System.out.println("5.) Update a priority index key");
			System.out.println("6.) Exit program");
			System.out.println();
			System.out.print("ENTER CHOICE (1-6): ");

			usrInput = reader.nextInt(); // reads user option

			if (usrInput == 1) {
				printMaxHeap(); // prints the ArrayList as max-heap
			}
			if (usrInput == 2) {
				insertProcess(); // inserts process into ArrayList
			}
			else if (usrInput == 3) {
				printSortedList(); // prints list sorted least to greatest
			}
			else if (usrInput == 4) {
				removeFirst(); // removes top priority process
			}
			else if (usrInput == 5) { // allows user to choose a new key for a chosen variable
				int inputId = 0;
				int inputKey = 999;

				System.out.println();
				System.out.println("Enter ID # of process (example: if updating p9, enter the digit 9): ");
				inputId = reader.nextInt();
				Integer.toString(inputId);
				String idCheck = "p" + inputId;
				
				System.out.println("Enter new priority index (must be higher than its current index): ");
				inputKey = reader.nextInt();
				
				for (int i = 0; i < pList.size(); i++) {
					if (pList.get(i).getId() == idCheck)
						inputId = i;
				}
				heap.heapIncreaseKey(pList, inputId, inputKey);

				System.out.println("*****Priority index succesfully updated*****");
				System.out.println();
			}
			else if (usrInput == 6) {
				isRunning = false; // ends loop of user menu options
			}
		}

		reader.close(); // close the Scanner
	}
	
	// prints the heap as a max-heap
	public static void printMaxHeap() {
		heap.buildMaxHeap(pList);
		heap.printHeap(pList);
	}

	// inserts a new process into the heap
	public static void insertProcess() throws Exception {
		heap.maxHeapInsert(pList);
		System.out.println();
		System.out.println("===========UPDATED HEAP==========");
		heap.buildMaxHeap(pList);
		heap.printHeap(pList);
		System.out.println("*****You have inserted a new process!*****");
		System.out.println();
	}
	
	// sorts the heap least to greatest and prints it
	public static void printSortedList() {
		heap.heapSort(pList);
		System.out.println();
		System.out.println("===========SORTED LIST==========");
		heap.printAry(pList);
		System.out.println();
	}
	
	// makes heap into a max-heap and then extracts the highest priority process 
	public static void removeFirst() throws Exception {
		heap.buildMaxHeap(pList);
		Process temp = heap.heapExtractMax(pList);
		System.out.println();
		System.out.println("===========UPDATED HEAP==========");
		heap.printHeap(pList);
		System.out.println("*****You have removed process " + temp.getId() + " " + temp.getKey() + "*****");
		System.out.println();

	}
} 