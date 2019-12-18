package days;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class IntcodeComputerTest {

    IntcodeComputer computer;

    @Before
    public void setUp() {
        computer = new IntcodeComputer();

    }

    @Test
    public void ShouldAdd() {
        computer.run("1,5,5,6,99,1,7,0");
        Assert.assertEquals(2, computer.table[6]);
    }

    @Test
    public void ShouldMul() {
        computer.run("2,5,5,6,99,2,7,0");
        Assert.assertEquals(4, computer.table[6]);
    }

    @Test
    public void relativeBase() {
        computer.run("109,19,99");
        Assert.assertEquals(19, computer.relativeBase);

        computer.run("109,-20,99");
        Assert.assertEquals(-1, computer.relativeBase);

    }

    @Test
    public void outputA16DigitNumber() {
        computer.run("1102,34915192,34915192,7,4,7,99,0");

        long answer = Long.valueOf("1219070632396864");
        Assert.assertEquals(computer.table[7], answer);

        computer.run("104,1125899906842624,99");
        Assert.assertEquals(computer.table[1], 1125899906842624L);
    }

}