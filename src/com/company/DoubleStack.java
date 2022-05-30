package com.company;

public class DoubleStack {
    double[] tab;
    int firstFree;

    public DoubleStack(int MaxSize) {
        tab = new double[MaxSize];
        firstFree = 0;
    }

    int getMaximumStackSize() {
        return tab.length;
    }

    boolean isEmpty() {
        if (firstFree == 0) {
            return true;
        } else
            return false;
    }

    int getSize() {
        return firstFree;
    }

    void push(double E) throws ArrayIndexOutOfBoundsException {
        if (firstFree < tab.length) {
            tab[firstFree] = E;
            firstFree++;

        } else {
            throw new ArrayIndexOutOfBoundsException(
                    "Przepełnienie stosu, operacja nie powiodła się");
        }
    }

    double pop() throws IndexOutOfBoundsException {
        if (firstFree <= 0) {
            throw new IndexOutOfBoundsException(
                    "Stos jest pusty, operacja nie powiodła się");
        }

        double temp = tab[firstFree - 1];
        firstFree--;
        return temp;
    }

    double popWithoutPopping() {
        return tab[firstFree - 1];
    }
}
