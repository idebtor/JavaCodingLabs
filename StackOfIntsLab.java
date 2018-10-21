import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Stack;

/** 
 * Lecture note: Thinking in Objects
 * @author Youngsup Kim <p>
 * Step 1: The StackOfInts class implements a stack that holds ints.
 * Step 2: The StackOfIntegers class implements a stack that holds Integers.
 * Step 3: The StackGenerics class implements a stack that holds Java generic type. 
 * Step 4: Make StackGenerics iterable such that we can use for-each loop.
 * 
 * Specifications: Follow the how Java Stack works. 
 *     You may test the following cases in Java Stack
 * 	   - the initial capacity of the stack
 *     - the rule of the stack capacity increment when the stack is full
 *     - the rule of the stack capacity decrement
 *     - how clear() works, is there a minimum stack capacity to keep?
 *     - check how trimToSize() works
 *     - toString() - Use a StringBuilder instead of String during processing
 *     - equals()
 *     - pop() with empty stack 
 * <p> 
 * 
 * Topics: 
 * Stack - LIFO
 * 	- Review Java class Stack: 
 *		. size, capacity, push(), pop(), peek(), clear(), empty()
 *		. trimToSize()
 * 
 *  - Implement StackOfIntegers
 *  	. when and how doubling capacity, reducing capacity
 *  	. System.arraycopy()
 *  	. print stack? - toString(), String vs StringBuilder
 *  	. stack empty? 
 *  - Use EmptyStackException, throw-try-catch
 * 	- Why using size(), capacity() instead of getSize(), getCapacity()
 *  - No Side-Effect: not printing in class definition
 *  - Throwing exception: IllegalArgumentException, EmptyStackException
 */

public class StackOfIntsLab {
	private int[] elements;
	private int size;
	private static final int DEFAULT_CAPACITY = 2;
	
	/** Construct a stack with the default capacity */
	public StackOfIntsLab() {
		this(DEFAULT_CAPACITY );
	}
	
	/** Construct a stack with the specified maximum capacity */
	public StackOfIntsLab(int capacity) {		
		elements = new int[capacity];
	}

	/**
	 * Push a new int into the top of the stack 
	 * Double the stack capacity when size > capacity().
	 * */
	public void push(int item) {
		if (size >= capacity()) {
			int[] temp = new int[capacity() * 2];
			System.arraycopy(elements, 0, temp, 0, size);
			elements = temp;
		}
		elements[size++] = item;
	}

	/**
	 * Return and remove the top element from the stack 
	 * Reduce the stack capacity in half when  
	 * (size * 4 <= capacity())
	 */
	public int pop(){

		if (size * 4 <= capacity()) {
			int[] temp = new int[capacity() / 2];
			System.arraycopy(elements, 0, temp, 0, size);
			elements = temp;
		}
		return elements[--size];
	}

	/** Return the top element from the stack */
	public int peek() {
		return elements[size-1];
	}
	
	/** Test whether the stack is empty */
	public boolean empty() {	
		return size == 0;
	}

	/** Return the number of elements in the stack */
	public int size() {
		return size;
	}
	
	public int capacity() {
		return elements.length;
	}
	
	/** Make the stack empty */
	public void clear() {
		size = 0;
		elements = new int[DEFAULT_CAPACITY];
	}
	
	/** make it fit tight */
	public void trimToSize() {
		// resize elements to capacity
		if (capacity() > size) {
			int[] temp = new int[size];
			System.arraycopy(elements, 0, temp, 0, size);
			elements = temp;
		}
	}
	
	/*
	 * Returns a string repr. of stack
	 * For example: "[1, 2, 4]"
	 */
	@Override
	public String toString(){
		String delimiter = ", ";
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < size; i++) 
			sb.append(elements[i] + delimiter);
		int i = sb.lastIndexOf(delimiter);   // remove the last ", " if any.
		if (i > 0) sb.delete(i, i+2);
		
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		/* part1 */
		Stack<Integer> as1 = new Stack<>();
		as1.push(1);
		as1.push(3);
		as1.push(7);
		as1.push(17);
		System.out.println(as1);
		StackOfIntsLab s1 = new StackOfIntsLab();
		for(Integer one : as1) {
			s1.push(one);
		}
		System.out.println(s1);

		/* part2 */
		System.out.println("DEFAULT_CAPACITY: " + s1.capacity());
		StackOfIntsLab s2 = new StackOfIntsLab(3);
		System.out.println("s2's capacity: " + s2.capacity()+", size: " + s2.size()+"\n");
		for(int i = 0; i<4;i++) {
			s2.push(i*10);
		}
		System.out.println("s2: " + s2.toString());
		System.out.println("size: " + s2.size() +", capacity: " +s2.capacity());
		s2.pop();
		s2.pop();
		s2.pop();
		System.out.println("s2: " + s2.toString());
		System.out.println("size: " + s2.size() +", capacity: " +s2.capacity());
		s2.push(-10);
		s2.trimToSize();
		if(s2.empty()) {
			System.out.println("s2 is empty");
		} else {
			System.out.println("s2 is not empty");
			System.out.println("s2: " + s2.toString());
			System.out.println("size: " + s2.size() +", capacity: " +s2.capacity());
			System.out.println("peek: " + s2.peek());
		}
	
		s2.clear();
		System.out.println("clear!");
		if(s2.empty()) {
			System.out.println("size: " + s2.size() +", capacity: " +s2.capacity());
			System.out.println("s2 is empty");
		} else {
			System.out.println("s2 is not empty");
			System.out.println("s2: " + s2.toString());
			System.out.println("size: " + s2.size() +", capacity: " +s2.capacity());
		}
	}
}
