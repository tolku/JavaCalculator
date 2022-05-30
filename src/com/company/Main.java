package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InfixExpression x = new InfixExpression("1+4+5.17");
        System.out.println(x.infixExpToONP()
        );
    }
}
