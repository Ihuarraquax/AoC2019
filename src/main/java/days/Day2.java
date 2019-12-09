package days;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Day2 {

    private int[] table;
    private String data;
    public final int VALUE = 19690720;
    int nounAnsfer;
    int verbAnsfer;

    public void solve() {

        data = Utils.getInputData();

        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                initTable(noun, verb, data);
                processOpcodes();
                if (table[0] == VALUE) {
                    nounAnsfer = noun;
                    verbAnsfer = verb;
                    break;
                }
            }
        }

        System.out.println("noun: " + nounAnsfer);
        System.out.println("verb: " + verbAnsfer);
        System.out.println(100 * nounAnsfer + verbAnsfer);
    }

    private void initTable(int noun, int verb, String data) {
        StringTokenizer tokenizer = new StringTokenizer(data, ",");

        table = makeTableFromTokenizer(tokenizer);
        table[1] = noun;
        table[2] = verb;
    }

    private void printTable() {
        for (int i = 0; i < table.length; i++) {
            if (i == table.length - 1) {
                System.out.print(table[i]);
            } else {
                System.out.print(table[i] + ",");
            }
        }
    }

    private void processOpcodes() {
        for (int i = 0; i < table.length; ) {
            if (table[i] == 99) {
                break;
            } else if (table[i] == 1) {
                table[table[i + 3]] = table[table[i + 1]] + table[table[i + 2]];
            } else if (table[i] == 2) {
                table[table[i + 3]] = table[table[i + 1]] * table[table[i + 2]];
            }
            i += 4;
        }
    }

    private int[] makeTableFromTokenizer(StringTokenizer tokenizer) {
        int[] table = new int[tokenizer.countTokens()];
        for (int i = 0; i < table.length; i++) {
            table[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return table;
    }
}
