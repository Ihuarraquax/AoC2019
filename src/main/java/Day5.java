import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Day5 {

    public int[] table;
    private String data;
    public final int VALUE = 19690720;
    int nounAnsfer;
    int verbAnsfer;
    private Scanner scanner;
    final String ADD = "01";
    final String MUL = "02";
    final String INPUT = "03";
    final String OUTPUT = "04";
    final String JIT = "05";
    final String JIF = "06";
    final String LT = "07";
    final String EQU = "08";
    final String HALT = "99";

    void solve(String s) {
        initTable(s);
        processOpcodes();
    }

    void solve() {
        scanner = new Scanner(System.in);
        data = Utils.getInputData();
        initTable(data);
        processOpcodes();
        scanner.close();
    }

    private void initTable(String data) {
        StringTokenizer tokenizer = new StringTokenizer(data, ",");

        table = makeTableFromTokenizer(tokenizer);
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

            char[] command = getOpcodeFromNumber(i);
            String opcode = command[3] + "" + command[4];
            int firstParam = 0;
            int secondParam = 0;
            int thirdParam = 0;


            if (opcode.equals(HALT)) {
                break;
            }

            if (!opcode.equals(INPUT) && !opcode.equals(OUTPUT) && !opcode.equals(HALT)) {
                if (command[2] == '1') {
                    firstParam = table[i + 1];
                } else {
                    firstParam = table[table[i + 1]];
                }

                if (command[1] == '1') {
                    secondParam = table[i + 2];
                } else {
                    secondParam = table[table[i + 2]];
                }

                if (!opcode.equals(JIT) && !opcode.equals(JIF)) {

                    if (command[0] == '1') {
                        thirdParam = table[i + 3];
                    } else {
                        thirdParam = table[table[i + 3]];
                    }
                }
            }

            System.out.println("i:" + i+" command:"+ Arrays.toString(command) +" params:"+firstParam+","+secondParam+","+thirdParam+",");
            for (int j = 0; j < table.length; j++) {
                System.out.print("t[" + j + "]=" + table[j] + ",");
            }
            System.out.println();


            switch (opcode) {
                case ADD:
                    table[thirdParam] = firstParam + secondParam;
                    i += 4;
                    break;
                case MUL:
                    table[thirdParam] = firstParam * secondParam;
                    i += 4;
                    break;
                case INPUT:
                    table[table[i + 1]] = scanner.nextInt();
                    inputIntoPosition(table[table[i + 1]], scanner.nextInt());
                    i += 2;
                    break;
                case OUTPUT:
                    System.out.println(table[thirdParam]);
                    i += 2;
                    break;
                case JIT:
                    if (firstParam == 1) {
                        i = secondParam;
                    } else {
                        i += 3;
                    }
                    break;
                case JIF:
                    if (firstParam == 0) {
                        i = secondParam;
                    } else {
                        i += 3;
                    }
                    break;
                case LT:
                    if (firstParam < secondParam) {
                        table[thirdParam] = 1;
                    } else {
                        table[thirdParam] = 0;
                    }
                    i += 4;
                    break;
                case EQU:
                    if (firstParam == secondParam) {
                        table[thirdParam] = 1;
                    } else {
                        table[thirdParam] = 0;
                    }
                    i += 4;
                    break;
            }
        }

    }

    public void inputIntoPosition(int i, int nextInt) {

    }

    private char[] getOpcodeFromNumber(int i) {
        char[] command = new char[5];
        Arrays.fill(command, '0');
        String s = String.valueOf(table[i]);

        for (int tableIndex = 4, sIndex = s.length() - 1; sIndex >= 0; sIndex--, tableIndex--) {
            command[tableIndex] = s.charAt(sIndex);
        }
        return command;

    }

    private int[] makeTableFromTokenizer(StringTokenizer tokenizer) {
        int[] table = new int[tokenizer.countTokens()];
        for (int i = 0; i < table.length; i++) {
            table[i] = Integer.parseInt(tokenizer.nextToken());
        }
        return table;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        day5.solve("1,4,4,5,1,0");
    }


}

