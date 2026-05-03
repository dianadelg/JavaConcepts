package beforeOptimizing;

public class TwoSum {

    /*
     * Problem: Two Sum
     *
     * Given an array of integers nums and an integer target,
     * return the indices of the two numbers such that they add up to target.
     *
     * You may assume that each input has exactly one solution,
     * and you may not use the same element twice.
     *
     * You can return the answer in any order.
     *
     * Examples:
     *
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: nums[0] + nums[1] == 9
     *
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     *
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     *
     * Constraints:
     * 2 <= nums.length <= 10^4
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     * Only one valid answer exists.
     */

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        throw new IllegalArgumentException("No solution found");
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 6, 1, 0};
        int target = 6;

        int[] result = twoSum(nums, target);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}