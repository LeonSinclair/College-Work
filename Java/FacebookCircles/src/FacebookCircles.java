import java.util.Arrays;

/**
 * Class FacebookCircles: calculates the statistics about the friendship circles in facebook data.
 *
 * @author Leon Sinclair
 *
 * @version 01/12/15 02:03:28
 */
/*
 * simple idea is to have the public constructor then call a private constructor which creates a graph of user objects which we call
 * a circle, then adding friendships is just creating an edge between them, we'll also have to define an edge object so
 * 
 */
public class FacebookCircles {
	private int[] circles;
	private int[] size;
	private int count;
  /**
   * Constructor
   * @param numberOfFacebookUsers : the number of users in the sample data.
   * Each user will be represented with an integer id from 0 to numberOfFacebookUsers-1.
   */
  public FacebookCircles(int numberOfFacebookUsers) {
	circles = new int[numberOfFacebookUsers];
	for(int i = 0; i <numberOfFacebookUsers; i++)
	{
		circles[i]=i;
	}
	size = new int[numberOfFacebookUsers];
	for(int i = 0; i<size.length; i++)
	{
		size[i] = 1;
	}
	count = numberOfFacebookUsers;
  }
  

  /**
   * creates a friendship connection between two users, represented by their corresponding integer ids.
   * @param user1 : int id of first user
   * @param user2 : int id of second  user
   */
  public void friends( int user1, int user2 ) {

    	//union - change the two sets to be equal to each other
	//whenever we do union we change the sizes for those two ids to be equal to the sum of the two sizes
	// then we sort the array - can't do since each id is associated with a position, cannot change the order
	int user1ID = circles[user1];
	int user2ID = circles[user2];
	int newSize = size[user1ID] + size[user2ID];
	if(user1ID<user2ID)
	{
		for(int i = 0; i<circles.length; i++)
		{
			if(circles[i] == user2ID)
			{
				circles[i] = user1ID;
				size[i] = newSize;
			}
			else if(circles[i] == user1ID)
			{
				size[i] = newSize;
			}
		}
	}
	else if(user1ID>user2ID)
	{
		for(int i = 0; i<circles.length; i++)
		{
			if(circles[i] == user1ID)
			{
				circles[i] = user2ID;
				size[i] = newSize;
			}
			else if(circles[i] == user2ID)
			{
				size[i] = newSize;
			}
		}
	}
	
  }
  
  /**
   * @return the number of friend circles in the data already loaded.
   */
  public int numberOfCircles() {
	//simple implementation is to iterate through the array and have a current id variable, whenever currentID changes
	//you can increment the count of circles
	int circlesCount = 1;
	int lastCircle = 0;
	int[] sortedCircles = circles;
	Arrays.sort(sortedCircles);
	for(int i = 0; i<sortedCircles.length; i++)
	{
		if(sortedCircles[i]>lastCircle)
		{
			circlesCount++;
			lastCircle = sortedCircles[i];
		}
	}
    return circlesCount;
  }

  /**
   * @return the size of the largest circle in the data already loaded.
   */
  public int sizeOfLargestCircle() {
	  int[] sortedSizes = size;
		Arrays.sort(sortedSizes);
	    return size[count-1];
  }

  /**
   * @return the size of the median circle in the data already loaded.
   */
  public int sizeOfAverageCircle() {
	int sum = 0;
	//maybe add size[i] onto i so that it skips that much and then keeps going from there
	for(int i = 0; i<circles.length; i++)
	{
		sum+=size[i];
		i = i + size[i]-1;
	}
	int average = sum/numberOfCircles();
    return average;
  }

  /**
   * @return the size of the smallest circle in the data already loaded.
   */
  public int sizeOfSmallestCircle() {
	// return size[circles[count-1]]
	int[] sortedSizes = size;
	Arrays.sort(sortedSizes);
    return size[0];
  }


}
