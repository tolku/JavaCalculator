package com.company;

import java.util.*;

public class InfixExpression {
    private String infix;

    public InfixExpression(String infix) {
        this.infix = infix;
    }

    public String getInfix() {
        return infix;
    }

    public double infixExpToONP() {
        char[] arr = new char[infix.length()];
        double[] numberArr;
        char[] operatorArr;
        String infixExp, shuntingYardExp;
        arr = infix.toCharArray();
        numberArr = fillNumberArr(arr);
        infixExp = infixConverter();
        shuntingYardExp = shuntingYardAlgo(infixExp);
        return shuntingYardToValue(shuntingYardExp, numberArr);
    }

    public double[] fillNumberArr(char[] arr) {
        String temp;
        List<Double> doubleList = new ArrayList<>();
        for (int counter = 0, arrayCounter = 0; counter < arr.length; ++counter) {
            temp = "";
            while (((int) arr[counter] >= 48 && (int) arr[counter] <= 57) || (int) arr[counter] == 46) {
                temp += arr[counter++];
                if (counter == arr.length) {
                    break;
                }
            }
            if (temp.length() > 0) {
                doubleList.add(Double.parseDouble(temp));
                arrayCounter++;
            }
        }
        double[] numberArr = new double[doubleList.size()];
        for (int counter = 0; counter < doubleList.size(); ++counter) {
            numberArr[counter] = doubleList.get(counter);
        }
        return numberArr;
    }


    public char[] fillOperatorArr(char[] arr) {
        String temp = "";
        for (int counter = 0; counter < arr.length; ++counter) {
            if ((int) arr[counter] >= 40 && (int) arr[counter] <= 45 && (int) arr[counter] != 44 || (int) arr[counter] == 47) {
                temp += arr[counter];
            }
        }
        char[] operatorArr = new char[temp.length()];
        operatorArr = temp.toCharArray();
        return operatorArr;
    }

    public String infixConverter() {
        int rem = 0;
        int setOk = 0;
        char[] infixArr;
        List<Character> infixList = new LinkedList<>();
        infixArr = infix.toCharArray();
        for (int sc = 0; sc < infix.length(); ++sc) {
            infixList.add(infixArr[sc]);
        }
        for (int counter = 0, number = 48; counter < infixArr.length; ++counter, ++setOk) {
            if ((int) infixArr[counter] == 40 || (int) infixArr[counter] == 41 || (int) infixArr[counter] == 42 || (int) infixArr[counter] == 43 || (int) infixArr[counter] == 45 || (int) infixArr[counter] == 47) {
                continue;
            }
            int rc = 0;
            boolean isNumber = false;
            int startIndex = setOk;
            while (((int) infixArr[counter] >= 48 && (int) infixArr[counter] <= 57) || (int) infixArr[counter] == 46) {
                isNumber = true;
                rc++;
                counter += 1;
                if (counter == infixArr.length - 1) {
                    //counter -= 1;
                    rc++;
                    break;
                }
                if (counter == infixArr.length) {
                    break;
                }
            }
            int endIndex = counter - 1 - rem;
            if (isNumber) {
                if (startIndex == endIndex) {
                    infixList.set(setOk, (char) number++);
                    setOk++;
                } else {
                    for (; rc - 1 > 0; rc--) {
                        rem++;
                        infixList.remove(startIndex);
                    }
                    infixList.set(setOk, (char) number++);
                    setOk += 1;
                }
            }
        }
        String newInfixExp = "";
        for (int counter = 0; counter < infixList.size(); ++counter) {
            newInfixExp += infixList.get(counter);
        }
        return newInfixExp;
    }

    public String shuntingYardAlgo(String infixExpConverted) {
        Map<Character, Integer> priority = new TreeMap<>();
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('(', 0);

        String end = "";
        char[] converted = infixExpConverted.toCharArray();
        Stack stack = new Stack(infixExpConverted.length());
        String t = "";

        for (int counter = 0; counter < converted.length; ++counter) {
            if ((int) converted[counter] == 40 || (int) converted[counter] == 41 || (int) converted[counter] == 42 || (int) converted[counter] == 43 || (int) converted[counter] == 45 || (int) converted[counter] == 47) {//jezeli operator to dodaj na stos pod warunkiem...
                if (stack.isEmpty()) {
                    stack.push(converted[counter]);
                    continue;
                }
                if ((int)converted[counter] == 40){
                    stack.push(converted[counter]);
                    continue;
                }
                if ((int)converted[counter] == 41){
                    while ((int)stack.popWithoutPopping() != 40){
                        end += stack.pop();
                    }
                    t += stack.pop();//gdy znak jest lewym nawiasem
                    continue;
                }
                while (!stack.isEmpty()) {
                    if (priority.get(stack.popWithoutPopping()) >= priority.get(converted[counter])) {
                        end += stack.pop();
                    } else {
                        stack.push(converted[counter]);
                        break;
                    }
                }
            } else { //jezeli liczba to dodaj do wyjscia
                end += converted[counter];
                continue;
            }
        }
        while (!stack.isEmpty()){
            end += stack.pop();
        }
        return end;
    }

    public double shuntingYardToValue(String shuntingYardExp, double[] numbArray){
        int temp;
        double result = 0;
        double a, b;

        char[] shutntingYardArr;
        shuntingYardExp += "=";
        shutntingYardArr = shuntingYardExp.toCharArray();

        DoubleStack stack = new DoubleStack(shutntingYardArr.length);

        int counter = 0;
        int dcounter = 0;
        while (counter != shutntingYardArr.length - 1){
            if ((int) shutntingYardArr[counter] == 40 || (int) shutntingYardArr[counter] == 41 || (int) shutntingYardArr[counter] == 42 || (int) shutntingYardArr[counter] == 43 || (int) shutntingYardArr[counter] == 45 || (int) shutntingYardArr[counter] == 47){
                if (shutntingYardArr[counter] == '='){
                    result = (int)stack.pop();
                    return result;
                }
                a = stack.pop();
                b = stack.pop();
                switch (shutntingYardArr[counter]){
                    case '+':
                        result = a + b;
                        break;
                    case '-':
                        result = a - b;
                        break;
                    case '*':
                        result = a * b;
                        break;
                    case '/':
                        result = a / b;
                        break;
                }
                stack.push(result);
                ++counter;
                ++dcounter;
                continue;
            }
            stack.push(numbArray[shutntingYardArr[dcounter++] - 48]);

            ++counter;
        }
        return result;
    }
}