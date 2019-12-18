package util;
// Java Implementation to find the line passing
// through two points
class GFG
{

    // This pair is used to store the X and Y
// coordinate of a point respectively
    static class Pair
    {
        int first, second;

        public Pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }


    }

    // Function to find the line given two points
    static void lineFromPoints(Pair P, Pair Q)
    {
        int a = Q.second - P.second;
        int b = P.first - Q.first;
        int c = a*(P.first) + b*(P.second);

        if(b < 0)
        {
            System.out.println("The line passing through points P and Q is: "
                    + a + "x " + b + "y = " + c);
        }
        else
        {
            System.out.println("The line passing through points P and Q is: "
                    + a + "x + " + b + "y = " + c);
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        Pair P = new Pair(1, 0);
        Pair Q = new Pair(4, 3);
        lineFromPoints(P, Q);
    }
}

// This code is contributed by Princi Singh

