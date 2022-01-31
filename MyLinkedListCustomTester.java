
/**
 * Name:Bill(Jinshi) He
 * ID: A17005195
 * Email: jih064@ucsd.edu
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * 
 * 2-4 sentence file description here
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList list1, list2;
    private MyLinkedList.MyListIterator listIt1, listIt2;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        list1 = new MyLinkedList();
        listIt1 = list1.new MyListIterator();

        list2 = new MyLinkedList();
        list2.add("CSE11");
        list2.add("CSE12");
        listIt2 = list2.new MyListIterator();
    }

    /**
     * @author Bill(Jinshi) He  
     * test the hasNext method when called on a empty MyLinkedList
     */
    @Test
    public void testHasNext() {
        // list1 is a empty linked list
        assertFalse("hasNext() should return false when applying to empty list"
            , listIt1.hasNext());
    }

    /**
     * @author Bill(Jinshi) He
     * test the next method when there is no next element node in the list
     */
    @Test
    public void testNext() {
        boolean ifExceptionIsThrown = false;
		try {
			// list1 is an empty linked list; calling next() should throw
            // exception
            listIt1.next();
		}
		catch (NoSuchElementException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }

    /**
     * @author Bill(Jinshi) He
     * test the hasPrevious method when applying to an empty list
     */
    @Test
    public void testHasPrevious() {
        // list1 is a empty linked list
        assertFalse("hasPrevious() should return false when applying to empty list"
            , listIt1.hasPrevious());
    }

    /**
     * @author Bill(Jinshi) He
     * test the previous method when there is no previous element node
     */
    @Test
    public void testPrevious() {
        boolean ifExceptionIsThrown = false;
		try {
			// list1 is an empty linked list; calling previous() should throw
            // exception
            listIt1.previous();
		}
		catch (NoSuchElementException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }

    /**
     * @author Bill(Jinshi) He
     * test the nextIndex method when applying to empty linked list
     */
    @Test
    public void testNextIndex() {
        assertEquals("NextIndex() should return 0 if it is empty list", 0
            , listIt1.nextIndex());
    }

    /**
     * @author Bill(Jinshi) He
     * test the previousIndex method when applying to empty list
     */
    @Test
    public void testPreviousIndex() {
        assertEquals("PreviousIndex() should return -1 if it is empty list", 
            -1, listIt1.previousIndex());
    }

    /**
     * @author Bill(Jinshi) He
     * test the set method when element is null
     */
    @Test
    public void testSet() {
        boolean ifExceptionIsThrown = false;
		try {
			// setting the element to null should throw exception
            listIt1.set(null);
		}
		catch (NullPointerException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }

    /**
     * @author Bill(Jinshi) He
     * test the set method when add/remove have been called since most recent
     * next/previous call
     */
    @Test
    public void testSetTwo() {
        boolean ifExceptionIsThrown = false;
		try {
			// add a element to list2 with methods in listIt2
            listIt2.add("CSE20");

            // calling set method right after add method should throw 
            // IllegalStateException
            listIt2.set("CSE21");
		}
		catch (IllegalStateException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }

    /**
     * @author Bill(Jinshi) He
     * test the remove method when neither next nor previous were called
     */
    @Test
    public void testRemoveTestOne() {
        boolean ifExceptionIsThrown = false;
		try {
            // neither next nor previous were called on listIt1. Calling remove
            // should throw IllegalStateException
            listIt1.remove();
		}
		catch (IllegalStateException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }

    /**
     * @author Bill(Jinshi) He
     * test the remove method when add or remove have been called
     */
    @Test
    public void testRemoveTestTwo() {
        boolean ifExceptionIsThrown = false;
		try { 
            listIt2.add("CSE30");

            // Just called add to listIt2. Calling remove here should throw
            // IllegalStateException
            listIt2.remove();
		}
		catch (IllegalStateException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }
    /**
     * @author Ethan Williams
     * test the remove method when calling it twice(second should give State exception)
     */
    @Test
    public void testRemoveTestThree() {
        
        MyLinkedList<Integer> fList= new MyLinkedList<Integer>();
        fList.add(5);
        fList.add(7);
        MyLinkedList.MyListIterator fListIterator = fList.new MyListIterator();
        
        fListIterator.next();
        fListIterator.remove();
        int dummy = 0;
        try{
            fListIterator.remove();
        }catch(IllegalStateException a){
            dummy = 1;
        }     
        assertEquals("", 1, dummy);
    }

    /**
     * @author Bill(Jinshi) He
     * test the add method when element is null
     */
    @Test
    public void testAdd() {
        boolean ifExceptionIsThrown = false;
		try { 
            listIt1.add(null);
		}
		catch (NullPointerException e)
		{
			ifExceptionIsThrown = true;
		}
		assertTrue("Test if exception is thrown", ifExceptionIsThrown);
    }
     
    /**
     * @author Ethan Williams
     * test the add method when adding to empty list
     */
    @Test
    public void testAdd2() {
        MyLinkedList empty = new MyLinkedList<String>();
        MyLinkedList.MyListIterator emptyIt = empty.new MyListIterator();
        emptyIt.add((Integer)5);
        assertEquals("", 5, emptyIt.previous());
    }
}
