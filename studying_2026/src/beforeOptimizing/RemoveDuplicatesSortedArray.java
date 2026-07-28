package beforeOptimizing;

public class RemoveDuplicatesSortedArray {
//	Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place 
//	such that each unique element appears only once. The relative order of the elements should be kept the same.
//
//	Consider the number of unique elements in nums to be k​​​​​​​​​​​​​​. After removing duplicates, return the number of unique elements k.
//
//	The first k elements of nums should contain the unique numbers in sorted order. The remaining elements beyond index 
//	k - 1 can be ignored.
	
//	Input: nums = [0,0,1,1,1,2,2,3,3,4]
//			Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
//			Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
//			It does not matter what you leave beyond the returned k (hence they are underscores).
	
    public static int removeDuplicates(int[] nums) {
    	//sorted makes me think pointers
    	//must do in place
    	
    	if(nums.length==1) {
    		return 1;
    	}
    	
    	//[0,1,1,1,2]
    	int k=0;
    	int i=1; //unique elements
    	int current, prev;
	    do {
	    	current=nums[i];
	    	prev=nums[i-1];
	    	
	    	if(current!=prev) {
	    		k++; //distinct item in the right place
	    		prev=current;
	    		current++;
	    	}else {
	    		//0 1 1 1 2
	    		while(current==prev) {
	    			current++;
	    		}
	    		//once out of here, !=
	    		nums[prev+1]=current; //prev == 1 current = 2 now.  so then 0 1 2 1 
	    		
	    		k++;
	    	}
	    }while(i<nums.length);
	    
	    return 0;
        
    }
    
    public static void main (String[] args) {
    	int[] test = {0,0,1,1,1,2,2,3,3,4};
    	System.out.println(removeDuplicates(test));
    }
    
    /*
     * wooooooooooo I'm struggling :( I think overload
     */
}
