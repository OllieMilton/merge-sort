package mergesort;

import java.util.Arrays;

public class MergeSort {

	
	public static void main(String[] args) {
		int[] arr = new int[9];
		arr[0] = 9;
		arr[1] = 3;
		arr[2] = 4;
		arr[3] = 2;
		arr[4] = 35;
		arr[5] = 78;
		arr[6] = 0;
		arr[7] = 22;
		arr[8] = 16;
		System.out.println(Arrays.toString(arr));
		MergeSort ms = new MergeSort();
		ms.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public int[] sort(int[] arr) {
		int[] workspace = new int[arr.length];
		recurseMerge(workspace, arr, 0, arr.length-1);
		return arr;
	}
	
	private void recurseMerge(int[] workspace, int[] toSort, int lower, int upper) {
		if (lower == upper) {
			// we have got the base condition of a single element which is considered sorted
			return;
		} else {
			// find the mid point in this section of the array
			int midPoint = (upper+lower) / 2;
			// pointer to the bottom of the upper section of the array
			int upPtr = midPoint+1;
			// recursively sort the lower section
			recurseMerge(workspace, toSort, lower, midPoint);
			// and now the upper section
			recurseMerge(workspace, toSort, upPtr, upper);
			// now merge the array elements
			merge(workspace, toSort, lower, upPtr, upper);
		}
	}
	
	private void merge(int[] workspace, int[] toSort, int lower, int upPtr, int upper) {
		int wsPtr = 0;
		int lowPtr = lower;
		int mid = upPtr -1;
		int size = upper-lower+1;
		
		// while the pointer to the lower section of the array is lees than or equal to the middle
		// and the point to the upper section is less than or equal to the upper bound
		// order the in into the work space
		while (lowPtr <= mid && upPtr <= upper) {
			if (toSort[lowPtr] < toSort[upPtr]) {
				workspace[wsPtr++] = toSort[lowPtr++];
			} else {
				workspace[wsPtr++] = toSort[upPtr++];
			}
		}
		
		// now handle any cases the size of the array is not a power of two
		while (lowPtr <= mid) {
			workspace[wsPtr++] = toSort[lowPtr++];
		}
		
		while (upPtr <= upper) {
			workspace[wsPtr++] = toSort[upPtr++];
		}
		// now copy the work space back into the array
		for (wsPtr = 0; wsPtr < size; wsPtr++ ) {
			toSort[lower+wsPtr] = workspace[wsPtr];
		}
	}
}
