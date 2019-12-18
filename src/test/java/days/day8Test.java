package days;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class day8Test {

    private Layer layer;

    @Before
    public void setUp() throws Exception {
        layer = new Layer(2, 3);
    }

    @Test
    public void ShouldAddDataToLayer() {
        layer.addData(1);
        layer.addData(2);
        layer.addData(3);
        layer.addData(4);

        assertEquals(1, layer.data[0][0]);
        assertEquals(4, layer.data[1][0]);
    }


}