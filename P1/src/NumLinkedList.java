/**
 * ADT of a Linked List that stores numbers.
 * EECS 233, Prof. Lewicki 
 * @author Ryan Rose
 */
public class NumLinkedList implements NumList{
	/**
	 * Node class.
	 */
	private class DoubleNode{
		/**
		 * Stores the value of the node.
		 */
		private double element;
		
		/**
		 * Stores the address of the next node in the list.
		 */
		private DoubleNode next;
		
		/**
		 * Constructor the requires an element value, and the address of the next node (or null).
		 * @param element the value of the element to be stored in the node
		 * @param next the address of the next node (or null)
		 */
		public DoubleNode(double element, DoubleNode next){
			this.element = element;
			this.next = next;
		}
		
		/**
		 * Returns the address of the next node.
		 */
		public DoubleNode next(){
			return this.next;
		}
		
		/**
		 * Sets the next node of this node
		 */
		public void setNext(DoubleNode node){
			next = node;
		}
		
		/**
		 * Returns the element of this node.
		 */
		public double getElement(){
			return element;
		}
		
		/**
		 * Sets the element of this node.
		 */
		/* public void setElement(double value){
			this.element = value;
		} */
	}
	
	/**
	 * Holds the head of the linked list.
	 */
	private DoubleNode head;
	
	/**
	 * Holds the tail of the linked list
	 */
	private DoubleNode tail;
	
	/**
	 * Holds the number of elements in the list.
	 */
	private int size;
	
	/**
	 * Creates an empty list of size zero.
	 */
	public NumLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * Returns the size, i.e. number of elements, in the list.
	 */
	public int size(){
		return size;
	}
	
	/**
	 * Sets the size, i.e. number of elements in the list.
	 */
	private void setSize(int value){
		size = value;
	}
	
	/**
	 * Gets the head.
	 */
	private DoubleNode getHead(){
		return head;
	}
	
	/**
	 * Sets the head.
	 * @param node the DoubleNode to be made the head
	 */
	private void setHead(DoubleNode node){
		head = node;
	}
	
	/**
	 * Gets the tail.
	 */
	private DoubleNode getTail(){
		return tail;
	}
	
	/**
	 * Sets the tail.
	 * @param node the DoubleNode to be made the tail
	 */
	private void setTail(DoubleNode node){
		tail = node;
	}
	
	/**
	 * Removes the duplicates of the list, maintaining order, and the first value of each element.
	 */
	public void removeDuplicates(){
		NumLinkedList newList = new NumLinkedList();
		DoubleNode ndptr = getHead();
		for(int i = 0; i < size() - 1; i++){
			if(!newList.contains(lookup(i)))
				newList.add(lookup(i));
			ndptr = ndptr.next();
		}
		if(!newList.contains(ndptr.getElement()))
			newList.add(ndptr.getElement());
		setHead(newList.getHead());
		setTail(newList.getTail());
		setSize(newList.size());
	}
	
	/**
	 * Adds a new value to the end of the list.
	 * @param value the value to be added to the list
	 */
	public void add(double value){
		if(size() == 0){
			setHead(new DoubleNode(value, null));
			setTail(getHead());
			setSize(1);
		}
		else{
			DoubleNode adding = new DoubleNode(value, null);
			getTail().setNext(adding);
			setTail(adding);
			setSize(size() + 1);
		}
	}
	
	/**
	 * Checks if another list is equal to this list.
	 * @param otherList the other list to be compared to this list
	 */
	public boolean equals(NumList otherList){
		String thisString = this.toString();
		String otherString = otherList.toString();
		return thisString.equals(otherString);
	}
	
	/**
	 * Inserts a new element at the ith index. If i > the number of elements in the list, add the value to the end of the list.
	 * @param i index of insertion
	 * @param value the value to be inserted into the list
	 */
	public void insert(int i, double value){
		DoubleNode ndptr = getHead();
		if(i >= size()){
			add(value);
		}
		else{
			for(int index = 0; index < i - 1; index++)
				ndptr = ndptr.next();
			DoubleNode save = ndptr.next();
			DoubleNode adding = new DoubleNode(value, null);
			ndptr.setNext(adding);
			ndptr.next().setNext(save);
			setSize(size() + 1);
		}
	}
	
	/**
	 * Removes the i-th element.
	 * @param i index at which the value should be removed
	 */
	public void remove(int i){
		DoubleNode ndptr = getHead();
		if(i == 0){
			setSize(size() - 1);
			setHead(getHead().next());
		}
		else if(i < size() - 1){
			for(int index = 0; index < i - 1; index++)
				ndptr = ndptr.next();
			ndptr.setNext(ndptr.next().next());
			setSize(size() - 1);
		}
		else if(i == size() - 1){
			for(int index = 0; index < i - 1; index++)
				ndptr = ndptr.next();
			ndptr.setNext(null);
			setTail(ndptr);
			setSize(size() - 1);
		}
	}
	
	/**
	 * Checks is a given value is in the list.
	 * @param value value that is being checked to the list
	 */
	public boolean contains(double value){
		DoubleNode ndptr = getHead();
		for(int index = 0; index < size(); index++){
			if(ndptr.getElement() == value)
				return true;
			ndptr = ndptr.next();
		}
		return false;
	}
	
	/**
	 * Looks up the value of the list at a specific index.
	 * @param i index at which the value is being looked up
	 */
	public double lookup(int i) throws NullPointerException{
		DoubleNode ndptr = getHead();
		for(int index = 0; index < i; index++){
			try{
				ndptr = ndptr.next();
			}
			catch(IndexOutOfBoundsException e){
				System.err.println("NullPointerException:" + e.getMessage());
			}
		}
		return ndptr.getElement();
	}
	
	/**
	 * Turns the list contents into a String, separated by spaces.
	 */
	public String toString(){
		if(size() == 0)
			return "";
		else if(size() == 1)
			return "" + getHead().getElement() + "";
		else{
			StringBuilder builder =  new StringBuilder();
			DoubleNode ndptr = getHead();
			while(ndptr.next() != getTail()){
				builder.append(ndptr.getElement());
				builder.append(' ');
				ndptr = ndptr.next();
			}
			builder.append(ndptr.getElement());
			builder.append(' ');
			builder.append(ndptr.next().getElement());
			return builder.toString();
		}
	}
}
