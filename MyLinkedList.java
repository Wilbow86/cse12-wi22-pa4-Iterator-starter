/**
 * Name: Jinshi He, Ethan Williams
 * Email: jih064@ucsd.edu, e5williams@ucsd.edu
 * Sources: None
 * 
 * This file contains all of the implementations of linkedlist for PA3, which 
 * includes a MyLinkedList class (outlines the general structure of a 
 * linkedlist) and a Node class (describe the attributes of a single node). 
 * Node class is within MyLinkedList class, and they both contains useful 
 * methods in support of the implementation of linkedlist as a whole. The file
 * also contains the MyListIterator class which is a more optimized tool to
 * do library functions on a LinkedList
 */

import java.lang.reflect.InaccessibleObjectException;
import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/** 
 * This is a MyLinkedList class where the general structure of linkedlist is
 * implemented. It includes three instance variables which corresponds to 
 * three of its essential attributes: head node, tail node, and size. It also 
 * includes many helper methods, enabling users to modify the linkedlist
 * as they want. 
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size;
	Node head;
	Node tail;

	/**
	 * A Node class that holds data and references to previous and next Nodes.
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;

		/** 
		 * Constructor to create singleton Node 
		 * @param element Element to add, can be null	
		 */
		public Node(E element) {
			// Initialize the instance variables
			this.data = element;
			this.setNext(null);;
			this.setPrev(null);;
		}

		/** 
		 * Set the parameter prev as the previous node
		 * @param prev - new previous node
		 */
		public void setPrev(Node prev) {
			this.prev = prev;		
		}

		/** 
		 * Set the parameter next as the next node
		 * @param next - new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/** 
		 * Set the parameter element as the node's data
		 * @param element - new element 
		 */
		public void setElement(E element) {
			this.data = element;
		}

		/** 
		 * Accessor to get the next Node in the list 
		 * @return the next node
		 */
		public Node getNext() {
			return this.next;
		}

		/** 
		 * Accessor to get the prev Node in the list
		 * @return the previous node  
		 */
		public Node getPrev() {
			return this.prev;
		}

		/** 
		 * Accessor to get the Nodes Element 
		 * @return this node's data
		 */
		public E getElement() {
			return this.data;
		}
	}

	//  Implementation of the MyLinkedList Class
	/**
	 * The zero argument constructor for MyLinkedList that initiate all of
	 * its instance variables
	 */
	public MyLinkedList() {
		// initiate head and tail to null node
		this.head = new Node(null);
		this.tail = new Node(null);

		// set up the next and previous for appropriate nodes
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);

		// initiate size to zero
		this.size = 0;
	}

	/**
	 * Return the number of nodes being stored
	 * @return the number of nodes stored
	 */
	@Override
	public int size() {
		// return the number of nodes stored in linkedlist
		return this.size;
	}

	/**
	 * Get data within the node at position index
	 * @param index - the index of targeted node
	 * @return the data stored in the targeted node
	 */
	@Override
	public E get(int index) {
		// check if to throw exception
		if(index < 0 || index > (this.size() - 1)) {
			throw new IndexOutOfBoundsException("index is out of bound: " + index);
		}

		// return the element by accessing getNth(int index) and getElement
		// methods
		return this.getNth(index).getElement();  
	}

	/**
	 * Add a node into this list by index. The input index can be any integers 
	 * in between zero and the number of elements (inclusive on both ends)
	 * @param index - the index we want to add our new node to
	 * @param data - the data of the newly added node
	 */
	@Override
	public void add(int index, E data) {
		// check if any exceptions should be thrown
		if(data == null) {
			throw new NullPointerException("element is invalid");
		}
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException("index is out of bound: " + index);
		}

		// initiate toAdd node and after node that will be after toAdd
		// when add method is over
		Node toAdd = new Node(data);
		Node after = this.head.getNext();
		for(int i = 0; i < index; i++) {
			after = after.getNext();
		}

		// set up the previous and next for appropriate nodes
		toAdd.setPrev(after.getPrev());
		toAdd.setNext(after);
		after.getPrev().setNext(toAdd);
		after.setPrev(toAdd);
		
		// increase the size
		this.size += 1;
	}

	/**
	 * Add a node at the end into this list
	 * @param data - the data of the newly added node
	 * @return always true (such thing exists due to the method definiton in
	 * AbstractList)
	 */
	public boolean add(E data) {
		// check if data is null
		if(data == null) {
			throw new NullPointerException("element is invalid");
		}

		// use existing add(int index, E data) method
		this.add(this.size(), data);
		
		// always return true
		return true; 
	}

	/**
	 * Set the element for the node at index to data
	 * @param index - index of the node in which we want to change element
	 * @param data - new data that is going to replace the old data
	 * @return the element that was previously stored in this node
	 */
	public E set(int index, E data) {
		// check if to throw exceptions
		if(data == null) {
			throw new NullPointerException("element is invalid");
		}
		if(index < 0 || index > (this.size() - 1)) {
			throw new IndexOutOfBoundsException("index is out of bound: " + index);
		}

		// get specified node, save old data, update it to new data
		Node target = this.getNth(index);
		E oldData = target.getElement();
		target.setElement(data);
		
		// return old data
		return oldData;
	}

	/**
	 * Remove the node from the position index in this list
	 * @param index - index of the node that we are about to remove
	 * @return the element that was previously stored in this node
	 */
	public E remove(int index) {
		// check if to throw exceptions
		if(index < 0 || index > (this.size() - 1)) {
			throw new IndexOutOfBoundsException("index is out of bound: " + index);
		}

		// initate the target node we would like to remove, before node that 
		// represents the node before target node, and after node that 
		// represents the node after target node
		Node target = this.getNth(index);
		Node before = target.getPrev();
		Node after = target.getNext();

		// set previous and next for appropriate nodes
		before.setNext(after);
		after.setPrev(before);

		// decrease the size
		this.size -= 1;

		// return removed node's data
		return target.getElement();
	}

	/**
	 * Remove all nodes from the list
	 */
	public void clear() {
		// set previous and next for appropriate nodes
		this.head.setNext(this.tail);
		this.tail.setPrev(this.head);

		// set size to zero
		this.size = 0;
	}

	/**
	 * Determine if the list is empty
	 */
	public boolean isEmpty() {
		// check if size is zero
		return (this.size() == 0);
	}

	/**
	 * A helper method that returns the Node at a specified index, not the 
	 * content
	 * @param index - index of the targeted node
	 * @return node at the specified index
	 */
	protected Node getNth(int index) {
		if(index < 0 || index > (this.size() - 1)) {
			throw new IndexOutOfBoundsException("index is out of bound: " + index);
		}
		Node target = this.head.getNext();
		for(int i = 0; i < index; i++) {
			target = target.getNext();
		}
		return target;
	}
	
	/**
	*Method is for PA, seems to serve no purpose
	*
	*@author Ethan Williams
	*@return new myListIterator
	*/
	public ListIterator<E> listIterator(){
	
		return this.new MyListIterator;
	}
	
	/**
	*Method is for PA, seems to serve no purpose
	*
	*@author Ethan Williams
	*@return new myListIterator
	*/
	public ListIterator<E> Iterator(){
	
		return this.new MyListIterator;
	}

	/**
	 * MyListIterator class implements a list iterator that is able to traverse
	 * list by moving a space at a time in either direction. It can also add, 
	 * set, and remove elements in the list. 
	 */
	protected class MyListIterator implements ListIterator<E> {
		// class variables
		Node left, right;
		int idx;
		boolean forward;
		boolean canRemoveOrSet;

		/**
		 * @author Bill(Jinshi) He
		 * The zero argument constructor that creates an iterator on a 
		 * MyLinkedList. It sets up all of its instance variables to default
		 * value.
		 */
		public MyListIterator() {
			this.idx = 0;
			this.forward = true;
			this.canRemoveOrSet = false;
			this.left = head;
			this.right = head.getNext();
		}
		
		// MyListIterator methods
		/**
		 * @author Bill(Jinshi) He
		 * Determine if there is an element node when going in the forward
		 * (head to tail) direction fron the current iterator position. Dummy
		 * nodes do not count as element nodes
		 * @return if there is a next element node in the list
		 */
		public boolean hasNext() {
			// If right node is tail node, then there is no next element,
			// meaning that we are gonna return false. 
			return (this.right != tail);
		}

		/**
		 * @author Bill(Jinshi) He
		 * Return the next element in the list when going forward, and move the 
		 * iterator forward by one node.
		 * @return the element of the next node in the list
		 */
		public E next() {
			// Use haxNext() to determine if to throw NoSuchElementException
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}

			// Save the element of the node when going forward
			E toReturn = this.right.getElement();
			
			// Modify the instance variables according to list iterator rule
			this.idx += 1;
			this.left = this.left.getNext();
			this.right = this.right.getNext();
			this.forward = true;
			this.canRemoveOrSet = true;
			
			// Return the element
			return toReturn;
		}

		/**
		 * @author Bill(Jinshi) He
		 * Determine if there is an element node in the backward direction
		 * (tail to head) direction from the current iterator position. Dummy 
		 * nodes do not count as element nodes.
		 * @return if there is a previous element node in the list
		 */
		public boolean hasPrevious() {
			// If the left node is head node, then there is no previous element 
			// node in the list, which means that we are gonna return false.
			return (this.left != head);
		}

		/**
		 * @author Bill(Jinshi) He
		 * Return the next element in the list when going backward, and move 
		 * the iterator by one node.
		 * @return element of the previous node in the list
		 */
		public E previous() {
			// Use haxPrevious() to determine if to throw NoSuchElementException
			if(!this.hasPrevious()) {
				throw new NoSuchElementException();
			}

			// Save the element of the node when going backward
			E toReturn = this.left.getElement();
			
			// Modify the instance variables according to list iterator rule
			this.idx -= 1;
			this.left = this.left.getPrev();
			this.right = this.right.getPrev();
			this.forward = false;
			this.canRemoveOrSet = true;

			// Return the element
			return toReturn;
		}

		public int nextIndex() {
			if(this.right == tail) {
				return size();
			}
			return this.idx;
		}

		/**
		 * Method gives the value of the index of the node 
		 * that would be returned by Previous
		 * 
		 * @return the index of the node that would be returned by Previous
		 * @Author Ethan Williams
		 */

		public int previousIndex(){
			// previous would return left node, so use idx-1
			return idx -1;
		}

		/**
		 * @author Ethan Williams
		 * 
		 * Method adds a node with specified element after the 
		 * left node and moves forward an index
		 * 
		 * @param element element field of the node to be added
		 * @exception NullPointerException if element is null
		 */
		public void add(E element){
			//check if element is null
			if(element == null){throw new NullPointerException("");}

			Node newNode = new Node(element);
			//set newNode and left/right pointers
			newNode.setPrev(this.left);
			newNode.setNext(this.right);
			this.left.setNext(newNode);
			this.left = newNode;
			this.right.setPrev(newNode);
			//update index
			this.idx++;
			//update canRemoveOrSet
			this.canRemoveOrSet = false;

		}

		/**
		 * @author Ethan Williams
		 * 
		 * Method sets the element field of left/right node
		 * (depends on forward) to specified value
		 * 
		 * @param element element field to set chosen node's field to
		 * @exception NullPointerException if element is null
		 * @exception IllegalStateException if not allowed to set/remove 
		 * right now
		 */
		public void set(E element){

			//check if element is null
			if(element == null){throw new NullPointerException("");}
			//check if allowed to remove/set
			if(canRemoveOrSet){
				//if forward set left, if not set right
				if(forward){
					this.left.setElement(element);
				}else{this.right.setElement(element);}
				this.canRemoveOrSet = false;

			}else{throw new IllegalStateException("");}
		}

		/**
		 * @author Ethan Williams
		 * 
		 * Method removes the left or right node depending
		 * on state of forward
		 * 
		 * @exception IllegalStateException if mot allowed to set
		 * right now
		 */
		public void remove(){
			//check if allowed to remove
			if(canRemoveOrSet){
				//work on left if forward, right otherwise
				if(forward){
					//set ponters around node to remove to exclude the node
					this.left.getPrev().setNext(this.right);
					this.right.setPrev(this.left.getPrev());
					this.left = this.right.getPrev();
					idx--;
				}else{
					//set ponters around node to remove to exclude the node
					this.right.getNext().setPrev(this.left);
					this.left.setNext(this.right.getNext());
					this.right = this.left.getNext();
				}this.canRemoveOrSet = false;

			}else{throw new IllegalStateException("");}
		}		
	}
}
