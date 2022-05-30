package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfixExpressionTest {

    @Test
    public void fillNumberArr() {
        InfixExpression x = new InfixExpression("1+4+5.17");
        double[] ax = x.fillNumberArr(x.getInfix().toCharArray());
        double[] ass = {1, 4, 5.17};
        assertArrayEquals(ass, ax);
    }

    @Test
    public void fillOperatorArr() {
        InfixExpression x = new InfixExpression("1+4+5.17-1/10");

        char[] ass = {'+', '+', '-', '/'};
        assertArrayEquals(ass, x.fillOperatorArr(x.getInfix().toCharArray()));
    }

    @Test
    public void infixConverter() {
        InfixExpression first = new InfixExpression("1+2+3+4+5");
        String f1 = first.infixConverter();
        assertEquals("0+1+2+3+4", f1);

        InfixExpression second = new InfixExpression("1.23+1.56+6.1921+7.1");
        String f2 = second.infixConverter();
        assertEquals("0+1+2+3", f2);

        InfixExpression third = new InfixExpression("1+1.953+9.23+1");
        String f3 = third.infixConverter();
        assertEquals("0+1+2+3", f3);

        InfixExpression fourth = new InfixExpression("0");
        String f4 = fourth.infixConverter();
        assertEquals("0", f4);

    }

    @Test
    public void shuntingYardAlgo(){
        InfixExpression n = new InfixExpression("12+1*(2*3+4/5)");
        String conv = n.infixConverter();
        String x = n.shuntingYardAlgo(conv);
        assertEquals("0123*45/+*+", x);
    }

    @Test
    public void shuntingYardToValue(){
        InfixExpression n = new InfixExpression("12+1*(2*3+4)");
        double[] ax = n.fillNumberArr(n.getInfix().toCharArray());
        String conv = n.infixConverter();
        String x = n.shuntingYardAlgo(conv);
        double res = n.shuntingYardToValue(x, ax);
        assertEquals(22, res);
    }

}