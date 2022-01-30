/**
 * Name:Ethan Williams
 * Email:e5williams@ucsd.edu
 * Sources used: javadoc conventions: 
 * https://www2.hawaii.edu/~tp_200/ics111/material/codingStandards.
 * html#:~:text=Every%20class%20and%20method%20should%20be%20preceded%20with,
 * should%20name%20the%20class%2C%20describe%20its%20purpose%2C%20and
 * 
 * This file holds MyLinkedList and Node classes.
 * MyLinkedList is an extention of the AbstractList class
 * from the java's util.
 */

import java.util.AbstractList;

/** 
 * MyLinkedList is an implementation of the LinkedList that
 * includes a no-argument constructor, and the methods:
 * add(at index), add, get, getNth, set, remove, clear, isEmpty, size.
 * MyLinkedList uses a nested Node class for its nodes.
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int size; //tracks number of elements
	Node head; //will be a null dummy node
	Node tail; //will also be a null dummy node

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
			this.next = null;
			this.prev = null;
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
	/** Only 0-argument constructor is defined */
	public MyLinkedList() {
		this.head = new Node(null); //dummy
		this.tail = new Node(null); //dummy
		this.head.setNext(this.tail);//point head/tail to eachother
		this.tail.setPrev(this.head);
		this.size = 0; //no elements in list so far
	}

	/**
   * Accessor of size field
   * @return size field of this list.
   */ 
	@Override
	public int size() {
		
		return this.size; //return field the accessor is for
	}
	/**
   * Accessor for specified node's element field
   * @param index the index of the element to return
   * @exception IndexOutOfBoundsException if index is negative
   * or greater than or equal to the size of the list
   * @return Element of the indexth element.
   */ 
	@Override
	public E get(int index) {
		//exception for index being negative or OOB
		if(index < 0 || index >= this.size){
			throw new IndexOutOfBoundsException("");
		}

		E toRet; //will hold return value
		toRet = this.getNth(index).getElement();
		return toRet;
	}
	 /**
   * Creates a node with Element:data at the indexth position
   * @param index the index of where to add 
   * @param data element field of node to add
   * @exception IndexOutOfBoundsException if index is negative
   * or greater than the size of the list
   * @exception NullPointerException if data is null
   */ 
	@Override
	public void add(int index, E data) {
		//exception for data being null
		if(data == null){
			throw new NullPointerException("");
		}
		//exception for index being negative or OOB
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("");
		}
		//case of adding at index after last
		if(index == size){
			this.add(data);
			return; //return because finished
		}
		Node newNode = new Node(data); //node to insert/add
		newNode.setNext(this.getNth(index)); //set nN pointers 1st
		newNode.setPrev(this.getNth(index).getPrev());
		newNode.getNext().setPrev(newNode); //update other pointers
		newNode.getPrev().setNext(newNode); 
		this.size++; //update size
	}
	 /**
   * Creates a node with Element:data at the end of list
   * (but before the tail's dummy node) 
   * @param data element field of node to add
   * @exception NullPointerException if data is null
   * @return true; no case for false
   */ 
	public boolean add(E data) {
		//exception for data being null
		if(data == null){
			throw new NullPointerException("");
		}

		Node newNode = new Node(data); //node to add
		this.tail.getPrev().setNext(newNode);
		newNode.setPrev(this.tail.getPrev());
		newNode.setNext(this.tail);
		this.tail.setPrev(newNode);
		this.size++; //update size
		return true;
	}
	/**
   * sets a specified node's Element field to data
   * @param index the index of the element to change
   * @param data value to change Element to
   * @exception IndexOutOfBoundsException if index is negative
   * or greater than or equal to the size of the list
   * @exception NullPointerException if data is null
   * @return replaced data from node
   */ 
	public E set(int index, E data) {
		//exception for null data
		if(data == null){
			throw new NullPointerException("");
		}
		//exception for index being negative or OOB
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("");
		}
		E toRet = this.get(index); //holds return value
		this.getNth(index).setElement(data); //change element
		return toRet; 
	}
	/**
   * removes specified node from list
   * @param index the index of the node to remove
   * @exception IndexOutOfBoundsException if index is negative
   * or greater than or equal to the size of the list
   * @return removed node's data
   */ 
	public E remove(int index) {
		//exception for index being negative or OOB
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("");
		}
		E toRet = this.get(index); //holds return value
		Node toRem = this.getNth(index); //node to remove
		//update surrounding pointers to exclude toRem
		toRem.getNext().setPrev(toRem.getPrev());
		toRem.getPrev().setNext(toRem.getNext()); 

		this.size--; //update size
		return toRet; 
	}
	/**
   * clears all non-dummy nodes from list
   * and resets size
   */ 
	public void clear() {
		this.head = new Node(null); //reset fields
		this.tail = new Node(null);
		this.head.setNext(this.tail); //point head & tail to eachother
		this.tail.setPrev(this.head);
		this.size = 0;
	}
	/**
   * tells if the list is empty
   * @return true if there are no non-dummy nodes 
   * in list; false otherwise
   */ 
	public boolean isEmpty() {
		if(this.size == 0){//check if 0 elements
			return true;
		}  return false;
	}
	/**
   * Accessor for specified node
   * @param index the index of the element to access
   * @exception IndexOutOfBoundsException if index is negative
   * or greater than or equal to the size of the list
   * @return specified node
   */ 
	protected Node getNth(int index) {
		//exception for index being negative or OOB
		if(index < 0 || index > this.size){
			throw new IndexOutOfBoundsException("");
		}
		Node curNode = this.head.getNext(); //use to traverse
		for(int i = 0; i < index; i++){
			curNode = curNode.getNext();
		}
		return curNode;
	}
}