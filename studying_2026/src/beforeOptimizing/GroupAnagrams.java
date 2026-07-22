package beforeOptimizing;

import java.util.*;

public class GroupAnagrams {

//	Given an array of strings strs, group the anagrams together. You can return the answer in any order.

//			Example 1:
//
//			Input: strs = ["eat","tea","tan","ate","nat","bat"]
//
//			Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//	Explanation:
//
//		There is no string in strs that can be rearranged to form "bat".
//		The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
//		The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
	
	
    public static List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> masterList = new ArrayList<>();
    	
    	for(int i=0; i<strs.length; i++) {
    		List<String> targetsWords = new ArrayList<>();
    		targetsWords.add(strs[i]);
        	 for(int j=i+1; j<strs.length; j++) {
        		 if(isValidAnagram(strs[i], strs[j])) {
        			 targetsWords.add(strs[j]);
        		 }
        	 }
        	 masterList.add(targetsWords);
         }
    	
    	return masterList;
    }
    
    //leverage valid anagram method, return true if yes, false if no
    public static boolean isValidAnagram(String target, String check) {
    	//where target is the original word and check is the word we're checking against
    	if(target.length()!=check.length()) {
    		return false;
    	}
    	
    	int[] counts = new int[26]; 
    	//a -- 0
    	//b -- 1
    	//c -- 2 etc
    	
    	for(int i=0; i<target.length(); i++) {
    		counts[target.charAt(i)-'a']++; //this works ONLY for all lowercase letters in english. This is something to consider when building this out
    		counts[check.charAt(i)-'a']--;
    	}
    	
    	for (int count : counts)
    		if(count !=0) {
    			return false;
    		}
    	return true;
    	   	
    }
    
    public static void main (String[] args) {
    	String[] strs = {"eat","tea","tan","ate","nat","bat"};
    	List<List<String>> result = groupAnagrams(strs);
    	
    	System.out.print(result); //want to focus on how we'd iterate through each list, maybe add this in a comment
    	
    	//SO. Issue with this solution -- once we do EAT TEA ATE (correct), it iterates and we then do TEA, ATE. Which is duplicative
    	//so we'd really want to record that we've seen this before -- HASHMAP.
    	
    	//let's think about this again
    	//we would need to add a word to the map and all anagrams associated with map
    	//ISSUE: what word is the key? Because we can't just use target, since if we search for "ate" and target is "eat", how would we know?
    	
    }
	
//      NOTE: struggled to consider edge cases:
//      Example 2:
//
//    		Input: strs = [""]
//
//    		Output: [[""]]
//
//    	Example 3:
//
//    		Input: strs = ["a"]
//
//    		Output: [["a"]]
    
    //also, needed to consider that this works for lowercase letters only. Which was in the original problem, but I just assumed instead of reading it
}
