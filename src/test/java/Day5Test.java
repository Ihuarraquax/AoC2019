import days.Day5;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Day5Test {

    Day5 day5;

    @Before
    public void setUp() {
        day5 = new Day5();
    }

    @Test
    public void ShouldAdd() {
        day5.solve("1,5,5,6,99,1,7,0");
        Assert.assertEquals(2, day5.table[6]);
    }

    @Test
    public void ShouldMul() {
        day5.solve("2,5,5,6,99,2,7,0");
        Assert.assertEquals(4, day5.table[6]);
    }

}