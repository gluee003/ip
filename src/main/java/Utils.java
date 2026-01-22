/**
 * The Utils class is a collection of useful methods.
 */
public class Utils {
    /**
     * Returns the index of the first occurrence of the target element in an array.
     * Returns -1 is the element is not in the array.
     *
     * @param arr The array to search on.
     * @param target The target element.
     * @return Index of target.
     * @param <T> The type of the array and target.
     */
    public static <T> int findInArr(T[] arr, T target) {
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(target)) {
                index = i;
                break;
            }
        }

        return index;
    }
}
