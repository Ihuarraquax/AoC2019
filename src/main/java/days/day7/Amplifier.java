package days.day7;

import days.Day5;

import javax.tools.JavaCompiler;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Amplifier implements Runnable {
    private Day5 computer;
    public OutputStream output;
    public InputStream input;
    public Amplifier(String code) {
        computer = new Day5(code);
    }


    @Override
    public void run() {

    }
}
