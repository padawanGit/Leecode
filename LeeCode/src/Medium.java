import java.lang.reflect.Array;
import java.util.*;
public class Medium {
	int first = -1;
	int currentValue = 0;
	boolean foundCurrentValue = false;
	int targetValue = -1;
	public int generateNextPermutation(int[] num)
	{
		
		for(int i = 0;i<num.length;i++)
		{
			currentValue = currentValue*10 + num[i];
		}
		Arrays.sort(num);
		List<Integer> result = new ArrayList<Integer>();
		generateNums(0,num,result);
		
		int a = (targetValue==-1?first:targetValue);
		System.out.println("next permutation is "+a);
		return a;
	}

	public boolean generateNums(int used, int[] toBeUsed, List<Integer> result)
	{
		boolean allUsed = true;
		//toBeUsed is in ascending order 
		boolean found = false;
		for(int i=0;i<toBeUsed.length;i++)
		{
			if(toBeUsed[i]!= -1) // there're still unused element
			{	
				allUsed = false;
				used = used*10 + toBeUsed[i];
				int temp = toBeUsed[i];
				toBeUsed[i] = -1;
				found = generateNums(used,toBeUsed,result);
				if(found) return true;
				used = (used-temp)/10;
				toBeUsed[i] = temp;
			}
		}
		if(allUsed) 
		{
			if(targetValue == -1 && foundCurrentValue == true)
			{
				targetValue = used;
				found = true;
			}
			first = first == -1? used:first;
			if(used == currentValue)
			{
				foundCurrentValue = true;
			}
			System.out.println(used);
			result.add(used);
		}
		return false;
	}
	
}

