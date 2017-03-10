import java.util.*; //test,test
public class Solution {
	//Given n pairs of parentheses, write a function to count all combinations of well-formed parentheses.
	public int countParenthesis(int n)
	{
		int[] result = new int[n+1];
		result[0] = 1;
		if(n>=1) result[1] = 1;
		for(int i=2;i<=n;i++)
		{
			result[i]=result[i-1];
			for(int j=0;j<=i-2;j++)
			{
				result[i] = result[i] + (result[j]*result[i-1-j]);
			}
		}
		return result[n];
	}
	
	public List<String> generateParethesis(int n)
	{
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>(n+1);
		for(int i=0;i<=n;i++)
		{
			results.add(i, new ArrayList<String>());
		}
		//result[i] stores all well-formed parentheses for i pairs
		//results.add(0, new ArrayList<String>());
		results.get(0).add("");
		if(n>=1) results.get(1).add("()");
		for(int i = 2;i<=n;i++)
		{
			for(String s : results.get(i-1))
			{
				String cur = "("+s+")";
				results.get(i).add(cur);
			}
			for(int j = 0;j<=i-2;j++)
			{
				for(String s1 : results.get(j))
					for(String s2 : results.get(i-1-j))
					{
						String cur = "("+s1+")"+s2;
						results.get(i).add(cur);
					}
			}
		}
		System.out.println("generateParethesis size is "+results.get(n).size());
		//System.out.println(results.get(n));
		return results.get(n);		
	}
	int nPair;
	public List<String> recursiveGenerateParethesis(int n)
	{
		List<String> result = new ArrayList<String>();
		nPair = n;
		recursiveGenerateParethesis("",0,result);
		System.out.println("recursiveGenerateParethesis size is "+result.size());
		return result;
	}

	private void recursiveGenerateParethesis(String cur,int k,List<String> l)
	{
		if(cur.length() == 2*nPair)
			l.add(cur);
		//still have space to position a '('
		if(2*nPair - cur.length() - k > 0)
		{
			recursiveGenerateParethesis(cur+"(",k+1,l);
		}
		//position a ')'
		if(k>0)
		{
			recursiveGenerateParethesis(cur+")",k-1,l);
		}
	}
	
	public int removeAllDupes(int[] nums, int val)
	{
		//int count = 0;
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] == val)
			{
				for(int j=i+1;j<nums.length;j++)
				{
					if(nums[j] != nums[i])
					{
						this.swapElements(nums, i, j);
						i=j;
						j=j+1;
					}
				}
			}
		}
//		System.out.println(nums);
		return 0;
	}
	public static void swapElements(int[] nums,int indexA, int indexB)
	{
		int tmp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = tmp;
	}
	public class ListNode{
		int val;
		ListNode next;
		ListNode(int x){ val = x;}
	}
	public static void printListNode(ListNode head)
	{
		while(head != null)
		{
			System.out.print(head.val+" --> ");
			head = head.next;
		}
	}
	public ListNode setupListNode( int[] data)
	{
		ListNode pre = new ListNode(-1);
		ListNode cursor = pre;
		for(int i : data)
		{
			ListNode tmp = new ListNode(i);
			cursor.next = tmp;
			cursor = tmp;
		}
		return pre.next;
	}
	
	public static void main(String[] args)
	{
		Solution s = new Solution();
		ListNode head = s.setupListNode(new int[] {1,2,3,4});
		s.printListNode(head);
		/*
		System.out.println("countParenthesis is "+s.countParenthesis(5));
		System.out.println("generateParethesis is "+s.generateParethesis(5));
		System.out.println("recursiveGenerateParethesis is "+ s.recursiveGenerateParethesis(5));
		for(int n=0;n<=10;n++)
		{
			int num = s.countParenthesis(n);
			System.out.println(String.format("num is %d, countParenthesis is %d", n, num));
			s.recursiveGenerateParethesis(n);
			//s.generateParethesis(n);
		}
		*/
	}
}
