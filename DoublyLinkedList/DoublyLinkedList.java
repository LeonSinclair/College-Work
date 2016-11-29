import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  
 *  @version 13/10/16 18:15
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 * DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated string of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail, tmp;

    /**
     * Constructor
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic runtime cost: theta(1)
     *
     * Justification:
     *  Constant running time since it is just returning the result of a boolean expression
     */
    public boolean isEmpty()
    {
      return(head==null && tail==null);
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: theta(N)
     * 
     * Justification:
     *  In the worst case you have go through a while loop up to very close to N
     */
    public void insertBefore( int pos, T data ) 
    {
       
    	DLLNode toInsert = new DLLNode(data,null,head);
    	if(pos<=0 || isEmpty())
    	{
    		toInsert.next = head;
    		toInsert.prev = null;
    		if(isEmpty()) 
    		{
    			tail = toInsert;
        		head = toInsert;
    		}
    		else
    		{
        		head.prev = toInsert;
        		head = toInsert;
    		}
    		
    	}
    	else if(pos>=size()-1)
    	{
    		toInsert.prev = tail;
    		toInsert.next = null;
    		tail.next = toInsert;
    		tail = toInsert;
    	}
    	else
    	{
    		int i = 0;
    		toInsert.next = head;
    		DLLNode tmpPointer = new DLLNode(null,head,head);
    		while(i!=pos && pos<=size())
    		{
    				tmpPointer.prev = tmpPointer.next;
    				tmpPointer.next = tmpPointer.next.next;
    				i++;
    		}
    		if(i==pos)
    		{
    			toInsert.prev = tmpPointer.prev;
				toInsert.next = tmpPointer.next;
				toInsert.prev.next = toInsert;
				toInsert.next.prev = toInsert;
    		}
    	}
      return;
    }

    /*
     * 
     * 
     */
    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic runtime cost: theta(N)
     *
     * Justification:
     *  Has to iterate through every node if pos is a large enough number
     *
     * Worst-case precise runtime cost: 2N + 1
     *
     * Justification:
     *  Has to iterate through every node in the worst case which costs N and has two function calls
     *  size has cost N, and isEmpty has constant running time - costs 1
     *  
     */
    public T get(int pos) 
    {	
      DLLNode getter = new DLLNode(null, null, head);
      int size = size();
      T returnItem = null;
      if(pos>=size)return null;
      if(isEmpty())return null;
      for(int i = 0; i<size; i++)
      {
    	  
    	  if(i==pos)
    	  {
    		 returnItem = getter.next.data;
    	  }
    	  else
    	  {
    		  getter = getter.next;
    	  }
      }
      return returnItem;
    }

    //
    /*
     * Worst Case Asymptotic Running Time: theta(N)
     * 
     * 
     * Justification
     * Has to go through every node to find the distance from head to tail
     * 
     * 
     * 
     */
    private int size() {
    	int count=0;
		if(head!=null && tail!=null)
		{
			count++;
			DLLNode tmp = head;
			while(tmp.next!=null)
			{
				tmp = tmp.next;
				count++;
			}
		}
		return count;
	}

	/**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: theta(N)
     *
     * Justification:
     *  Worst case is when pos == size-2 and it has to iterate through nearly every node in the list
     */
    public boolean deleteAt (int pos) 
    {
      
    	boolean returnFlag = false;
    	if(pos>size()-1|| pos<0) return returnFlag;
    	DLLNode toDelete = new DLLNode (null, head, head);
    	int i  = 0;
    	if(isEmpty()) return returnFlag;
    	else if(head==tail)
    	{
    		head = null;
    		tail = null;
    		returnFlag = true;
    		return returnFlag;
    	}
    	else if(pos==0)
    	{
    		head = head.next;
    		head.prev = null;
    		returnFlag = true;
    		return returnFlag;
    	}
    	else if(pos==size()-1)
    	{
    		tail = tail.prev;
    		tail.next = null;
    		returnFlag = true;
    		return returnFlag;
    	}
    	else
    	{
    		while(i<size())
    		{
    			if(i == pos)
    			{
    				toDelete.prev.next = toDelete.prev.next.next;
    				toDelete.next.prev = toDelete.next.prev.prev;
    				returnFlag = true;
    			}
    			else
    			{
    				toDelete.prev = toDelete.next;
    				toDelete.next = toDelete.next.next;
    				
    			}
    			i++;
    		}
    	}
      return returnFlag;
    }
    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic runtime cost: theta(N)
     *
     * Justification:
     *  Has to iterate through every element in a list of size N and swap their next and prev values
     */
    public void reverse()
    {
		while(head!=null)
		{
			tmp = head.next;
			head.next = head.prev;
			head.prev = tmp;
			if (tmp==null) // don't want to set head to null!
			{
				break;
			}
			head = tmp;
    		
    		//constantly move through and swap each prev and next link
        }
    	
    }


    /*----------------------- STACK */
    /**
     * This method should behave like the usual push method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: theta(1)
     *
     * Justification:
     *  constant running time since it is immediately placed in the list
     */
    public void push(T item) 
    {
      
    	insertBefore(0, item);
    }

    /**
     * This method should behave like the usual pop method of a Stack ADT.
     * If only the push and pop methods are called the data structure should behave like a stack.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: theta(1)
     *
     * Justification:
     *  Get(0) has constant running time since doesn't have to iterate at all
     *  the same is true for deleteAt(0)
     */
    public T pop() 
    {
    	
    	T item = get(0);
    	deleteAt(0);
    	return item;
    	
    }

    /*----------------------- QUEUE */
 
    /**
     * This method should behave like the usual enqueue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic runtime cost: theta(1)
     *
     * Justification:
     *  constant running time since it is immediately placed in the list and doesn't have to iterate
     */
    public void enqueue(T item) 
    {
    	insertBefore(0, item);
    }

     /**
     * This method should behave like the usual dequeue method of a Queue ADT.
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @return the earliest item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic runtime cost: theta(N)
     *
     * Justification:
     *  Must always go through to the last node of the list i.e must go through N values
     */
    public T dequeue() 
    {
      // pop from position size
      T item = get(size()-1);
      deleteAt(size()-1);
      return item;
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic runtime cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } 
        else 
        {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



