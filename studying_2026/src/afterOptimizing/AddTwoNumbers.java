
package afterOptimizing;
import shared.ListNode;

public class AddTwoNumbers {
		
		public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			
			// thinking: use pointers to iterate, carry over the one
			ListNode ptr1 = l1;
			ListNode ptr2 = l2;
			int carryOver = 0;
			int currentDigit = 0;
			ListNode solution = new ListNode(0,null); // create a dummy node, fake starter node so insertion logic is always identical
			//instead of having to handle a special FIRST NODE case hehe
			ListNode solutionPtr = solution; 
			//the above is declaring vars, time and space O(1)
			
			while(ptr1 != null || ptr2 != null || carryOver != 0) { //runtime O(max(n,m)) -- loop runs once per digit/node in the 
				// longer list, plus possibly one extra time if there’s a final carry. So technically like O(max(n,m)+1)
				//if ptr1/2.next -- null -- need to bring down the digits remaining from the longer number
				//work on current number 
				currentDigit = carryOver; // O(1) run/space

				if(ptr1!=null) {
					currentDigit += ptr1.val;
					ptr1 = ptr1.next; // O(1) run/space for whole block
				}
				
				if(ptr2!=null) {
					currentDigit += ptr2.val;
					ptr2 = ptr2.next; // O(1) run/space for whole block
				}
				
				//for digits >=10
				carryOver = currentDigit/10;
				currentDigit = currentDigit%10;
				
				//create new Node
				solutionPtr.next = new ListNode(currentDigit, null); // O(1) run/space in this loop, but overall O(max(n,m)+1) space
				//think of this like the number k of digits when we add. Which at max is greater than the biggest digit length + 1
				solutionPtr = solutionPtr.next;
			}
				
			//Runtime O max(n,m), space O(k) where k is the number of digits which is also max(n,m)+1	
			return solution.next; //return this because first node is a dummy node with 0 in it -- O(1)
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
			ListNode list2 = new ListNode(5,number5);
			traverseList(list2);
			System.out.println();
			traverseList(addTwoNumbers(list1, list2));
		}
		
		public static void traverseList(ListNode head) {
			//Assuming head is not null. Need to remember to do null head checks!!!
			ListNode ptr= head;
			do{
				System.out.print(ptr.val);
				ptr=ptr.next;
			}while(ptr != null);
		}
		
		
		/*
		 * LINKED LIST PATTERNS / THINGS TO REMEMBER
		 *
		 * 1. NULL CHECKS
		 * -------------------------------------------------
		 * Always check if a pointer is null BEFORE accessing:
		 *
		 *     ptr.next
		 *     ptr.val
		 *
		 * Otherwise -> NullPointerException
		 *
		 * Common safe pattern:
		 *
		 *     if(ptr != null) {
		 *         ptr = ptr.next;
		 *     }
		 *
		 * -------------------------------------------------
		 *
		 * 2. DUMMY NODE PATTERN
		 * -------------------------------------------------
		 * Dummy nodes simplify linked list insertion logic.
		 *
		 * Instead of special casing:
		 *
		 *     if(head == null)
		 *
		 * for the FIRST insertion,
		 * use a fake starter node:
		 *
		 *     ListNode dummy = new ListNode(0);
		 *
		 * Then insertion logic is ALWAYS:
		 *
		 *     current.next = newNode;
		 *     current = current.next;
		 *
		 * At the end:
		 *
		 *     return dummy.next;
		 *
		 * because dummy itself is fake and not part of the answer.
		 *
		 * Pattern shows up CONSTANTLY in:
		 * - merging lists
		 * - building output lists
		 * - removing nodes
		 * - partitioning lists
		 * - reversing portions of lists
		 *
		 * -------------------------------------------------
		 *
		 * 3. LINKED LIST BUILDING
		 * -------------------------------------------------
		 * There are TWO common patterns:
		 *
		 * A. PREPEND (add to front)
		 *
		 *     newNode.next = head;
		 *     head = newNode;
		 *
		 * This reverses order.
		 *
		 * B. APPEND (add to end)
		 *
		 *     tail.next = newNode;
		 *     tail = tail.next;
		 *
		 * Keeps original order.
		 *
		 * I accidentally reversed the answer initially
		 * because I was prepending instead of appending.
		 *
		 * -------------------------------------------------
		 *
		 * 4. LOOP CONDITIONS
		 * -------------------------------------------------
		 * For linked list math problems:
		 *
		 *     while(ptr1 != null || ptr2 != null || carry != 0)
		 *
		 * is VERY common.
		 *
		 * This handles:
		 * - different length lists
		 * - leftover carry
		 * - final digit creation
		 *
		 * -------------------------------------------------
		 *
		 * 5. CARRY PATTERN
		 * -------------------------------------------------
		 * Common math pattern:
		 *
		 *     current = val1 + val2 + carry
		 *     carry = current / 10
		 *     digit = current % 10
		 *
		 * Integer division extracts carry.
		 * Modulus extracts current digit.
		 *
		 * -------------------------------------------------
		 *
		 * 6. BIG-O PATTERN
		 * -------------------------------------------------
		 * Traversing linked lists once:
		 *
		 *     O(n)
		 *
		 * Traversing two lists together:
		 *
		 *     O(max(n,m))
		 *
		 * Creating output nodes:
		 *
		 *     O(max(n,m)) space
		 *
		 * Pointer variables themselves are O(1) space.
		 *
		 * -------------------------------------------------
		 *
		 * 7. INTERVIEW PATTERN RECOGNITION
		 * -------------------------------------------------
		 * If I see:
		 * - linked list output construction
		 * - repeated insertion
		 * - awkward first-node logic
		 *
		 * THINK:
		 *
		 *     "Should I use a dummy node?"
		 *
		 * If I see:
		 * - nested loops
		 * - repeated searching
		 *
		 * THINK:
		 *
		 *     "Can a HashMap turn this into O(n)?"
		 *
		 */
		

}

