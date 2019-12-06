import java.util.HashSet;
import java.util.Set;

public class Day4 {

    Set<Integer> possiblePasswords = new HashSet<>();

    private void solve() {

        for (int i = 130254; i <= 678275; i++) {
            String s = String.valueOf(i);
            boolean isPaired = false;
            int count = 1;

            boolean isGettingBigger = true;
            for (int j = 0; j < 5; j++) {
                if (s.charAt(j) > s.charAt(j + 1)) {
                    isGettingBigger = false;
                    break;
                }

                if (s.charAt(j) == s.charAt(j + 1)) {
                    count++;
                }
                else{
                    if (count == 2) {
                        isPaired = true;
                    }
                    count = 1;
                }
            }
            if (count == 2) {
                isPaired = true;
            }

            if(isPaired&&isGettingBigger){
                possiblePasswords.add(i);
                System.out.println(i);
            }
        }
        System.out.println(possiblePasswords.size());


    }


    public static void main(String[] args) {
        Day4 day4 = new Day4();
        day4.solve();
    }
}
