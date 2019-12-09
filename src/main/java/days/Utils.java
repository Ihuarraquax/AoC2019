package days;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static String getInputData() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL("http://adventofcode.com/2019/day/1/input");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            int c;
            while ((c = bufferedReader.read()) != '\n') {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}

