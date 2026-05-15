package beforeOptimizing;

import java.util.ArrayList;

public class LongestCommonPrefix {
	
	
//	Write a function to find the longest common prefix string amongst an array of strings.
//
//	If there is no common prefix, return an empty string "".
//
//	 
//
//	Example 1:
//
//	Input: strs = ["flower","flow","flight"]
//	Output: "fl"
//	Example 2:
//
//	Input: strs = ["dog","racecar","car"]
//	Output: ""
//	Explanation: There is no common prefix among the input strings.
//	 
//
//	Constraints:
//
//	1 <= strs.length <= 200
//	0 <= strs[i].length <= 200
//	strs[i] consists of only lowercase English letters if it is non-empty.
	
    public static String longestCommonPrefix(String[] strs) {
    	String word = strs[0]; //logic: take any word and check for letters in every item
    	String longest = "";
    	boolean containsChar = true;
    	for(int j=0; j<word.length();j++) {
    		String current = word.charAt(j)+"";
    		//f
    		do {
    			for(int i=0; i<strs.length; i++) {
    				if(!strs[i].contains(current)) { //want to check the longest+current
    					containsChar = false;
    				}
    			}//if out of for loop, means all strings contain current. Append to longest
    			longest+=current;
    		}while(containsChar);
    	}
    	
    }
	
	public static void main (String[] args) {
		String[] str = {"flower","flow","flight"};
		System.out.println(str);
	}
	
	

}
