package days;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class IntcodeComputer {

    private static final int MEMORY_SIZE = 1000000;
    public int relativeBase;
    private int phase;
    public long[] table;
    private Scanner scanner;
    private final String ADD = "01";
    private final String MUL = "02";
    private final String INPUT = "03";
    private final String OUTPUT = "04";
    private final String JIT = "05";
    private final String JIF = "06";
    private final String LT = "07";
    private final String EQU = "08";
    private final String RB = "09";
    private final String HALT = "99";
    private char secondParamState;
    private char firstParamState;
    private char thirdParamState;
    private long c;
    private long b;
    private long a;
    private boolean stopped;
    private boolean isPhaseSet = true;
    private int i;

    public IntcodeComputer(String code) {
        initTable(code);
    }

    public IntcodeComputer(String code, int phase) {
        initTable(code);
        this.phase = phase;
        isPhaseSet = false;
        i = 0;
    }

    public IntcodeComputer() {

    }

    public void run(String code, int input) {
        initTable(code);
        computeCodeWithInputAndReturnOutput(input);
    }

    public void run(String code) {
        initTable(code);
        i = 0;
        scanner = new Scanner(System.in);
        processOpcodes();
    }

    public Integer computeCodeWithInputAndReturnOutput(int input) {
        PrintStream old = System.out;
        scanner = new Scanner(new ByteArrayInputStream(String.valueOf(input).getBytes()));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        processOpcodes();
        System.setOut(old);
        String returnValue = outputStream.toString().trim();
        if (returnValue.equals("")) {
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
        while (i < table.length) {

            char[] command = getOpcodeFromNumber(i);
            String opcode = command[3] + "" + command[4];
            firstParamState = '0';
            secondParamState = '0';
            thirdParamState = '0';
            if (opcode.equals(HALT) || stopped) {
                break;
            }

            evaluateParams(command);
            //debugInfo(i, command);

            switch (opcode) {
                case ADD:
                    evaluateABC();
                    table[(int)c] = a + b;
                    i += 4;
                    break;
                case MUL:
                    evaluateABC();
                    table[(int)c] = a * b;
                    i += 4;
                    break;
                case INPUT:
                    evaluateA();
                    if (!isPhaseSet) {
                        table[(int)a] = phase;
                        isPhaseSet = true;
                    } else {
                        if (scanner.hasNextLong()) {
                            table[(int)a] = scanner.nextLong();
                        } else {
                            stopped = true;
                            break;
                        }
                    }
                    i += 2;
                    break;
                case OUTPUT:
                    evaluateA();
                    System.out.println(table[(int)a]);
                    i += 2;
                    break;
                case JIT:
                    evaluateAB();
                    if (a != 0) {
                        i = (int)b;
                    } else {
                        i += 3;
                    }
                    break;
                case JIF:
                    evaluateAB();
                    if (a == 0) {
                        i =(int) b;
                    } else {
                        i += 3;
                    }
                    break;
                case LT:
                    evaluateABC();
                    if (a < b) {
                        table[(int)c] = 1;
                    } else {
                        table[(int)c] = 0;
                    }
                    i += 4;
                    break;
                case EQU:
                    evaluateABC();
                    if (a == b) {
                        table[(int)c] = 1;
                    } else {
                        table[(int)c] = 0;
                    }
                    i += 4;
                    break;
                case RB:
                    if (firstParamState == '0') {
                        a = table[(int)table[i + 1]];
                    }
                    if (firstParamState == '1') {
                        a = table[i + 1];
                    }
                    if (firstParamState == '2') {
                        a = table[relativeBase + (int)table[i + 1]];
                    }
                    relativeBase += a;
                    i += 2;
                    break;
            }
        }

    }

    private void evaluateAB() {
        if (firstParamState == '0') {
            a = table[(int)table[i + 1]];
        }
        if (firstParamState == '1') {
            a = table[i + 1];
        }
        if (firstParamState == '2') {
            a = table[relativeBase + (int)table[i + 1]];
        }

        if (secondParamState == '0') {
            b = table[(int)table[i + 2]];
        }
        if (secondParamState == '1') {
            b = table[i + 2];
        }
        if (secondParamState == '2') {
            b = table[relativeBase + (int)table[i + 2]];
        }
    }

    private void evaluateA() {
        if (firstParamState == '0') {
            a = table[i + 1];
        }
        if (firstParamState == '1') {
            a = i + 1;
        }
        if (firstParamState == '2') {
            a = relativeBase + table[i + 1];
        }
    }

    private void evaluateABC() {
        evaluateAB();

        if (thirdParamState == '0') {
            c = table[i + 3];
        }
        if (thirdParamState == '1') {
            c = i + 3;
        }
        if (thirdParamState == '2') {
            c = relativeBase + table[i + 3];
        }
    }

    private void debugInfo(int i, char[] command) {
        System.out.println("i:" + i + " command:" + Arrays.toString(command) + " params:" + firstParamState + "," + secondParamState + "," + thirdParamState + ",");
//        for (int j = 0; j < table.length; j++) {
//            System.out.print("t[" + j + "]=" + table[j] + ",");
//        }
//        System.out.println();
    }

    private void evaluateParams(char[] command) {
        firstParamState = command[2];
        secondParamState = command[1];
        thirdParamState = command[0];
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

    private long[] makeTableFromTokenizer(StringTokenizer tokenizer) {
        long[] table = new long[MEMORY_SIZE];
        int lastIndex = tokenizer.countTokens();
        Arrays.fill(table, 0);
        for (int i = 0; i < lastIndex; i++) {
            table[i] = Long.parseLong(tokenizer.nextToken());
        }
        return table;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        day5.solve();
    }


}

