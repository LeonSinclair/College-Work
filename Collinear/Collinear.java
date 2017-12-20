import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 03/10/16 17:10:35
 */
class Collinear
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2−y3)+x2(y3−y1)+x3(y1−y2)=0 
     * rewritten as x1(-1) + x2(2) + x3(1) = 0;
     * simplifies to -x1 + 2(x2) + x3 = 0;
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * Experimental Performance:
     * -------------------------
     *  Write the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.924
     *  2000              6.615
     *  4000              53.335
     *
     *  Assuming that the running time of your algorithm is of the form aN^b,
     *  estimate 'b' and 'a' by fitting a line to the experimental points:
     *
     *  b = 2.926
     *  a = 2^-29.298
     *
     *  What running time do you predict using your results for input size 5000? 
     *  What is the actual running time you get with such an input?
     *  What is the error in percentage?
     *
     *  Error = ( (Actual time) - (Predicted time) ) * 100 / (Predicted time)
     *
     *  Input Size N      Predicted Running Time (sec)        Actual Running Time (sec)       Error (%)
     *  ------------------------------------------------------------------------------------------------
     *  5000              100.83                                105.681                          4.81%
     * 
     *  Approximate Mathematical Performance:
     *  -------------------------
     *
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: ~(1/6)*(N^3)
     *
     *  Explanation: Focus on largest time consumer, array accesses.
     *  Inner loop runs N choose 3, so it's ~(1/6)*(N^3) array accesses, runs 3 times, (3 loops) so should be approximately ~(1/2)*(N^3) array accesses. 
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	int collinearCounter = 0;
    	for(int i=0; i<a1.length; i++)
    	{
    		for(int j=0; j<a2.length; j++)
    		{
    			for(int k=0; k<a3.length; k++)
    			{
    				if( ((-a1[i]) + (a2[j]*2) - (a3[k])) == 0)
    				{
    					collinearCounter++;
    				}
    			}
    		}
    	}
    	return collinearCounter;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * Experimental Performance:
     * -------------------------
     *  Measure the running time of the algorithm when run with the following input sizes
     *  
     *  Input Size N      Running Time (sec)
     *  ------------------------------------
     *  1000              0.153
     *  2000              0.354
     *  4000              1.319
     *  5000              2.138
     *
     *
     *  Compare Implementations:
     *  ------------------------
     *  Show the speedup achieved by this method, using the times you got from your experiments.
     *
     *  Input Size N      Speedup = (time of countCollinear)/(time of countCollinearFast)
     *  ---------------------------------------------------------------------------------
     *  1000              6.04
     *  2000              18.68
     *  4000              40.46
     *  5000              49.43
     *
     *
     *  Approximate Mathematical Performance:
     *  -------------------------------------
     *
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: ~(N^2)*(log N)
     *
     *  Explanation: Sorts N numbers with insertion sort, then for each pair of numbers do a binary search for the final value. (These are explained in their parts)
     *  Binary search takes log N time, insertion sort worst case is N^2 time,  time for array accesses of two for loops is N^2. So approximates to ~(N^2)*(log N)
     * 
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
      sort(a1);
      sort(a2);
      sort(a3);
      int collinearCounter=0;
      for(int i=0; i<a1.length; i++)
      {
    	  for(int j=0; j<a2.length; j++)
    	  {
    		  int x = (-a1[i] + 2*(a2[j]));
    		  if(binarySearch(a3, x))
    		  {
    			  collinearCounter++;
    		  }
    	  }
      }
      return collinearCounter;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: ~N^2
     *
     *  Explanation: Assume we have to swap roughly every second value on average. Iterate through all the values and then swap N/2 times (roughly). Gives us a cost of (N^2)/2 on average, however
     *  in tilde notation we ignore the /2 so end up with a runtime of ~N^2
     *
     */
    static void sort(int[] a)
    {
    	int key;
    	for(int i=1; i<a.length; i++)
    	{
    		key = a[i];
    		for(int j=i-1; j>=0; j--)
    		{
    			if(key<a[j])
    			{
    				a[j+1] = a[j];
    				a[j] = key;
    			}
    		}
    	}
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     * Approximate Mathematical Performance:
     * -------------------------------------
     *  Using an appropriate cost model, give the performance of your algorithm.
     *  Explain your answer.
     *
     *  Performance: log(N)
     *
     *  Explanation: The calculation of how many times you have to access a subarray is a recurrence function of T(N), when you're dividing by 2 each time the function eventually sums to log(N)
     *
     */
    static boolean binarySearch(int[] a, int x)
    {	
    	int key = x;
    	int low = 0;
    	int high = a.length-1;
    	while(low<=high)
    	{
    		int mid = (low + high)/2;
    		if(key<a[mid])	
    		{
    			high = mid - 1;
    		}
    		else if(key>a[mid])	
    		{
    			low = mid + 1;
    		}
    		else //if (key==a[mid])
    		{
    			return true;
    		}	
    	}
      return false;
    }

}
