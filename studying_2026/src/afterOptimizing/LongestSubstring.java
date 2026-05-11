package afterOptimizing;

import java.util.HashSet;

public class LongestSubstring {
	
	public static int lengthOfLongestSubstring(String s) {
		//SLIDING WINDOW
		//we want to make sure that at every moment in time, the window (L to R) is valid -- do we have any dupes
		//think of a  b  c  a
		//as       L
		//         R
		//where we move R when window is valid
		//goal is to expand R over for the longest string.
		//Check window is valid. Right now, L and R are just at a, so window is "a"
		//it passes our criteria -- no dupes. So we add to set we've already seen and longest length becomes = 1
		HashSet<Character> chars = new HashSet<Character>(); //where we will put letters we have already seen
		int longest = 0; //where we will keep the length of the longest substring. We do not need the actual substring
		//next move would be:
		//      a  b  c  a
		//      L
		//         R
		//Look at R. Ask, have we seen the letter at R before (b)? No. Add to set of now seen 
		//then ask, is window valid? Window = ab. Yes, valid. No dupes. So we increase longest = 2
		//a formula for the length of current window -- can actually be dictated by the set. But cannot be size of set because
		//sometimes it is empty. So it's actually (index of R - index of L + 1) -- look at "a" -- R=0, L=0, + 1 = 1 length
		
		//Let's say we keep doing this until
		//      a  b  c  a
		//      L
		//               R
		//We have a failure. So now, we want to move L. Slide the window
		//RULE: When valid, move R. When invalid, move L. BUT BEFORE WE MOVE L, must remove letter at L from the set
		
		//So now:
		//      a  b  c  a
		//         L
		//               R
		
		int l = 0; //for the L. You'd think we'd do the same for R....but alas. Will use this for for loop
		for(int r=0; r<s.length(); r++) { //using this -- makes it O(n). If we used a while here, not guaranteed to be O(n)
			//in here, will use a while loop -- "to make sure window is valid" == move until something is true/remove when not
			while(chars.contains(s.charAt(r))) { //while the current R letter is in the set meaning we've seen it/invalid
				//so when we've already seen this letter
				//this while loop is O(n) because AT MOST we add all of the chars from s to the set. So this would be 
				//n chars at max, making it at max O(n)
				chars.remove(s.charAt(l)); //remove char from set
				l += 1; //move L over an index. Slide the window :)
			}
			//once out of here, this is where we have a valid window
			int w = (r-l)+1; //the formula to calculate length of the current window
			longest = Math.max(w, longest); //set longest to current window length if > current longest 
			chars.add(s.charAt(r)); //add in char at R if we know it's valid
		}
		
		return longest;
		
		//Time -- O(2n) == O(n)
		//Space -- set at max is O(n) == O(n)
    }
    
	
	public static void main (String[] args) {
		//Given a string s, find the length of the longest substring without duplicate characters.
		System.out.println(lengthOfLongestSubstring("abcabc"));
		System.out.println(lengthOfLongestSubstring("bbbbb"));
		System.out.println(lengthOfLongestSubstring("pwwkew"));
		System.out.println(lengthOfLongestSubstring("abcde")); //this only works as a weird side effect, because we should reset every j
	}


}
