package afterOptimizing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicates {

    /*
     * =====================================================
     * 1. UNSORTED ARRAY — EXTRA SPACE ALLOWED
     * =====================================================
     *
     * Uses a HashSet to remember values already encountered.
     * Preserves the original order.
     *
     * Time:  O(n) average
     * Space: O(n)
     */
    public static int[] removeDuplicatesFromUnsortedArray(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            // add() returns true only when num was not already present.
            if (seen.add(num)) {
                result.add(num);
            }
        }

        return listToArray(result);
    }

    /*
     * =====================================================
     * 2. SORTED ARRAY — TWO POINTERS
     * =====================================================
     *
     * Because duplicates are next to each other, no HashSet
     * is needed.
     *
     * Modifies the original array.
     * Returns the number of unique elements.
     *
     * Only indexes 0 through uniqueCount - 1 are valid afterward.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    public static int removeDuplicatesFromSortedArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // write points to the location where the next
        // unique value should be placed.
        int write = 1;

        for (int read = 1; read < nums.length; read++) {
            // Because the array is sorted, compare the current
            // value to the most recently retained unique value.
            if (nums[read] != nums[write - 1]) {
                nums[write] = nums[read];
                write++;
            }
        }

        return write;
    }

    /*
     * =====================================================
     * 3. UNSORTED ARRAY — NO EXTRA WORKING SPACE
     * =====================================================
     *
     * Sorts the original array first, then uses two pointers.
     *
     * Modifies the original array.
     * Returns the number of unique elements.
     *
     * Time:  O(n log n)
     * Space: Depends on the sorting implementation.
     *
     * Note:
     * Arrays.sort(int[]) uses an in-place dual-pivot quicksort,
     * so no O(n) HashSet is created.
     *
     * Original order is NOT preserved.
     */
    public static int removeDuplicatesFromUnsortedArrayInPlace(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        return removeDuplicatesFromSortedArray(nums);
    }

    /*
     * =====================================================
     * 4. JAVA LIST — PRESERVE ORIGINAL ORDER
     * =====================================================
     *
     * Uses a HashSet for lookup and a new List for the result.
     *
     * Time:  O(n) average
     * Space: O(n)
     */
    public static <T> List<T> removeDuplicatesFromList(List<T> list) {
        Set<T> seen = new HashSet<>();
        List<T> result = new ArrayList<>();

        for (T item : list) {
            if (seen.add(item)) {
                result.add(item);
            }
        }

        return result;
    }

    /*
     * =====================================================
     * 5. JAVA LIST — LINKEDHASHSET SHORTCUT
     * =====================================================
     *
     * LinkedHashSet:
     * - prevents duplicates
     * - preserves insertion order
     *
     * Time:  O(n) average
     * Space: O(n)
     */
    public static <T> List<T> removeDuplicatesWithLinkedHashSet(
            List<T> list) {

        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    /*
     * =====================================================
     * 6. JAVA LIST — ORDER DOES NOT MATTER
     * =====================================================
     *
     * Converts the List directly into a HashSet.
     *
     * Time:  O(n) average
     * Space: O(n)
     *
     * HashSet does NOT guarantee original ordering.
     */
    public static <T> Set<T> removeDuplicatesWhenOrderDoesNotMatter(
            List<T> list) {

        return new HashSet<>(list);
    }

    /*
     * Basic node class for the custom linked-list examples.
     */
    private static class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
        }
    }

    /*
     * =====================================================
     * 7. LINKED LIST — HASHSET
     * =====================================================
     *
     * Removes duplicate nodes while preserving the first
     * occurrence of each value.
     *
     * Time:  O(n) average
     * Space: O(n)
     */
    public static ListNode removeDuplicatesFromLinkedList(ListNode head) {
        Set<Integer> seen = new HashSet<>();

        ListNode current = head;
        ListNode previous = null;

        while (current != null) {
            if (seen.contains(current.value)) {
                // Skip the duplicate node.
                previous.next = current.next;
            } else {
                // Keep this node.
                seen.add(current.value);
                previous = current;
            }

            current = current.next;
        }

        return head;
    }

    /*
     * =====================================================
     * 8. LINKED LIST — NO EXTRA SPACE
     * =====================================================
     *
     * For every node, a runner scans all nodes after it
     * and removes duplicates.
     *
     * Time:  O(n^2)
     * Space: O(1)
     */
    public static ListNode removeDuplicatesFromLinkedListNoExtraSpace(
            ListNode head) {

        ListNode current = head;

        while (current != null) {
            ListNode runner = current;

            while (runner.next != null) {
                if (runner.next.value == current.value) {
                    // Remove runner.next because it is a duplicate.
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            current = current.next;
        }

        return head;
    }

    /*
     * =====================================================
     * 9. SMALL KNOWN INTEGER RANGE — BOOLEAN ARRAY
     * =====================================================
     *
     * Useful when values are known to be within a small range.
     *
     * Example:
     * minValue = 0
     * maxValue = 100
     *
     * Time:  O(n)
     * Space: O(maxValue - minValue + 1)
     *
     * This preserves the original order.
     */
    public static int[] removeDuplicatesWithBooleanArray(
            int[] nums,
            int minValue,
            int maxValue) {

        if (minValue > maxValue) {
            throw new IllegalArgumentException(
                    "minValue cannot be greater than maxValue.");
        }

        boolean[] seen = new boolean[maxValue - minValue + 1];
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            if (num < minValue || num > maxValue) {
                throw new IllegalArgumentException(
                        num + " is outside the allowed range.");
            }

            int index = num - minValue;

            if (!seen[index]) {
                seen[index] = true;
                result.add(num);
            }
        }

        return listToArray(result);
    }

    /*
     * =====================================================
     * 10. STRING — PRESERVE FIRST OCCURRENCE
     * =====================================================
     *
     * Example:
     * "banana" becomes "ban"
     *
     * Time:  O(n) average
     * Space: O(n)
     */
    public static String removeDuplicateCharacters(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text cannot be null.");
        }

        Set<Character> seen = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (seen.add(character)) {
                result.append(character);
            }
        }

        return result.toString();
    }

    /*
     * Converts a List<Integer> to an int[].
     */
    private static int[] listToArray(List<Integer> list) {
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    /*
     * Creates a linked list from an int array.
     */
    private static ListNode createLinkedList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    /*
     * Prints a linked list.
     */
    private static void printLinkedList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.value);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        /*
         * -------------------------------------------------
         * 1. Unsorted array with HashSet
         * -------------------------------------------------
         */
        int[] unsortedArray = {4, 2, 4, 1, 2, 3, 1};

        int[] uniqueUnsorted =
                removeDuplicatesFromUnsortedArray(unsortedArray);

        System.out.println("1. Unsorted array with HashSet:");
        System.out.println(Arrays.toString(uniqueUnsorted));
        System.out.println();

        /*
         * -------------------------------------------------
         * 2. Already-sorted array with two pointers
         * -------------------------------------------------
         */
        int[] sortedArray = {1, 1, 2, 2, 2, 3, 4, 4};

        int sortedUniqueCount =
                removeDuplicatesFromSortedArray(sortedArray);

        System.out.println("2. Sorted array with two pointers:");
        System.out.println(
                Arrays.toString(
                        Arrays.copyOf(sortedArray, sortedUniqueCount)
                )
        );
        System.out.println();

        /*
         * -------------------------------------------------
         * 3. Unsorted array without a HashSet
         * -------------------------------------------------
         */
        int[] unsortedInPlace = {4, 2, 4, 1, 2, 3, 1};

        int inPlaceUniqueCount =
                removeDuplicatesFromUnsortedArrayInPlace(unsortedInPlace);

        System.out.println("3. Unsorted array sorted in place:");
        System.out.println(
                Arrays.toString(
                        Arrays.copyOf(unsortedInPlace, inPlaceUniqueCount)
                )
        );
        System.out.println();

        /*
         * -------------------------------------------------
         * 4. Java List preserving order
         * -------------------------------------------------
         */
        List<Integer> numberList =
                Arrays.asList(4, 2, 4, 1, 2, 3, 1);

        List<Integer> uniqueList =
                removeDuplicatesFromList(numberList);

        System.out.println("4. List with HashSet:");
        System.out.println(uniqueList);
        System.out.println();

        /*
         * -------------------------------------------------
         * 5. LinkedHashSet shortcut
         * -------------------------------------------------
         */
        List<Integer> linkedHashSetResult =
                removeDuplicatesWithLinkedHashSet(numberList);

        System.out.println("5. List with LinkedHashSet:");
        System.out.println(linkedHashSetResult);
        System.out.println();

        /*
         * -------------------------------------------------
         * 6. Order does not matter
         * -------------------------------------------------
         */
        Set<Integer> uniqueSet =
                removeDuplicatesWhenOrderDoesNotMatter(numberList);

        System.out.println("6. List when order does not matter:");
        System.out.println(uniqueSet);
        System.out.println();

        /*
         * -------------------------------------------------
         * 7. Linked list with HashSet
         * -------------------------------------------------
         */
        ListNode linkedListWithSet =
                createLinkedList(4, 2, 4, 1, 2, 3, 1);

        System.out.println("7. Linked list with HashSet:");
        System.out.print("Before: ");
        printLinkedList(linkedListWithSet);

        linkedListWithSet =
                removeDuplicatesFromLinkedList(linkedListWithSet);

        System.out.print("After:  ");
        printLinkedList(linkedListWithSet);
        System.out.println();

        /*
         * -------------------------------------------------
         * 8. Linked list without extra space
         * -------------------------------------------------
         */
        ListNode linkedListWithoutSet =
                createLinkedList(4, 2, 4, 1, 2, 3, 1);

        System.out.println("8. Linked list without extra space:");
        System.out.print("Before: ");
        printLinkedList(linkedListWithoutSet);

        linkedListWithoutSet =
                removeDuplicatesFromLinkedListNoExtraSpace(
                        linkedListWithoutSet
                );

        System.out.print("After:  ");
        printLinkedList(linkedListWithoutSet);
        System.out.println();

        /*
         * -------------------------------------------------
         * 9. Boolean array for a small known range
         * -------------------------------------------------
         */
        int[] smallRangeArray = {4, 2, 4, 1, 2, 3, 1};

        int[] uniqueSmallRange =
                removeDuplicatesWithBooleanArray(
                        smallRangeArray,
                        0,
                        10
                );

        System.out.println("9. Small known value range:");
        System.out.println(Arrays.toString(uniqueSmallRange));
        System.out.println();

        /*
         * -------------------------------------------------
         * 10. String
         * -------------------------------------------------
         */
        String text = "banana";

        System.out.println("10. String:");
        System.out.println(
                text + " -> " + removeDuplicateCharacters(text)
        );
    }
}