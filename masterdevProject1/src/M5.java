import java.util.Scanner;
public class M5 {
    public static Scanner scanner = new Scanner(System.in);
    
    public static int area(int a, int b, int c) throws Exception {
        if (a + b > c && a + c > b && b + c > a) {
            return (a + b + c) / 2;
        } else {
            throw new Exception("Triangle is not satisfied");
        }
    }
    public static void main(String[] args) throws Exception {
        int a, b, c;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        System.out.println(area(a, b, c));
    }
}
