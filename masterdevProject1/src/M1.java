import java.util.Scanner;

public class M1 {
    public static Scanner scanner = new Scanner(System.in);
    public static int total(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();;
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(total(a));
    }
        
    }

