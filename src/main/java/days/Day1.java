package days;

import java.io.*;
import java.net.URL;
import java.util.StringTokenizer;

public class Day1 {

    int answer = 0;
    public void solve() {

        String data = Utils.getInputData();
        StringTokenizer tokenizer = new StringTokenizer(data);
        while (tokenizer.hasMoreTokens()) {
            calculate(Integer.parseInt(tokenizer.nextToken()));
        }
    }



    private void calculate(int value) {

        answer += (value / 3) - 2 + calculateRec((value / 3) - 2);
    }

    private int calculateRec(int i) {

        int fuel = (i / 3) - 2;
        if (fuel <= 0) {
            return 0;
        }
        else{
            return fuel + calculateRec(fuel);
        }
    }


}
