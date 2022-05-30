package com.company;

public class Stack {
    char[] tab;
    int firstFree;

    public Stack(int MaxSize) {
        tab = new char[MaxSize];
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

    void push(char E) throws ArrayIndexOutOfBoundsException {
        if (firstFree < tab.length) {
            tab[firstFree] = E;
            firstFree++;

        } else {
            throw new ArrayIndexOutOfBoundsException(
                    "Przepełnienie stosu, operacja nie powiodła się");
        }
    }

    char pop() throws IndexOutOfBoundsException {
        if (firstFree <= 0) {
            throw new IndexOutOfBoundsException(
                    "Stos jest pusty, operacja nie powiodła się");
        }

        char temp = tab[firstFree - 1];
        firstFree--;
        return temp;
    }

    char popWithoutPopping(){
        return tab[firstFree - 1];
    }
}
