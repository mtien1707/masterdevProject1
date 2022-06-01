import java.util.Scanner;

public class M2 {
    public static Scanner scanner = new Scanner(System.in);
    //Input is a string, returns the most characters that appear in that string
    public static char mostCharacters(String str) {
        int count[] = new int[10000];
        int len = str.length();
        for (int i=0; i<len; i++)
            count[str.charAt(i)]++;
      
        int max = -1;  
        char result = ' ';   
        for (int i = 0; i < len; i++) {
            if (max < count[str.charAt(i)]) {
                max = count[str.charAt(i)];
                result = str.charAt(i);
            }
        }
      
        return result;
    }
    public static void main(String[] args) throws Exception {
        String s ;

        s = scanner.nextLine();    
        System.out.print(mostCharacters(s));
    }
}
