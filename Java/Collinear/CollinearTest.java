import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }
    
    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays", expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       
        int[] a2 = { 5 };       
        int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }

    
    @Test
    public void testBinarySearchValueNotFound()
    {
    	int[] a = {20,40,60,80,100};
    	int x = 3;
    	boolean expectedResult = false;
    	assertEquals("(binarySearch with value not in the array)", expectedResult, Collinear.binarySearch(a, x));
    }
    
    @Test
    public void testBinarySearchValueFound()
    {
    	int[] a = {20,40,60,80,100};
    	int x = 40;
    	boolean expectedResult = true;
    	assertEquals("(binarySearch with value in the array)", expectedResult, Collinear.binarySearch(a, x));
    }
    
    @Test
    public void testInsertionSortUnsortedArray()
    {
    	int[] a = {3,2,5,17,18};
    	int[] expectedResult = {2,3,5,17,18};
    	Collinear.sort(a);
    	Assert.assertArrayEquals("(Sorting an unsorted array)",expectedResult,a);
    }
    
    @Test
    public void testInsertionSortSortedArray()
    {
    	int[] a = {2,3,5,17,18};
    	int[] expectedResult = {2,3,5,17,18};
    	Collinear.sort(a);
    	Assert.assertArrayEquals("(Sorting a sorted array)",expectedResult,a);
    }
    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     *  You should read the lecture notes and/or book to understand how to correctly implement the main methods.
     *  You can use any of the provided classes to read from files, and time your code.
     *
     */
     public static void main(String[] args)  throws IOException
     {
    	File array1File = new File("InputFiles-new/r02000-1.txt");
     	File array2File = new File("InputFiles-new/r02000-2.txt");
     	File array3File = new File("InputFiles-new/r02000-3.txt");
     	int inputSize = 2000;
     	Scanner array1 = new Scanner(array1File);
     	Scanner array2 = new Scanner(array2File);
     	Scanner array3 = new Scanner(array3File);
 		int i=0;
     	int []a1 = new int[inputSize];
     	int []a2 = new int[inputSize];
     	int []a3 = new int[inputSize];
     	while(array1.hasNextInt() || array2.hasNextInt() || array3.hasNextInt())
     	{
     		if(array1.hasNextInt())
     		{
     			a1[i] =(array1.nextInt());
     		}
     		if(array2.hasNextInt())
     		{
     			a2[i] =(array2.nextInt());
     		}	
     		if(array3.hasNextInt())
     		{
     			a3[i] =(array3.nextInt());
     		}
     		i++;
     	}
     	Stopwatch timer = new Stopwatch();
     	int counter = Collinear.countCollinear(a1,a2,a3);
     	System.out.println("Running time for inputSize of " + inputSize + " is " + timer.elapsedTime());
     }

}

