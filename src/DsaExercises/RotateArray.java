package DsaExercises;

class RotateArray {


    static void rotateArr(int[] arr, int d) {
        int n = arr.length;

        //when d > size of array
        d %= n;

        // reversing the entire array
        reverse(arr, 0, n - 1);

        // reversing the first d elements
        reverse(arr, 0, d - 1);

        // reversing the remaining n-d elements
        reverse(arr, d, n - 1);
    }

    //reverse function
    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        //6 5 4 3 2 1

        //5 6 4 3 2 1

        //5 6 1 2 3 4
        int d = 2;

        rotateArr(arr, d);

        for (int j : arr) System.out.print(j + " ");
    }
}