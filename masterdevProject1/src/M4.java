import java.util.Scanner;

public class M4 {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        int n = scanner.nextInt();
        System.out.println(isPrime(n));
    }

}
