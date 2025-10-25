package com.finn.juenger;

import java.util.*;
import java.util.regex.*;

public class Calculator {
    //Attributes
    private Scanner scan;
    private Formula formulaCollection;

    //Binomial Attributes
    private double x;
    private int k;
    private String unequationType;
    private boolean marker = false;

    //Constructor
    public Calculator() {
        formulaCollection = new Formula();
    }

    public double linearEquation(String input) {

        double a = 0;
        double b = 0;
        double c = 0;
        double x;
        input = input.replace(" ", "");

        String[] split = input.split("x|=");

        split = Arrays.stream(split)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        if(split.length == 2) {
            a = Double.parseDouble(split[0]);
            c = Double.parseDouble(split[1]);
        }

        if(split.length == 3) {
            a = Double.parseDouble(split[0]);
            b= Double.parseDouble(split[1]);
            c = Double.parseDouble(split[2]);
        }

        c-=b;

        x = formulaCollection.linearSolving(a,c);
        return x;

    }

    public double[] quadraticEquation(String input) {
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;

        double[] results;

        input = input.replace(" ", "");
        String[] split = input.split("x\\^2|x|=");

        split = Arrays.stream(split)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        if(split.length == 4) {
            a = Double.parseDouble(split[0]);
            b = Double.parseDouble(split[1]);
            c = Double.parseDouble(split[2]);
            d = Double.parseDouble(split[3]);

            c -= d;

            results = formulaCollection.quadraticSolving(a, b, c);
        }
        else /*if(split.length == 3)*/{
            a = Double.parseDouble(split[0]);
            b = Double.parseDouble(split[1]);
            c = Double.parseDouble(split[2]) * -1;

            System.out.println("a = " + a + " b = " + b + " c = " + c);

            results = formulaCollection.quadraticSolving(a, b, c);
        }

        return results;
    }

    public double exponentialEquation(String input) {
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double x = 0;

        Pattern patternComplete = Pattern.compile("(\\+|-)?[0-9]+((\\.|,)[0-9]+)?\\*(([0-9]+((\\.|,)[0-9]+)?)|e)\\^x(\\+|-)[0-9]+((\\.|,)[0-9]+)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        Pattern patternWithoutC = Pattern.compile("(\\+|-)?[0-9]+((\\.|,)[0-9]+)?\\*(([0-9]+((\\.|,)[0-9]+)?)|e)\\^x=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        Pattern patternWithoutA = Pattern.compile("(([0-9]+(\\.[0-9]+)?)|e)\\^x(\\+|-)[0-9]+(\\.[0-9]+)?=(\\+|-)?[0-9]+(\\.[0-9]+)?");
        Pattern patternWithoutAandC = Pattern.compile("(([0-9]+((\\.|,)[0-9]+)?)|e)\\^x=(\\+|-)[0-9]+((\\.|,)[0-9]+)?");

        input = input.replace(" ", "");

        Matcher mComplete = patternComplete.matcher(input);
        Matcher mWithoutC = patternWithoutC.matcher(input);
        Matcher mWithoutA = patternWithoutA.matcher(input);
        Matcher mWithoutAandC = patternWithoutAandC.matcher(input);

        String[] split = input.split("\\*|\\^x|=");

        split = Arrays.stream(split)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        if(mComplete.matches()) {
            if(split[1].equals("e"))
                split[1] = String.valueOf(Math.E);

            a = Double.parseDouble(split[0]);
            b = Double.parseDouble(split[1]);
            c = Double.parseDouble(split[2]);
            d = Double.parseDouble(split[3]);

            d -= c;
            x = formulaCollection.exponentialSolving(a, b, d);
        } else if (mWithoutC.matches()) {
            if(split[1].equals("e"))
                split[1] = String.valueOf(Math.E);

            a = Double.parseDouble(split[0]);
            b = Double.parseDouble(split[1]);
            d = Double.parseDouble(split[2]);

            x = formulaCollection.exponentialSolving(a, b, d);
        } else if (mWithoutA.matches()) {
            if(split[0].equals("e"))
                split[0] = String.valueOf(Math.E);

            b = Double.parseDouble(split[0]);
            c = Double.parseDouble(split[1]);
            d = Double.parseDouble(split[2]);
            a = 1;
            d-=c;

            x = formulaCollection.exponentialSolving(a, b, d);
        } else /*if (mWithoutAandC.matches())*/ {
            if(split[0].equals("e"))
                split[0] = String.valueOf(Math.E);

            b = Double.parseDouble(split[0]);
            d = Double.parseDouble(split[1]);
            a = 1;

            x = formulaCollection.exponentialSolving(a, b, d);
        }
        return x;
    }

    private void binomialFormula(int n, double p, String formulaInput) {
        marker = false;

        formulaInput = formulaInput.replace(" ", "");
        formulaInput = formulaInput.substring(1);
        String[] split1 = formulaInput.split("\\(|\\)");

        split1 = Arrays.stream(split1)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        split1[0] = split1[0].substring(1,split1[0].length() - 1); // Inspiration for extended feature: Automatically knowing which parameter to search for (not only k)

        // Here comes the automatic transformation into <=

        Pattern pattern = Pattern.compile("(<=|>=|<|>)([0-9]+((\\.|,)[0-9]+)?)");
        Matcher m = pattern.matcher(split1[1]);
        if(m.matches()) {
            this.unequationType = m.group(1);
            this.x = Double.parseDouble(m.group(2));
        } else {
            throw new IllegalArgumentException("Ungültiger String: " + split1[1]);
        }

        if(split1[0].equals("<")) {
            marker = true;
        }
        if(split1[0].equals(">")) {
            if(this.unequationType.equals("<=") || this.unequationType.equals("<"))
                this.unequationType = ">";
            else
                this.unequationType = "<";
            x-=1;
            x*=-1;
        }
        if(split1[0].equals(">=")) {
            if(this.unequationType.equals("<=") || this.unequationType.equals("<"))
                this.unequationType = ">";
            else
                this.unequationType = "<";
            x-=1;
            x*=-1;
            marker = true;
        }
    }

    public int binomialProblemSolving(int n, double p, String formulaInput) {
        this.k = 0;

        binomialFormula(n, p, formulaInput);

        this.k = formulaCollection.binomialSearchForK(n, p, k, x, unequationType);
        if(marker)
            k++;
        x = 0;
        marker = false;
        return k;
    }
}
