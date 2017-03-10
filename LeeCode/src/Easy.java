import java.util.*;
public class Easy {
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
	public ListNode setupListNode(int[] data)
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
	
	public void swapParis1(ListNode head)
	{
		if(head == null || head.next == null)
		{
			return;
		}
		ListNode preHead = new ListNode(-1);
		preHead.next = head;
		ListNode pre = preHead;
		ListNode first = pre.next;
		ListNode second = first.next;
		while(first != null && second != null)
		{
			pre.next = second;
			first.next = second.next;
			second.next = first;
			//move forward
			if(first.next != null)
			{
				pre = first;
				first= first.next;
				
				if(first != null)
				{
					second = first.next;
				}
				else
				{
					//there're odd nodes and we hit the end
					break;
				}
			}
			else
			{
				//there're even nodes and we hit the end
				break;
			}
		}
		System.out.println("Before assignment, head is:");
		this.printListNode(head);
		System.out.println();  
		System.out.println("preHead.nextis:");	
		this.printListNode(preHead.next);
		System.out.println();  
		System.out.println("After assignment, head is:");
		head = preHead.next;
		this.printListNode(head);
		System.out.println();  
		
	}
	
	public ListNode swapParis(ListNode head)
	{
		if(head == null || head.next == null)
		{
			return head;
		}
		ListNode preHead = new ListNode(-1);
		preHead.next = head;
		ListNode pre = preHead;
		ListNode first = pre.next;
		ListNode second = first.next;
		while(first != null && second != null)
		{
			pre.next = second;
			first.next = second.next;
			second.next = first;
			//move forward
			if(first.next != null)
			{
				pre = first;
				first= first.next;
				
				if(first != null)
				{
					second = first.next;
				}
				else
				{
					//there're odd nodes and we hit the end
					break;
				}
			}
			else
			{
				//there're even nodes and we hit the end
				break;
			}
		}

		return  preHead.next;
		
	}
	public int removeDupes(int[] nums)
	{
		if(nums.length <2)
			return nums.length;
		//at least have 2 numbers in the array
		int pre=nums[0];
		int count = 1;
		for(int i=1; i<nums.length;i++)
		{
			if(nums[i]!=pre)
			{
				//not dupe number
				nums[count++] = nums[i];
				pre = nums[i];
			}
			
		}
		
		return count;
	}
	
	public int removeAllDupes(int[] nums, int val)
	{
		//int count = 0;
		int resultLength = nums.length;
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] == val)
			{
				int swapCount = 0;
				for(int j=i+1;j<nums.length;j++)
				{
					if(nums[j] != nums[i])
					{
						//could be improved for case 3333333333333333333333333333333334
						this.swapElements(nums, i, j); 
						swapCount++;
						break;
					}
				}
				if(swapCount == 0)
				{
					//means all number from [i+1, length -1) is val
					resultLength=i; 
					break;
				}
			}
		}
		System.out.println(Arrays.toString(nums));
		System.out.println("resultLength is "+resultLength);
		return resultLength;
	}
	
	public int removeAllDupesImproved(int[] nums, int val)
	{
		//int count = 0;
		int resultLength = nums.length;
		int seat = nums.length;
		for(int i = 0; i < nums.length; i++)
		{
			if(nums[i] == val)
			{	seat = seat == nums.length?i:seat;
				//int swapCount = 0;
				for(int j=i+1;j<nums.length;j++)
				{
					//swapCount = 0;

					if(nums[j] != nums[i])
					{
						//could be improved for case 3333333333333333333333333333333334
						this.swapElements(nums, seat, j);  
						//now from nums[i+1] to nums[j] are all the same number: value
						//update the next seat number:
					    seat++; //
						//swapCount++;
						i=j;
						//j=i+1;
					}
				}
				//if(swapCount == 0 )
				//{
					//means all number from [seat, length -1) is val
					resultLength = seat;
					break;
				//}

			}
		}
		System.out.println(Arrays.toString(nums));
		System.out.println("resultLength is "+resultLength);
		return resultLength;
	}
	
	public int strStr(String hayStack, String needle)
	{
		if( hayStack == null || needle == null ||hayStack.length()<needle.length())
		{
			return -1;
		}
		//special logic
		if(needle == "")
		{
			return 0;
		}
		for(int j=0;j<hayStack.length();j++)
		{
			if( hayStack.length()-j+1 <needle.length())
			{
				return -1;
			}
			int startPosition = -1;
			int i=0;
			while(i<needle.length() && j< hayStack.length() && hayStack.charAt(j) == needle.charAt(i))
			{
				startPosition = startPosition == -1? j:startPosition;
				i++;
				j++;
			}
			//if(i == needle.length()) //we found the first match
			if(startPosition!= -1 && i == needle.length()) //we found the first match	
			{
				return startPosition;
			}
			//reset the J
			j = startPosition == -1?j :startPosition+ 1; //bugFix 1
			
			/*
			if(hayStack.charAt(j) == needle.charAt(0))
			{
				startPosition = j;
				int i=1;
				j = j+1;
				while(i<needle.length() && j< hayStack.length() && hayStack.charAt(j) == needle.charAt(i))
				{
					i++;
					j++;
				}
				if(i == needle.length()) //we found the first match
				{
					return startPosition;
				}
				//reset the J
				j = startPosition + 1; //bugFix 1
			}
			*/
		}
		return -1;
	}
	
	public static void swapElements(int[] nums,int indexA, int indexB)
	{
		int tmp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = tmp;
	}
	
	public static void main(String[] Args)
	{
		System.out.println("Test");
		Easy s = new Easy();
		/*
		ListNode head = null;
		head = s.setupListNode( new int[]{1,2,3,4});
		//head here is not passed by reference, it's by value
		s.printListNode(head);
		System.out.println();
		s.swapParis1(head);
		s.printListNode(head);
		*/
		
		//System.out.println("Count is "+ s.removeDupes(new int[]{1,1,1,1,2,2,3,3}));
		
		//s.removeAllDupesImproved(new int[]{4,5},4);
		System.out.println("the result is "+ s.strStr("abcdeffg", "fff"));
		 
	}
}
