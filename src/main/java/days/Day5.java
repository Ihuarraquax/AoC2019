package days;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Day5 {

    private int phase;
    public int[] table;
    private String data;
    private final int VALUE = 19690720;
    private Scanner scanner;
    private final String ADD = "01";
    private final String MUL = "02";
    private final String INPUT = "03";
    private final String OUTPUT = "04";
    private final String JIT = "05";
    private final String JIF = "06";
    private final String LT = "07";
    private final String EQU = "08";
    private final String HALT = "99";
    private boolean secondParamState;
    private boolean firstParamState;
    private boolean thirdParamState;
    private int c;
    private int b;
    private int a;
    private boolean stopped;
    private boolean isPhaseSet = true;
    private int i;

    public Day5(String code) {
        initTable(code);
    }

    public Day5() {
    }

    public Day5(String code, int phase) {
        initTable(code);
        this.phase = phase;
        i = 0;
    }

    public void solve(String s) {
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

    public Integer computeCode(int input) {
        PrintStream old = System.out;
        isPhaseSet = false;
        scanner = new Scanner(new ByteArrayInputStream(String.valueOf(input).getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        processOpcodes();
        System.setOut(old);
        String returnValue = outputStream.toString().trim();
        if(returnValue.equals("")){
            return null;
        }
        return Integer.valueOf(returnValue);
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
        stopped = false;
        while(i < table.length) {

            char[] command = getOpcodeFromNumber(i);
            String opcode = command[3] + "" + command[4];
            firstParamState = false;
            secondParamState = false;
            thirdParamState = false;

            if (opcode.equals(HALT) || stopped) {
                break;
            }

            evaluateParams(i, command, opcode);
            //debugInfo(i, command);

            switch (opcode) {
                case ADD:
                    a = firstParamState ? table[i + 1] : table[table[i + 1]];
                    b = secondParamState ? table[i + 2] : table[table[i + 2]];
                    c = thirdParamState ? i + 3 : table[i + 3];
                    table[c] = a + b;
                    i += 4;
                    break;
                case MUL:
                    a = firstParamState ? table[i + 1] : table[table[i + 1]];
                    b = secondParamState ? table[i + 2] : table[table[i + 2]];
                    c = thirdParamState ? i + 3 : table[i + 3];
                    table[c] = a * b;
                    i += 4;
                    break;
                case INPUT:
                    a = firstParamState ? i + 1 : table[i + 1];
                    if (!isPhaseSet) {
                        table[a] = phase;
                        isPhaseSet = true;
                    } else {
                        table[a] = scanner.nextInt();
                    }
                    i += 2;
                    break;
                case OUTPUT:
                    a = firstParamState ? i + 1 : table[i + 1];
                    System.out.println(table[a]);
                    i += 2;
                    stopped = true;
                    break;
                case JIT:
                    a = firstParamState ? table[i + 1] : table[table[i + 1]];
                    b = secondParamState ? table[i + 2] : table[table[i + 2]];
                    if (a != 0) {
                        i = b;
                    } else {
                        i += 3;
                    }
                    break;
                case JIF:
                    a = firstParamState ? table[i + 1] : table[table[i + 1]];
                    b = secondParamState ? table[i + 2] : table[table[i + 2]];
                    if (a == 0) {
                        i = b;
                    } else {
                        i += 3;
                    }
                    break;
                case LT:
                    a = firstParamState ? table[i + 1] : table[table[i + 1]];
                    b = secondParamState ? table[i + 2] : table[table[i + 2]];
                    c = thirdParamState ? i + 3 : table[i + 3];
                    if (a < b) {
                        table[c] = 1;
                    } else {
                        table[c] = 0;
                    }
                    i += 4;
                    break;
                case EQU:
                    a = firstParamState ? table[i + 1] : table[table[i + 1]];
                    b = secondParamState ? table[i + 2] : table[table[i + 2]];
                    c = thirdParamState ? i + 3 : table[i + 3];
                    if (a == b) {
                        table[c] = 1;
                    } else {
                        table[c] = 0;
                    }
                    i += 4;
                    break;
            }
        }

    }

    private void debugInfo(int i, char[] command) {
        System.out.println("i:" + i + " command:" + Arrays.toString(command) + " params:" + firstParamState + "," + secondParamState + "," + thirdParamState + ",");
//        for (int j = 0; j < table.length; j++) {
//            System.out.print("t[" + j + "]=" + table[j] + ",");
//        }
//        System.out.println();
    }

    private void evaluateParams(int i, char[] command, String opcode) {
        if (command[2] == '1') {
            firstParamState = true;
        }
        if (command[1] == '1') {
            secondParamState = true;
        }
        if (command[0] == '1') {
            thirdParamState = true;
        }
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
        day5.solve();
    }


}

