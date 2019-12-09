import days.Day5;
import days.day7.Day7;
import org.junit.Assert;
import org.junit.Test;
import util.Permutation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Set;

public class Day7Test {

    String testCode = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";

    String testCode2 = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";

    @Test
    public void shouldProcessCodeWithProgramInput() {
        Day5 day5 = new Day5(testCode);
        String input = "8";
        String output = "";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        output = day5.computeCode(input);
        Assert.assertEquals("1000", output.trim());

    }

    @Test
    public void getThrusterSignal() {

        String sequence = "98765";
        int signal = Day7.getThrustlerSignal("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5", sequence);
        Assert.assertEquals(139629729, signal);


        sequence = "43210";
        signal = Day7.getThrustlerSignal(testCode2, sequence);

        Assert.assertEquals(43210, signal);

        sequence = "01234";
        signal = Day7.getThrustlerSignal("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0", sequence);
        Assert.assertEquals(54321, signal);

        sequence = "10432";
        signal = Day7.getThrustlerSignal("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0", sequence);
        Assert.assertEquals(65210, signal);

    }

    @Test
    public void getThrustlerSignalFromAmpWithLoop() {

        Integer[] table = {5, 6, 7, 8, 9};
        Set<String> sequences = Permutation.printAllOrdered(table);
        int maxThrustlerSignal = Integer.MIN_VALUE;
        for (String seq : sequences) {
            int thrustlerSignal = Day7.getThrustlerSignal("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5", seq);
            if (thrustlerSignal > maxThrustlerSignal) {
                maxThrustlerSignal = thrustlerSignal;
            }
        }
        System.out.println(maxThrustlerSignal);


    }

}