package DsaExercises;
import java.util.Arrays;

import java.util.Arrays;

class Kadane {

    static int maxSubarraySum(int[] arr) {

        //  the maximum sum found so far
        int res = arr[0];

        // max sum of subarray ending at current position
        int maxEnding = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // extend the previous subarray or star new from current element
            maxEnding = Math.max(maxEnding + arr[i], arr[i]);

            // update the result if the new subarray sum is larger
            res = Math.max(res, maxEnding);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, -8, 7, -1, 2, 3};
        System.out.println(maxSubarraySum(arr));
    }
}