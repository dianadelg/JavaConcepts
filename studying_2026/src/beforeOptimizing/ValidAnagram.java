package beforeOptimizing;

import java.util.HashMap;
//import java.util.HashSet;

public class ValidAnagram {
	
//	Given two strings s and t, return true if the two strings are anagrams of each other, 
//	otherwise return false.
//
//	An anagram is a string that contains the exact same characters as another string, 
//	but the order of the characters can be different

//	Input: s = "racecar", t = "carrace"
//
//	Output: true

	//retried solution with new logic, kept old one and notes for self awareness
	public static boolean isAnagram(String s, String t) {
    	//we want to go through the strings and check to see if a letter appears and how often it appears
    	//thinking hashmap
    	HashMap<Character, Integer> countS = new HashMap<>(); //at most, O(n) letters size (all letters in here)
    	HashMap<Character, Integer> countT = new HashMap<>(); //O(n) size
    	
    	if(s.length() != t.length()) { //O(n) runtime
    		//not same length, so immediately exit
    		return false;
    	}
    	
    	//else we know it's the same length, so use either for loop 
    	for(int i=0; i< s.length(); i++) { //O(n) runtime
    		//check letter exists in map
    		if(countS.containsKey(s.charAt(i))) {
    			int count = countS.get(s.charAt(i))+1;
    			countS.put(s.charAt(i), count);
    		}else {
    			countS.put(s.charAt(i), 1);
    		}
    		
    		if(countT.containsKey(t.charAt(i))) {
    			int count = countT.get(t.charAt(i))+1;
    			countT.put(t.charAt(i), count);
    		}else {
    			countT.put(t.charAt(i), 1);
    		}
    	}

    	//can compare hashmaps directly, no need to loop
    	return countT.equals(countS); //O(n) runtime, technically O(k) where k is the number of distinct keys and k is always <=n letters
//    	//now compare the two hashmaps
//    	for (int j=97; j<123; j++) {
//    		char curr = (char)j;
//    		if(countS.get(curr)!=countT.get(curr)){
//    			return false;
//    		}
//    	}
//    	return true;
    }
    
	//runtime: O(n)
	//space: O(n)
	/*
	 * NOTE: The constraints say:
		lowercase English letters
		That means there are only 26 possible keys.
		So each map can never have more than 26 entries.
		Space = O(26) = O(1)
		So for LeetCode's exact constraints, you could argue:
		
		Time: O(n)
		Space: O(1)
		
		because the alphabet is fixed.
	 */
	
	public static void main (String[] args) {
		System.out.println(isAnagram("racecar", "carrace"));
		System.out.println(isAnagram("jar", "jam"));
		System.out.println(isAnagram("abb", "aab")); //showing as false now
	}
	

	
//OLD ALGO before understanding what an Anagram is :)	
//    public static boolean isAnagram(String s, String t) {
//    	//check lengths. IF mismatch, return false
//    	if(s.length() != t.length()) {
//    		return false;
//    	}
//    	
//    	//else put one word in set, then iterate through other and see if exists in set
//    	//O(2n) 0-- O(n) - runtime complexity
//    	HashSet<Character> set = new HashSet<>(); //O(n) all letters in input - space complexity
//    	for(int i=0; i<s.length(); i++){
//    		set.add(s.charAt(i));
//    	}
//    	//out here, set exists. Now check against t
//    	for(int j=0; j<t.length(); j++) {
//    		if(!set.contains(t.charAt(j))) {
//    			return false;
//    		}
//    	}
//    	return true; //if it makes it out here, all letters do exist in other word
//    }
    
    //AHH so this is actually WRONG
    //Hashset only tracks if it exists, NOT counts
    //so "abb" and "aab" pass because the set says, yes there's an a and yes there's a b
    //NOT how many. So this would fail
    //anagram requires frequency, not just existence
    //will need to fix in optimized solution

}
