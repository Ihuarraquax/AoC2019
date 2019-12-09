import days.day6.Planet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Day6Test {

    private Planet planetC;
    private Planet planetB;
    private Planet planetA;
    private Planet planetF;
    private Planet planetE;
    private Planet planetD;


    @Before
    public void setup() {
        planetA = new Planet("A");
        planetB = new Planet("B");
        planetC = new Planet("C");

        planetD = new Planet("D");
        planetE = new Planet("E");
        planetF = new Planet("F");
    }
    @Test
    public void planetBOrbitsA() {

        planetB.setPlanetOrbitingAround(planetA);

        Assert.assertTrue(planetB.isInOrbitAround(planetA));
    }

    @Test
    public void planetCOrbitsBAndBOrbitsAIsCOrbitingAroundA() {
        planetC.setPlanetOrbitingAround(planetB);
        planetB.setPlanetOrbitingAround(planetA);

        Assert.assertTrue(planetC.isInOrbitAround(planetA));
    }

    @Test
    public void setPlanetOrbitByName() {
        planetB.setPlanetOrbitingAround("A");

        Assert.assertTrue(planetB.isInOrbitAround(planetA));
        Assert.assertTrue(planetB.isInOrbitAround("A"));
    }

    @Test
    public void countYourOrbits() {
        planetC.setPlanetOrbitingAround(planetB);
        planetB.setPlanetOrbitingAround(planetA);
        Assert.assertEquals(2, planetC.getAllOrbits());
    }

    @Test
    public void findMinOrbitalTransfers() {

/*
                  / YOU
        C - B - A
       /
      D - F - E - SAN
  */

        planetA.setPlanetOrbitingAround(planetB);
        planetB.setPlanetOrbitingAround(planetC);
        planetC.setPlanetOrbitingAround(planetD);
        planetE.setPlanetOrbitingAround(planetF);
        planetF.setPlanetOrbitingAround(planetD);

        Planet you = new Planet("YOU");
        you.setPlanetOrbitingAround(planetA);
        Planet san = new Planet("SAN");
        san.setPlanetOrbitingAround(planetE);

        Assert.assertEquals(5, Planet.minOrbitalTransfers(you,san));

    }

    @Test
    public void findFirstCommonPlanet() {
        planetA.setPlanetOrbitingAround(planetB);
        planetB.setPlanetOrbitingAround(planetC);
        planetC.setPlanetOrbitingAround(planetD);
        planetE.setPlanetOrbitingAround(planetF);
        planetF.setPlanetOrbitingAround(planetD);
        planetD.setPlanetOrbitingAround(new Planet("G"));
        Planet you = new Planet("YOU");
        you.setPlanetOrbitingAround(planetA);
        Planet san = new Planet("SAN");
        san.setPlanetOrbitingAround(planetE);

        Assert.assertEquals(planetD,Planet.findFirstCommonPlanet(you,san));
    }
}