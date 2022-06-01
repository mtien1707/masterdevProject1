import java.util.Scanner;

public class M6 {
    public static Scanner scanner = new Scanner(System.in);
    static void hollowCircle(int r) {
 
        // dist represents distance to the center
        double dist;
     
        // for horizontal movement
        for (int i = 0; i <= 2 * r; i++) {
     
        // for vertical movement
        for (int j = 0; j <= 2 * r; j++) {
            dist = Math.sqrt((i - r) * (i - r) +
                             (j - r) * (j - r));
            if (dist > r - 0.5 && dist < r + 0.5)
            System.out.print("*");
            else
            System.out.print(" ");
        }
     
        System.out.print("\n");
        }
    }
    
    public static void main(String[] args)
    {
        int r = scanner.nextInt();
        hollowCircle(r);
    }


   
    
}

