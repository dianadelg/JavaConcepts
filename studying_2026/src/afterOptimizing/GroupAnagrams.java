package afterOptimizing;
import java.util.*;

public class GroupAnagrams {

//		Given an array of strings strs, group the anagrams together. You can return the answer in any order.

//				Example 1:
	//
//				Input: strs = ["eat","tea","tan","ate","nat","bat"]
	//
//				Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//		Explanation:
	//
//			There is no string in strs that can be rearranged to form "bat".
//			The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
//			The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
	        
	    public static String sortsKey(String word) {
	        // 1. Convert string to char array
	        char[] chars = word.toCharArray();
	        
	        // 2. Sort the array alphabetically
	        Arrays.sort(chars); //O(n log(n)) -- why?
	        
	        // 3. Create a new string from the sorted array
	        return new String(chars);
	    }
	    
	    public static List<List<String>> groupAnagrams(String[] strs) {
	    	
	    	//new way to think about this
	    	//get word -- we want to create a common key all of the words have
	    	//so for this, anagrams -- let's sort in alphabetical order
	    	//so ate -- aet
	    	//eat -- aet
	    	//tea -- aet
	    	
	    	//for every word. Create this common key, look in hashmap. If it exists -- Add every occurrence of the word to the value list.
	    	//if already there, do nothing
	    		//value is a list of all words that use this common key
	    	//if it doesn't exist, create a new entry in the hashmap and add the target word
	    	
	    	HashMap<String, List<String>> map = new HashMap<>(); //this will hold the above
	    	//key -- string that is the common key
	    	//value -- list of words that match that common key
	    	
	    	//first, get word from list 
	    	for(String str: strs) {
	    		
	    		String sorted = sortsKey(str); //get sorted word
	    		if (map.containsKey(sorted)){
	    			//need to add to list
	    			//check if word is in value list -- duplicate check. The problem absolutely wants duplicates
	    				map.get(sorted).add(str);	    			
	    		}else {
	    			//add to map, initialize list
	    			List<String> words = new ArrayList<>();
	    			words.add(str);
	    			map.put(sorted,words);
	    		}
	    		
	    	}
	    	
	    	//convert hashmap into a list of List<String>
	    	//or do we?
	    	//we just have to return the arrays for each key (or the values)
	    	
	    	List<List<String>> masterList = new ArrayList<>();
	    	
	    	//add each value to list
	    	for(List<String> value: map.values()) {
	    		//for each List, add to master list
	    		masterList.add(value);
	    	}	    	
	    	
	    	return masterList;
	    }
	    
	    public static void main (String[] args) {
	    	String[] strs = {"eat","tea","tan","ate","nat","bat", "ate"}; //assuming distict words. If duplicated existed, we'd just do an additional check on the map value list before inserting
	    	//A HashMap has unique keys, but its values can contain duplicates. So the above is correct
	    	System.out.println(groupAnagrams(strs)); 
	    	
	    	String [] strs2 = {}; //empty list example
	    	System.out.println(groupAnagrams(strs2)); 
	    	
	    	String [] strs3 = {"cat"}; //single word example
	    	System.out.println(groupAnagrams(strs3)); 
	    	
	    }
		
	    //Every time I get a word, calculate its shared anagram key, then store the word in the list associated with that key.
	}
