package by.epam.inner.beans;

import by.epam.inner.exceptions.NotPositiveArgumentException;

import java.util.Objects;

public class Byn implements Comparable<Byn> {

    private static String convert(int pennies) {
        return pennies / 100 + "." + pennies % 100 / 10 + pennies % 10;
    }

    private final int pennies;

    public Byn(int pennies) {
        if (pennies < 0) {
            throw new NotPositiveArgumentException(pennies);
        }
        this.pennies = pennies;
    }

    public Byn() {
        this(0);
    }

    public Byn(Byn byn) {
        this(byn.pennies);
    }

    public Byn(int rub, int pennies) {
        this(getTotalPennies(rub, pennies));
    }


    private static int getTotalPennies(int rub, int pennies) {
        return rub * 100 + pennies;
    }

    @Override
    public String toString() {
        return convert(pennies);
    }

    @Override
    public int compareTo(Byn o) {
        return pennies - o.pennies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return pennies == byn.pennies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pennies);
    }

    public Byn mul(int number) {
        return new Byn(pennies * number);
    }

    public Byn sub(Byn byn) {
        return new Byn(pennies - byn.pennies);
    }

    public Byn add(Byn byn) {
        return new Byn(pennies + byn.pennies);
    }

}
