package days.day6;

import java.util.*;

public class Planet {

    public static Set<Planet> planetList = new HashSet<>();
    private String name;
    private Planet planetThisIsInOrbit;
    private Integer count;

    public Planet(String name) {
        this.name = name;
        planetList.add(this);
    }


    public boolean isInOrbitAround(Planet planet) {
        if (planetThisIsInOrbit.getName().equals(planet.getName())) {
            return true;
        } else if (planetThisIsInOrbit == null) {
            return false;
        } else {
            return planetThisIsInOrbit.isInOrbitAround(planet);
        }

    }

    public boolean isInOrbitAround(String name) {
        return isInOrbitAround(findPlanetByName(name));
    }

    public void setPlanetOrbitingAround(Planet planet) {
        this.planetThisIsInOrbit = planet;
    }

    public void setPlanetOrbitingAround(String name) {
        this.planetThisIsInOrbit = findPlanetByName(name);
    }

    public static Planet findPlanetByName(String name) {
        for (Planet planet : planetList) {
            if (planet.getName().equals(name)) {
                return planet;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return name.equals(planet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getAllOrbits() {
        if (count == null) {
            count = 0;

            if (planetThisIsInOrbit == null) {
                return count;
            } else {
                return count = planetThisIsInOrbit.getAllOrbits() + 1;
            }
        } else return count;
    }

    public static int minOrbitalTransfers(Planet start, Planet end) {

        Planet crossPlanet = findFirstCommonPlanet(start, end);

        int distanceFromStart = start.orbitalTransferTo(crossPlanet);
        int distanceFromEnd = end.orbitalTransferTo(crossPlanet);

        System.out.println(distanceFromStart);
        System.out.println(distanceFromEnd);
        return distanceFromEnd + distanceFromStart;
    }

    private int orbitalTransferTo(Planet crossPlanet) {
        int distance = 0;
        while (!this.planetThisIsInOrbit.equals(crossPlanet)) {
            this.planetThisIsInOrbit = this.planetThisIsInOrbit.planetThisIsInOrbit;
            distance++;
        }

        return distance;
    }

    public static Planet findFirstCommonPlanet(Planet start, Planet end) {

        List<Planet> planets = new ArrayList<>();


        while (start.planetThisIsInOrbit != null || end.planetThisIsInOrbit != null) {
            if (start.planetThisIsInOrbit != null) {
                if (planets.contains(start.planetThisIsInOrbit)) {

                    return start.planetThisIsInOrbit;
                } else {
                    planets.add(start.planetThisIsInOrbit);
                    start = start.planetThisIsInOrbit;

                }
            }
            if (end.planetThisIsInOrbit != null) {
                if (planets.contains(end.planetThisIsInOrbit)) {

                    return end.planetThisIsInOrbit;
                } else {
                    planets.add(end.planetThisIsInOrbit);
                    end = end.planetThisIsInOrbit;

                }
            }
        }
        return null;

    }
}