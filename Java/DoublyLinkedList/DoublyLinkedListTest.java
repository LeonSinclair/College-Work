import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);  
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }
    
    @Test
    public void testDeleteAt()
    {
    	
    	
    	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	//test empty list
    	
    	assertEquals("testing deleting in an empty list", false,testDLL.deleteAt(0));
        assertEquals("deleting a negative element", false, testDLL.deleteAt(-12));
        
        //test deleting with single element
    	testDLL.insertBefore(0,1);
        testDLL.deleteAt(0);
        assertEquals("testing deleting in an empty list", "",testDLL.toString());
        testDLL.insertBefore(0,1);

        assertEquals("testing delete with a greater value than size", false ,testDLL.deleteAt(1));
        testDLL.deleteAt(0);
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        //test deleting first element, last element, and element in the middle
        testDLL.deleteAt(0);
        assertEquals("testing deleting first element", "1,2,3", testDLL.toString());
        testDLL.deleteAt(2);
        assertEquals("testing deleting last element", "1,2", testDLL.toString());
        testDLL.insertBefore(2,3);
        testDLL.deleteAt(1);
        assertEquals("testing deleting middle element", "1,3", testDLL.toString());
        assertEquals("testing deleting element(1) in 2 element list", true, testDLL.deleteAt(1));
    }
    
    
    @Test
    public void testStacks()
    {
    	DoublyLinkedList<Integer> stackTest = new DoublyLinkedList<Integer>();
    	stackTest.reverse();
    	assertEquals("pop off empty stack", null, stackTest.pop());
    	stackTest.pop();
    	stackTest.push(-1);
    	stackTest.pop();
    	stackTest.push(2);
    	stackTest.push(3);
    	stackTest.push(4);
    	
    	assertEquals("push to stack", "4,3,2", stackTest.toString());
    	
    	assertEquals("pop off stack", 4, (int)stackTest.pop());
    	assertEquals("pop off stack", "3,2", stackTest.toString());	
    	
    	stackTest.reverse();
    	assertEquals("reverse", "2,3", stackTest.toString());
    	stackTest.reverse(); // returning to normal
    	
    	stackTest.push(4);
    	stackTest.reverse();
    	assertEquals("reverse", "2,3,4", stackTest.toString());
    }
    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    
    
    @Test
    public void testQueues()
    {
    	DoublyLinkedList<Integer> queueTest = new DoublyLinkedList<Integer>();
    	assertEquals("pop off empty stack", null, queueTest.dequeue());
    	
    	queueTest.enqueue(2);
    	assertEquals("get element", 2, (int) queueTest.dequeue());
    	queueTest.enqueue(2);
    	
    	queueTest.enqueue(3);
    	queueTest.enqueue(4);
    	
    	assertEquals("add to queue", "4,3,2", queueTest.toString());
    	
    	assertEquals("dequeue", 2, (int) queueTest.dequeue());
    	assertEquals("dequeue", "4,3", queueTest.toString());	
    }
    
    @Test
    
    public void testGet()
    {
    	DoublyLinkedList<Integer> queueTest = new DoublyLinkedList<Integer>();
    	queueTest.enqueue(2);
    	assertEquals("testing single element get", 2, (int) queueTest.get(0));
    	queueTest.enqueue(3);
    	queueTest.enqueue(4);
    	assertEquals("test for greater than size", null, queueTest.get(12));
    	queueTest.dequeue();
    	queueTest.dequeue();
    	assertEquals("out of bounds testing", null, queueTest.get(1));
    	queueTest.enqueue(5);
    	queueTest.enqueue(6);
    	queueTest.enqueue(7);
    	assertEquals("test get for more than 1 element list", 4, (int)queueTest.get(3));
    }
    

}

