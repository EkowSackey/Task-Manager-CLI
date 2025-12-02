package DsaExercises;


class MoveZeros {

    static void pushZerosToEnd(int[] arr) {

        // pointer to track the position  of next nz
        int nz = 0;

        for (int i = 0; i < arr.length; i++) {

            // if the current element is non-zero
            if (arr[i] != 0) {

                // swap current element with the 0 at index 'nz'
                int temp = arr[i];
                arr[i] = arr[nz];
                arr[nz] = temp;

                // move 'nz' pointer to the next position
                nz++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 4, 3, 0, 5, 0};
        pushZerosToEnd(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}