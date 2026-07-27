package beforeOptimizing;

import java.util.HashMap;

public class LongestSubstringTwoDistinct {
	
//	Given a string s, find the length of the longest substring that contains at most two 
//  distinct characters.
//
//	Example 1:
//
//	Input: s = "eceba"
//	Output: 3
//	Explanation: The substring is "ece" which its length is 3.
//	Example 2:
//
//	Input: s = "ccaabbb"
//	Output: 5
//	Explanation: The substring is "aabbb" which its length is 5.
	
//	Constraints	
//	1 <= s.length <= 104
//	s consists of English letters.
	
	//input: string
	//output is going to be the length of the longest substring (continuous)
	//criteria of not being a window: > 2 distinct characters
	//each unique character can repeat as many times as it wants
	
	public static int lengthOfLongestSubstringTwoDistinct(String s) {
		if (s.length() == 1) {
			return s.length();
		}
		
		HashMap<Character, Integer> map = new HashMap<>(); //worst case in space, each letter has a count of 1. O(n)
		 
		//when stuff in the window is valid, add to right index
		//when invalid, remove from left index
		map.put(s.charAt(0),1); 
		int length=1;
		int maxLength = 0;
		int l = 0;
		for(int r=1; r<s.length(); r++){ //runtime O(n) length of the string
			int charCount = 0;
			if(!map.containsKey(s.charAt(r))) {//runtime O(1) -- this is the advantage of using a hashmap
				charCount=1;
				map.put(s.charAt(r), charCount); //runtime O(1)
			}else {
				charCount = map.get(s.charAt(r))+1; //runtime O(1)
				map.put(s.charAt(r), charCount); //runtime O(1)
			}//added to hashmap
			 length = r-l+1; //update length
			
			//check window is valid
			//if it's valid, increase right index, add element to window, recalculate the length
			
			 //we need to count if map itself > 2 distinct chars			 
			if(map.size() > 2){ //You used if. Do NOT. Use while, shrink while window is invalid so it shrinks more than once
				//[aaa]
				//remove l from the window, increase l position
				//in this case, remove l from the window is the same as decreasing count by 1
				map.put(s.charAt(l), map.get(s.charAt(l))-1);
				if(map.get(s.charAt(l))==0) {
					//remove from map entireley
					map.remove(s.charAt(l)); //O(1)
				}
				l++;
				length = r-l+1; //update length
			}else {
				maxLength = Math.max(length, maxLength); //O(1) runtime
			}
			//O(n) loops * O(1) operations == O(n)
		}
		return maxLength;
		
		//O(n) runtime
		//O(1) space -- count is bound by alphabet size (26)
	}
	
	public static void main(String[] args) {
		String s = "ecebe";
		String s2 = "ccaabbb";
		
		System.out.println(lengthOfLongestSubstringTwoDistinct(s)); //3
		System.out.println(lengthOfLongestSubstringTwoDistinct(s2)); //5
		System.out.println(lengthOfLongestSubstringTwoDistinct("a")); //1
		System.out.println(lengthOfLongestSubstringTwoDistinct("ecec")); //4
		
		//still works, just lazy
		
	}
	
	
}
