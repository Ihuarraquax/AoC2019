package days;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AsteroidMapTest {

    private AsteroidMap asteroidMap;

    @Before
    public void setUp() throws Exception {
        asteroidMap = new AsteroidMap();
        asteroidMap.initMap(
                        ".#..#\n" +
                        ".....\n" +
                        "#####\n" +
                        "....#\n" +
                        "...##");
    }

    @Test
    public void name() {


        Assert.assertEquals('#', asteroidMap.at(1, 0));
        Assert.assertEquals('#', asteroidMap.at(3, 4));
        Assert.assertEquals('.', asteroidMap.at(1, 4));
    }

    @Test
    public void calculateLoSAsteroids() {

        Assert.assertEquals(7, asteroidMap.calculateLoSAsteroids(4,0));
        Assert.assertEquals(-1, asteroidMap.calculateLoSAsteroids(2,0));
        Assert.assertEquals(5, asteroidMap.calculateLoSAsteroids(4,2));
    }


}
