package afterOptimizing;
import java.util.*;

	public class ValidAnagram {
		
//		Given two strings s and t, return true if the two strings are anagrams of each other, 
//		otherwise return false.
	//
//		An anagram is a string that contains the exact same characters as another string, 
//		but the order of the characters can be different

//		Input: s = "racecar", t = "carrace"
	//
//		Output: true
		
	    public static boolean isAnagram(String s, String t) {
	    	//we want to go through the strings and check to see if a letter appears and how often it appears
	    	//thinking hashmap
	    	HashMap<Character, Integer> countS = new HashMap<>();
	    	HashMap<Character, Integer> countT = new HashMap<>();
	    	
	    	if(s.length() != t.length()) {
	    		//not same length, so immediately exit
	    		return false;
	    	}
	    	
	    	//else we know it's the same length, so use either for loop 
	    	for(int i=0; i< s.length(); i++) {
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
	    	
	    	//now compare the two hashmaps
	    	for (int j=97; j<=123; j++) {
	    		char curr = (char)j;
	    		if(countS.get(curr)!=countT.get(curr)){
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	    
		
		public static void main (String[] args) {
			System.out.println(isAnagram("racecar", "carrace"));
			System.out.println(isAnagram("jar", "jam"));
			System.out.println(isAnagram("abb", "aab")); //showing as true, why?
		}

	}
