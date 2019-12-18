package days.day7;

import days.Day5;
import util.Permutation;

import java.util.Set;

public class Day7 {


    public static void main(String[] args) {


        Integer[] table = {5, 6, 7, 8, 9};
        Set<String> sequences = Permutation.printAllOrdered(table);
        int maxThrustlerSignal = Integer.MIN_VALUE;
        Day7.getThrustlerSignal(code, "98765");
        for (String seq : sequences) {
            int thrustlerSignal = Day7.getThrustlerSignal(code, seq);
            if (thrustlerSignal > maxThrustlerSignal) {
                maxThrustlerSignal = thrustlerSignal;
            }
        }
        System.out.println(maxThrustlerSignal);
    }


    public static int getThrustlerSignal(String code, String sequence) {

        Day5 ampA = new Day5(code, Character.getNumericValue(sequence.charAt(0)));
        Day5 ampB = new Day5(code, Character.getNumericValue(sequence.charAt(1)));
        Day5 ampC = new Day5(code, Character.getNumericValue(sequence.charAt(2)));
        Day5 ampD = new Day5(code, Character.getNumericValue(sequence.charAt(3)));
        Day5 ampE = new Day5(code, Character.getNumericValue(sequence.charAt(4)));
                             //139629729
        Integer output = 0;
        Integer ampELastOutput = 0;
        while (true) {
            output = ampA.computeCode(output);
            if (output==null){
                break;
            }
            output = ampB.computeCode(output);
            if (output==null){
                break;
            }
            output = ampC.computeCode(output);
            if (output==null){
                break;
            }
            output = ampD.computeCode(output);
            if (output==null){
                break;
            }
            output = ampE.computeCode(output);
            if (output==null){
                break;
            }
            ampELastOutput = output;
        }

        return ampELastOutput;
    }

  private static String code = "3,8,1001,8,10,8,105,1,0,0,21,38,59,76,89,106,187,268,349,430,99999,3,9,1002,9,3,9,101,2,9,9,1002,9,4,9,4,9,99,3,9,1001,9,5,9,1002,9,5,9,1001,9,2,9,1002,9,3,9,4,9,99,3,9,1001,9,4,9,102,4,9,9,1001,9,3,9,4,9,99,3,9,101,4,9,9,1002,9,5,9,4,9,99,3,9,1002,9,3,9,101,5,9,9,1002,9,3,9,4,9,99,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,99";
    //private static String code = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
}
