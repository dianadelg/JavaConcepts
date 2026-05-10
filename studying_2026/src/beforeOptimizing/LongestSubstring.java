package beforeOptimizing;

import java.util.HashSet;

public class LongestSubstring {
	
    public static int lengthOfLongestSubstring(String s) {
    	//a note about this: characters must stay next to each other AND you cannot skip around
    	//algo. For each letter n in string
    	//check if n+1....n letters form a string without any duplicate letters
    	//need to keep track of current string being built and lookup letters -- hashset -- does it exist or not
    	//pwwkew
    	String currentLongest = ""; //TIP: do not store string, just store lengths (it only asks for lengths)
    	String currentStringBuilder = ""; 
    	HashSet<Character> chars = new HashSet<>(); //O(n) at worst for space
    	for(int j=0; j< s.length(); j++) { //O(n)
	    	for(int i=j; i< s.length(); i++) {//O(n)
	    		char curr = s.charAt(i);
	    		if(chars.contains(curr)) {
	    			//means it exists in the substring, therefore, reset
	    			if(currentLongest.length() < currentStringBuilder.length()) {
	    				//set current String Builder to current Longest
	    				currentLongest=currentStringBuilder;
	    				currentStringBuilder="";
	    				chars.clear();
	    				break;
	    			}
	    		}else {
	    			//means does not contain yet
	    			chars.add(curr);
	    			currentStringBuilder+=curr;
	    		}
	    	}
    	}
    	System.out.println(currentLongest);
        return currentLongest.length();
    }
    //Time O(n). Space -- longest substring could be a string of O(n^2) at most (entire string)
	
	public static void main (String[] args) {
		//Given a string s, find the length of the longest substring without duplicate characters.
		System.out.println(lengthOfLongestSubstring("abcabc"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(lengthOfLongestSubstring("pwwkew"));
		System.out.println(lengthOfLongestSubstring("abcde")); //this only works as a weird side effect, because we should reset every j
	}

}
