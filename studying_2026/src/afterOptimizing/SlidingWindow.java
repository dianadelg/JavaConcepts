package afterOptimizing;

import java.util.HashSet;

public class SlidingWindow {

    //Longest Substring Without Repeating Characters    
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        
        int l=0;
        int longestSubstring=0;
        int length=0;
        
        //[a]bca
        
        HashSet<Character> set = new HashSet<>();
        
        for(int r=0; r<s.length(); r++){ //I got this right
        	
            
        	while(set.contains(s.charAt(r))) {
            	//remove char at left from set. NOT R. Algo is remove from the left
            	set.remove(s.charAt(l));
            	//shrink window from left
            	l++;
            }
        	
        	set.add(s.charAt(r));
        	length = r-l+1;
        	longestSubstring=Math.max(length, longestSubstring);
            
        }
        return longestSubstring;
        }
	
}
