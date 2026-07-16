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
		    if (s.length() != t.length()) {
		        return false;
		    }

		    int[] counts = new int[26];

		    for (int i = 0; i < s.length(); i++) {
		        counts[s.charAt(i) - 'a']++;
		        counts[t.charAt(i) - 'a']--;
		    }

		    for (int count : counts) {
		        if (count != 0) {
		            return false;
		        }
		    }

		    return true;
		}
		
//		Time: O(n)
//		Space: O(1)
	    
		
		public static void main (String[] args) {
			System.out.println(isAnagram("racecar", "carrace"));
			System.out.println(isAnagram("jar", "jam"));
			System.out.println(isAnagram("abb", "aab")); //showing as true, why?
		}

	}
