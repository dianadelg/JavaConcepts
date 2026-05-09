package beforeOptimizing;

import java.util.HashSet;

public class LongestSubstring {
	
    public static int lengthOfLongestSubstring(String s) {
    	//a note about this: characters must stay next to each other AND you cannot skip around
    	//algo. For each letter n in string
    	//check if n+1....n letters form a string without any duplicate letters
    	//need to keep track of current string being built and lookup letters -- hashset -- does it exist or not
    	
    	String currentLongest = "";
    	String currentStringBuilder = "";
    	HashSet<Character> chars = new HashSet<>();
    	for(int i=0; i< s.length(); i++) {
    		char curr = s.charAt(i);
    		if(chars.contains(curr)||i==s.length()-1) { //means on last letter
    			//means it exists in the substring, therefore, reset
    			if(currentLongest.length() < currentStringBuilder.length()) {
    				//set current String Builder to current Longest
    				currentLongest=currentStringBuilder;
    				currentStringBuilder="";
    				chars.clear();
    			}
    		}else {
    			//means does not contain yet
    			chars.add(curr);
    			currentStringBuilder+=curr;
    		}
    	}
    	System.out.println(currentLongest);
        return currentLongest.length();
    }
	
	public static void main (String[] args) {
		//Given a string s, find the length of the longest substring without duplicate characters.
		System.out.println(lengthOfLongestSubstring("abcabc"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(lengthOfLongestSubstring("pwwkew"));
	}

}
