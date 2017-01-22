import java.util.Arrays;


public class CheckTriangle
{


    public static final int ACUTE_TRI = 2;
    public static final int RIGHT_ANGLED_TRI = 1;
    public static final int OBTUSE_TRI = 3;
    public static final double TINY_DIFF = 0.0001;
    public static final int NOT_TRI = 0;

    public static boolean form_triangle(double[] ls)
    {
    	//TODO
    	//2*max(a,b,c) < sum(a, b, c)
    	//LHS < RHS
    	//if true then is a properly formed triangle
       double LHS = 2*max(ls);
       double RHS = sum(ls);
       if(LHS<RHS) return true;
       return false;
    }
    
    public static double max (double [] sides)
    {
    	
    	//finds the maximum number in an array
    	double max = sides[0];
    	for(int i = 1; i<sides.length; i++)
    	{
    		if(max<sides[i])
    		{
    			max = sides[i];
    		}
    	}
    	return max;
    }
    
    public static double sum (double[] sides)
    {
    	double sum = sides[0];
    	for(int i = 1; i<sides.length;i++)
    	{
    		sum += sides[i];
    	}
    	return sum;
    }

    public static int kind_triangle(double[] ls)
    {
    	//c^2 = a^2 + b^2
    	//if(c^2 -(a^2 + b^2))==0 - right angled
    	//if(c^2 -(a^2 + b^2))<0 - acute
    	//if(c^2 -(a^2 + b^2))>0 - obtuse
    	if(!form_triangle(ls)) return NOT_TRI;
    	Arrays.sort(ls);
    	double a = ls[0]; // sort in ascending order and then load into variables
    	double b = ls[1];
    	double c = ls[2];
    	double aSqrd = Math.pow(a, 2);
    	double bSqrd = Math.pow(b, 2);
    	double cSqrd = Math.pow(c, 2);

    	double answer = cSqrd - aSqrd - bSqrd;
    	if(answer<0) return ACUTE_TRI;
    	else if(answer==0) return RIGHT_ANGLED_TRI;
    	else return OBTUSE_TRI;
    }



    //returns the minimum element of arr
    public static double min(double[] sides)
    {
    	double min = sides[0];
    	for(int i = 1; i<sides.length; i++)
    	{
    		if(min>sides[i])
    		{
    			min = sides[i];
    		}
    	}
    	return min;
    }
}
