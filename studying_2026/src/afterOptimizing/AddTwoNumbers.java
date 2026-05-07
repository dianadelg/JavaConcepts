
package afterOptimizing;
import shared.ListNode;

public class AddTwoNumbers {
		
		public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			
			// thinking: use pointers to iterate, carry over the one
			ListNode ptr1 = l1;
			ListNode ptr2 = l2;
			int carryOver = 0;
			int currentDigit = 0;
			ListNode solutionPtr = null; //where solution will be stored
			
			while(ptr1 != null && ptr2 != null) {
				//if ptr1/2.next -- null -- need to bring down the digits remaining from the longer number
				//work on current number 
				currentDigit = ptr1.val + ptr2.val+carryOver;
				carryOver = 0;
				if(currentDigit>=10) {
					//means we have to carry over
					carryOver = currentDigit/10;
					currentDigit = currentDigit%10;
				}
				//create new Node
				ListNode solution = new ListNode(currentDigit, solutionPtr);
				solutionPtr = solution;
				
				ptr1=ptr1.next;
				ptr2=ptr2.next;
			}
			
			return solutionPtr;
		}
		
		/*
		 *  Input: l1 = [2,4,3], l2 = [5,6,4]
			Output: [7,0,8]
			Explanation: 342 + 465 = 807.
		 */
		
		public static void main(String[] args) {
			ListNode number1 = new ListNode(3, null);
			ListNode number2 = new ListNode(4, number1);
			ListNode number6 = new ListNode(8, number2);
			ListNode list1 = new ListNode(2, number6);
			traverseList(list1);
			System.out.println();
			ListNode number4 = new ListNode(4, null);
			ListNode number5 = new ListNode(6, number4);
			ListNode list2 = new ListNode(5, number5);
			traverseList(list2);
			System.out.println();
			traverseList(addTwoNumbers(list1, list2));
			
			//not robust because converting the linked lists into integers can overflow
			//so won't work for big numbers
			//better approach - add digit-by-digit with a carry (which I started but then got frustrated lol)
		}
		
		public static void traverseList(ListNode head) {
			ListNode ptr= head;
			do{
				System.out.print(ptr.val);
				ptr=ptr.next;
			}while(ptr != null);
		}
		

}

