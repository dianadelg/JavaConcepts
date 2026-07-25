package beforeOptimizing;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElement {
	
//	Given an integer array nums and an integer k, return the kth largest element in the array.
//
//	Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//	Can you solve it without sorting?
	
    public static int findKthLargest(int[] nums, int k) {
        //mind immediately goes to priority queue
    	
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //space O(n)

       //add each number from nums
        for(int num: nums) { //O(n)
        	maxHeap.add(num);//O(log n) --> so this happens n × log n = O(n log n)
        }

        for(int i=0; i<k-1; i++) { //O(k-1) or O(k) which is always less than n, based on constraints
            maxHeap.poll(); //  (O(log n)) runtime --> so this happens k × log n = O(k log n)
        }
        
        return maxHeap.peek(); //O(1)
        
        //space: O(n)
        //runtime: O(nlogn + klogn) = O(n log n)
    }
    
    public static void main (String [] args) {
    	int [] nums = {3,2,1,5,6,4};
    	int k = 6; //check last element, should be 1
    	
    	System.out.println(findKthLargest(nums, k));
    	
    	int [] nums2 = {3,2,3,1,2,4,5,5,6};
    	int k2 = 4; //4
    	
    	System.out.println(findKthLargest(nums2, k2));
    	
    	//can there be negatives, yes
    	
    	int [] nums3 = {-3,-2,-3,-1,-2,-4,-5,-5,-6};
    	int k3 = 4; //-3
    	
    	System.out.println(findKthLargest(nums3, k3));
    	
    	
    	
    }

}
