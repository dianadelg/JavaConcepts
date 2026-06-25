package beforeOptimizing;

import java.util.HashSet;

public class ValidAnagram {
	
//	Given two strings s and t, return true if the two strings are anagrams of each other, 
//	otherwise return false.
//
//	An anagram is a string that contains the exact same characters as another string, 
//	but the order of the characters can be different

//	Input: s = "racecar", t = "carrace"
//
//	Output: true
	
    public static boolean isAnagram(String s, String t) {
    	//check lengths. IF mismatch, return false
    	if(s.length() != t.length()) {
    		return false;
    	}
    	
    	//else put one word in set, then iterate through other and see if exists in set
    	//O(2n) 0-- O(n) - runtime complexity
    	HashSet<Character> set = new HashSet<>(); //O(n) all letters in input - space complexity
    	for(int i=0; i<s.length(); i++){
    		set.add(s.charAt(i));
    	}
    	//out here, set exists. Now check against t
    	for(int j=0; j<t.length(); j++) {
    		if(!set.contains(t.charAt(j))) {
    			return false;
    		}
    	}
    	return true; //if it makes it out here, all letters do exist in other word
    }
    
    //AHH so this is actually WRONG
    //Hashset only tracks if it exists, NOT counts
    //so "abb" and "aab" pass because the set says, yes there's an a and yes there's a b
    //NOT how many. So this would fail
    //anagram requires frequency, not just existence
    //will need to fix in optimized solution
    
	
	public static void main (String[] args) {
		System.out.println(isAnagram("racecar", "carrace"));
		System.out.println(isAnagram("jar", "jam"));
	}

}
