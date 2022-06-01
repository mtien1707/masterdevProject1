
import java.util.Scanner;
public class M3 {
    public static Scanner scanner = new Scanner(System.in);
    public static int[] sortArray(int[] n) {
    	QuickSort(n, 0, n.length - 1);
    	int result[] = new int[n.length]; 
        for (int i = 0; i < n.length; i++) {
        	result[i] = n[i];
        }
        return result;
    }
    
    public static void QuickSort(int n[], int l, int r) {
    	if(l >= r) return; 
		int mid = Partition(n, l, r);
		QuickSort(n, l, mid);
		QuickSort(n, mid + 1, r);
    }
    
    public static void Swap(int[] n, int l, int r) {
    	int temp = n[l];
    	n[l] = n[r]; 
    	n[r] = temp;
    }
    
    public static int Partition(int[] n, int l, int r) {
    	int pivot = n[l];
    	while(l < r) {
            while (l < r && n[r] >= pivot) r--;
            Swap(n, l, r);
            while (l < r && n[l] <= pivot) l++;
            Swap(n, r, l);
    	}
    	n[l] = pivot;
    	return l; 
    }
    public static void main(String[] args) throws Exception {
        
        int a[] = new int[1];
        int arr[];
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }   
        arr = sortArray(a);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i]);
    }
}

