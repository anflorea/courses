package ro.ubbcluj;

/**
 * Created by Flo on 27-Mar-16.
 */
public class Pair {
    private int number1;
    private int number2;

    public Pair(int no1, int no2) {
        number1 = no1;
        number2 = no2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (this == obj)
            return true;
        else if (obj instanceof Pair) {
            Pair aPair = (Pair) obj;
            if (aPair.number1 == this.number1 && aPair.number2 == this.number2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return number1 + number2;
    }
}
