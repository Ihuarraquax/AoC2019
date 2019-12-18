package days;

public class Layer {
    int[][] data;
    int indexWide = 0;
    int indexTall = 0;
    int wide;
    int tall;

    public Layer(int tall, int wide) {
        data = new int[tall][wide];
        this.wide = wide;
        this.tall = tall;
    }

    public boolean hasFreeSpots() {
        if (indexWide < wide && indexTall < tall) {
            return true;
        }
        return false;
    }

    public void addData(int value) {
        if (hasFreeSpots()) {
            data[indexTall][indexWide++] = value;
            if (indexWide == wide) {
                indexWide = 0;
                indexTall++;
            }
        }
    }

    public int countValues(int i) {
        int count = 0;
        for (int[] dataRow : data) {
            for (int value : dataRow) {
                if (value == i) {
                    count++;
                }
            }
        }
        return count;
    }
}
