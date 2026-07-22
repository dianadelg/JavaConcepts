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
	        char[] chars = word.toCharArray(); //If the current word has k characters, Java must copy all k characters into a new array. O(k) space and time
	        
	        // 2. Sort the array alphabetically
	        Arrays.sort(chars); //Time: O(k log k), Efficient comparison-based sorting repeatedly divides or organizes the data into smaller portions. There are roughly log k levels of work, with up to k elements processed at each level
	        
	        // 3. Create a new string from the sorted array
	        return new String(chars); //Java creates a new string from the sorted characters. It must process/copy those k characters. Space and time: O(k)
	        
	        //So whole helper method:
	        /*
	         * Time: O(k) + O(k log k) + O(k) --> O(2k + klog(k)) == O(k log k)
	         * Space: O(k) + O(1) + O(k)
	         */
	    }
	    
	    //Every time I get a word, calculate its shared anagram key, then store the word in the list associated with that key.

	    public static List<List<String>> groupAnagrams(String[] strs) {
	    	
	    	//new way to think about this
	    	//get word -- we want to create a common key all of the words have
	    	//so for this, anagrams -- let's sort in alphabetical order
	    	//so ate -- aet
	    	//eat -- aet
	    	//tea -- aet
	    	
	    	//for every word. Create this common key, look in hashmap. If it exists -- Add every occurrence of the word to the value list.
	    	// If the key already exists, add the current word to its existing group.
	    		//value is a list of all words that use this common key
	    	//if it doesn't exist, create a new entry in the hashmap and add the target word
	    	
	    	HashMap<String, List<String>> map = new HashMap<>(); //this will hold the above
	    	//Time: O(1), Immediate space: O(1) for empty map
	    	//key -- string that is the common key
	    	//value -- list of words that match that common key
	    	
	    	//first, get word from list 
	    	for(String str: strs) { //Runtime: O(n) for each word in list
	    		
	    		String sorted = sortsKey(str); //get sorted word
	    		/*For word up to k length...
	    		 * Time: O(k log k)
				   Temporary/new space: O(k) for new word 
				   Final Time: O(n × k log k)
				   in the worst case where every word has a different signature.
				   
	    		 */
	    		if (map.containsKey(sorted)){
	    			/* hashmap lookup
	    			 * Average time: O(1)
					   Worst case: O(n)
	    			 */
	    			//need to add to list
	    			//check if word is in value list -- add. The problem absolutely wants duplicates
	    			// Duplicate input words should be added again because the output preserves every occurrence.
	    				map.get(sorted).add(str);//Get and add O(1)  			
	    		}else {
	    			//add to map, initialize list
	    			List<String> words = new ArrayList<>();
	    			/*
	    			 * Creating an empty list:
	    			 * Time: O(1)
					   Space: O(1)
	    			 */
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
	    	String[] strs = {"eat","tea","tan","ate","nat","bat", "ate"}; //assuming distict words. 
	    	
	    	//A HashMap has unique keys, but its values can contain duplicates. So the above is correct
	    	System.out.println(groupAnagrams(strs)); 
	    	
	    	String [] strs2 = {}; //empty list example
	    	System.out.println(groupAnagrams(strs2)); 
	    	
	    	String [] strs3 = {"cat"}; //single word example
	    	System.out.println(groupAnagrams(strs3)); 
	    	
	    }
	    
	    /*
	     * Let:
	     * n = number of strings
	     * k = maximum length of a string
	     *
	     * Creating a sorted key for one word:
	     * - Convert to char[]: O(k)
	     * - Sort characters: O(k log k)
	     * - Create sorted String: O(k)
	     * - Total: O(k log k)
	     *
	     * We create one sorted key for each of the n words:
	     *
	     * Time: O(n * k log k)
	     *
	     * The HashMap stores sorted keys and groups containing all input words:
	     *
	     * Space: O(n * k)
	     *
	     * HashMap containsKey(), get(), and put() are O(1) average.
	     * ArrayList.add() is O(1) amortized.
	     */
		

	    /*
	     * GROUP ANAGRAMS PATTERNS / THINGS TO REMEMBER
	     *
	     * 1. BRUTE FORCE PATTERN
	     * -------------------------------------------------
	     * A brute-force solution could compare every word
	     * against every other word using an anagram check.
	     *
	     * Example idea:
	     *
	     *     for(int i = 0; i < strs.length; i++) {
	     *         for(int j = i + 1; j < strs.length; j++) {
	     *             if(isValidAnagram(strs[i], strs[j])) {
	     *                 // group them together
	     *             }
	     *         }
	     *     }
	     *
	     * This becomes difficult because:
	     *
	     *     - Every pair may be compared.
	     *     - The same group may be created multiple times.
	     *     - We need extra logic to track words already grouped.
	     *
	     * If the anagram check takes O(k), then:
	     *
	     *     Time: O(n^2 * k)
	     *
	     * where:
	     *
	     *     n = number of strings
	     *     k = maximum string length
	     *
	     * This works conceptually, but it is unnecessarily complicated
	     * and slower than grouping by a shared key.
	     *
	     * -------------------------------------------------
	     *
	     * 2. GROUP-BY-SIGNATURE PATTERN
	     * -------------------------------------------------
	     * Instead of comparing every word with every other word,
	     * calculate a common signature for each word.
	     *
	     * Anagrams produce the same signature.
	     *
	     * Example:
	     *
	     *     "eat" -> "aet"
	     *     "tea" -> "aet"
	     *     "ate" -> "aet"
	     *
	     *     "tan" -> "ant"
	     *     "nat" -> "ant"
	     *
	     *     "bat" -> "abt"
	     *
	     * Store the groups in:
	     *
	     *     HashMap<String, List<String>>
	     *
	     * The map represents:
	     *
	     *     signature -> all words with that signature
	     *
	     * Example:
	     *
	     *     "aet" -> ["eat", "tea", "ate"]
	     *     "ant" -> ["tan", "nat"]
	     *     "abt" -> ["bat"]
	     *
	     * This is called:
	     *
	     *     grouping by a canonical representation
	     *
	     * or:
	     *
	     *     grouping by a signature
	     *
	     * -------------------------------------------------
	     *
	     * 3. THE HASHMAP KEY MUST BE SHARED
	     * -------------------------------------------------
	     * Do not use the original word as the key.
	     *
	     * Incorrect:
	     *
	     *     "eat" -> [...]
	     *     "tea" -> [...]
	     *
	     * Those are different keys, even though the words are anagrams.
	     *
	     * Instead, transform each word into something all anagrams share:
	     *
	     *     String sorted = sortsKey(str);
	     *
	     * Then use:
	     *
	     *     sorted -> group of original words
	     *
	     * The key is used only for grouping.
	     * The original words belong in the value list.
	     *
	     * -------------------------------------------------
	     *
	     * 4. BUILDING THE SORTED SIGNATURE
	     * -------------------------------------------------
	     * For one word:
	     *
	     *     char[] chars = word.toCharArray();
	     *     Arrays.sort(chars);
	     *     return new String(chars);
	     *
	     * Step 1:
	     *
	     *     word.toCharArray()
	     *
	     * Copies all k characters into a new array.
	     *
	     *     Time: O(k)
	     *     Space: O(k)
	     *
	     * Step 2:
	     *
	     *     Arrays.sort(chars)
	     *
	     * Sorts the k characters.
	     *
	     *     Time: O(k log k)
	     *
	     * For a primitive char[] array, the sorting algorithm works
	     * mostly in place. Its exact auxiliary space depends on the
	     * Java implementation, but the helper method already uses O(k)
	     * space because of the char array.
	     *
	     * Step 3:
	     *
	     *     new String(chars)
	     *
	     * Creates the sorted String key.
	     *
	     *     Time: O(k)
	     *     Space: O(k)
	     *
	     * The dominant operation is sorting:
	     *
	     *     O(k log k)
	     *
	     * Therefore:
	     *
	     *     sortsKey(word)
	     *
	     *     Time: O(k log k)
	     *     Space: O(k)
	     *
	     * -------------------------------------------------
	     *
	     * 5. HASHMAP INSERTION PATTERN
	     * -------------------------------------------------
	     * For every word:
	     *
	     *     1. Calculate its signature.
	     *     2. Check whether the signature already exists.
	     *     3. Add the original word to that signature's list.
	     *
	     * Example:
	     *
	     *     String sorted = sortsKey(str);
	     *
	     *     if(map.containsKey(sorted)) {
	     *         map.get(sorted).add(str);
	     *     } else {
	     *         List<String> words = new ArrayList<>();
	     *         words.add(str);
	     *         map.put(sorted, words);
	     *     }
	     *
	     * If the key exists:
	     *
	     *     retrieve the existing list and add the word
	     *
	     * If the key does not exist:
	     *
	     *     create a new list
	     *     add the word
	     *     store the new key/list pair
	     *
	     * -------------------------------------------------
	     *
	     * 6. PUT() REPLACES — IT DOES NOT APPEND
	     * -------------------------------------------------
	     * This is incorrect:
	     *
	     *     map.put(sorted, new ArrayList<>());
	     *
	     * on every iteration.
	     *
	     * HashMap.put() does not add to an existing list.
	     * It replaces the value currently stored at that key.
	     *
	     * Example:
	     *
	     *     "aet" -> ["eat"]
	     *
	     * Calling:
	     *
	     *     map.put("aet", newListContainingTea);
	     *
	     * would replace the old list:
	     *
	     *     "aet" -> ["tea"]
	     *
	     * It would not become:
	     *
	     *     "aet" -> ["eat", "tea"]
	     *
	     * To append, retrieve the existing list:
	     *
	     *     map.get(sorted).add(str);
	     *
	     * -------------------------------------------------
	     *
	     * 7. DUPLICATES SHOULD BE PRESERVED
	     * -------------------------------------------------
	     * A HashMap has unique keys, but the ArrayList values
	     * may contain duplicate words.
	     *
	     * Example input:
	     *
	     *     ["eat", "tea", "eat"]
	     *
	     * Correct group:
	     *
	     *     ["eat", "tea", "eat"]
	     *
	     * Do not check:
	     *
	     *     if(!map.get(sorted).contains(str))
	     *
	     * That would incorrectly remove duplicate input occurrences.
	     *
	     * Every input word should appear exactly once in the output
	     * for each time it appeared in the input.
	     *
	     * -------------------------------------------------
	     *
	     * 8. MAP.VALUES() RETURNS THE GROUPS
	     * -------------------------------------------------
	     * At the end, the HashMap may look like:
	     *
	     *     "aet" -> ["eat", "tea", "ate"]
	     *     "ant" -> ["tan", "nat"]
	     *     "abt" -> ["bat"]
	     *
	     * The output does not need the keys.
	     * It only needs the grouped lists.
	     *
	     * Therefore use:
	     *
	     *     map.values()
	     *
	     * Important:
	     *
	     *     map.values()
	     *
	     * returns:
	     *
	     *     Collection<List<String>>
	     *
	     * It does not directly return:
	     *
	     *     List<List<String>>
	     *
	     * One option:
	     *
	     *     List<List<String>> result = new ArrayList<>();
	     *
	     *     for(List<String> group : map.values()) {
	     *         result.add(group);
	     *     }
	     *
	     * Shorter equivalent:
	     *
	     *     return new ArrayList<>(map.values());
	     *
	     * -------------------------------------------------
	     *
	     * 9. KEYSET VS VALUES VS ENTRYSET
	     * -------------------------------------------------
	     * Use:
	     *
	     *     map.keySet()
	     *
	     * when only the keys are needed.
	     *
	     * Use:
	     *
	     *     map.values()
	     *
	     * when only the values are needed.
	     *
	     * Use:
	     *
	     *     map.entrySet()
	     *
	     * when both the key and value are needed.
	     *
	     * Group Anagrams only needs the value lists,
	     * so map.values() is the correct choice.
	     *
	     * -------------------------------------------------
	     *
	     * 10. BIG-O VARIABLES
	     * -------------------------------------------------
	     * Define:
	     *
	     *     n = number of strings
	     *     k = maximum length of one string
	     *
	     * The outer loop runs once for each string:
	     *
	     *     for(String str : strs)
	     *
	     *     n iterations
	     *
	     * The enhanced for-loop itself uses:
	     *
	     *     Space: O(1)
	     *
	     * The variable str is only one reference that points
	     * to a different string on each iteration.
	     *
	     * A loop running n times affects time, but it does not
	     * automatically use O(n) space.
	     *
	     * -------------------------------------------------
	     *
	     * 11. TIME COMPLEXITY
	     * -------------------------------------------------
	     * For each of the n strings, we create a sorted signature.
	     *
	     * Signature creation:
	     *
	     *     O(k log k)
	     *
	     * Done n times:
	     *
	     *     n * O(k log k)
	     *
	     * HashMap operations are average O(1):
	     *
	     *     containsKey()
	     *     get()
	     *     put()
	     *
	     * ArrayList.add() is O(1) amortized.
	     *
	     * Building the final outer list takes O(g), where:
	     *
	     *     g = number of anagram groups
	     *
	     * Since g <= n, this does not dominate the sorting work.
	     *
	     * Final:
	     *
	     *     Time: O(n * k log k)
	     *
	     * -------------------------------------------------
	     *
	     * 12. SPACE COMPLEXITY
	     * -------------------------------------------------
	     * The HashMap stores:
	     *
	     *     - up to n distinct sorted keys
	     *     - lists containing references to all n input words
	     *     - up to n group/list objects
	     *
	     * Each sorted key may contain up to k characters.
	     *
	     * In the worst case, every word has a different signature:
	     *
	     *     O(n * k)
	     *
	     * The helper also creates a temporary char array:
	     *
	     *     O(k)
	     *
	     * Only one temporary char array is needed at a time,
	     * so it does not become O(n * k) by itself.
	     *
	     * Final:
	     *
	     *     Space: O(n * k)
	     *
	     * -------------------------------------------------
	     *
	     * 13. TEMPORARY ALLOCATION VS RETAINED MEMORY
	     * -------------------------------------------------
	     * A new sorted String is created for every word.
	     *
	     * However, not every sorted String remains stored.
	     *
	     * Example:
	     *
	     *     "eat" -> creates "aet"
	     *     "tea" -> creates another "aet"
	     *     "ate" -> creates another "aet"
	     *
	     * Only one equal "aet" key remains in the HashMap.
	     *
	     * The other temporary equal String objects are no longer
	     * needed after their lookup and may be garbage collected.
	     *
	     * This is why the number of retained keys depends on the
	     * number of distinct signatures.
	     *
	     * Still, worst-case space is O(n * k), because every word
	     * could have a unique signature.
	     *
	     * -------------------------------------------------
	     *
	     * 14. WHY THE SORTING APPROACH IS OPTIMIZED
	     * -------------------------------------------------
	     * Compared with pairwise comparison:
	     *
	     *     Brute force: O(n^2 * k)
	     *
	     * Sorting-signature approach:
	     *
	     *     O(n * k log k)
	     *
	     * Instead of repeatedly comparing words against each other,
	     * each word is transformed once and placed into its group.
	     *
	     * This is the standard clean interview solution.
	     *
	     * -------------------------------------------------
	     *
	     * 15. POSSIBLE FURTHER OPTIMIZATION
	     * -------------------------------------------------
	     * If the strings contain only lowercase English letters,
	     * a 26-element frequency array can be used as the signature.
	     *
	     * Example idea:
	     *
	     *     int[] counts = new int[26];
	     *
	     *     for(char c : str.toCharArray()) {
	     *         counts[c - 'a']++;
	     *     }
	     *
	     * This avoids sorting.
	     *
	     * Frequency-signature runtime:
	     *
	     *     Time: O(n * k)
	     *
	     * Sorting-signature runtime:
	     *
	     *     Time: O(n * k log k)
	     *
	     * The sorting solution is often easier to write and explain.
	     * The frequency solution is faster under the lowercase-letter
	     * constraint.
	     *
	     * -------------------------------------------------
	     *
	     * 16. HELPER METHOD PATTERN
	     * -------------------------------------------------
	     * Helper methods are appropriate in coding interviews.
	     *
	     * Example:
	     *
	     *     public static String sortsKey(String word)
	     *
	     * This separates:
	     *
	     *     signature creation
	     *
	     * from:
	     *
	     *     grouping logic
	     *
	     * Benefits:
	     *
	     *     - easier to read
	     *     - easier to test
	     *     - easier to explain
	     *     - keeps the main method focused
	     *
	     * A good explanation:
	     *
	     *     "I am extracting the signature-generation logic into
	     *      a helper method so the grouping method stays readable."
	     *
	     * -------------------------------------------------
	     *
	     * 17. COMMON MISTAKES
	     * -------------------------------------------------
	     * Mistake:
	     *
	     *     Comparing every pair of strings.
	     *
	     * Fix:
	     *
	     *     Compute one shared signature per word.
	     *
	     *
	     * Mistake:
	     *
	     *     Using the original word as the key.
	     *
	     * Fix:
	     *
	     *     Use the sorted word or frequency count as the key.
	     *
	     *
	     * Mistake:
	     *
	     *     Replacing an existing list with map.put().
	     *
	     * Fix:
	     *
	     *     Retrieve the existing list and append with add().
	     *
	     *
	     * Mistake:
	     *
	     *     Removing duplicate words.
	     *
	     * Fix:
	     *
	     *     Preserve every occurrence from the input.
	     *
	     *
	     * Mistake:
	     *
	     *     Assigning map.values() directly to a List.
	     *
	     * Fix:
	     *
	     *     Create a new ArrayList from the collection.
	     *
	     *
	     * Mistake:
	     *
	     *     Saying the enhanced for-loop uses O(n) space.
	     *
	     * Fix:
	     *
	     *     The loop variable is one reused reference, so O(1) space.
	     *
	     * -------------------------------------------------
	     *
	     * 18. INTERVIEW PATTERN RECOGNITION
	     * -------------------------------------------------
	     * If I see:
	     *
	     *     "Group equivalent items together"
	     *
	     * THINK:
	     *
	     *     "Can I generate a shared signature for each item?"
	     *
	     * If I see:
	     *
	     *     nested loops comparing every object with every other object
	     *
	     * THINK:
	     *
	     *     "Can I transform each object once and group it in a HashMap?"
	     *
	     * If I need:
	     *
	     *     one key associated with multiple items
	     *
	     * THINK:
	     *
	     *     HashMap<Key, List<Value>>
	     *
	     * General pattern:
	     *
	     *     item
	     *       ->
	     *     signature
	     *       ->
	     *     HashMap
	     *       ->
	     *     group
	     *
	     * -------------------------------------------------
	     *
	     * 19. CORE MENTAL MODEL
	     * -------------------------------------------------
	     * For every word:
	     *
	     *     1. Create its shared anagram signature.
	     *
	     *     2. Find the list associated with that signature.
	     *
	     *     3. Add the original word to that list.
	     *
	     * Then:
	     *
	     *     return all of the HashMap's value lists.
	     *
	     * One-sentence summary:
	     *
	     *     "Every time I get a word, I calculate its shared
	     *      anagram key and store the word in the list associated
	     *      with that key."
	     */

}
