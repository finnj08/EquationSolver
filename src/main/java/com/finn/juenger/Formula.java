package com.finn.juenger;

import java.math.BigInteger;

public class Formula {
    public double linearSolving (double a, double b) {
        return b / a;
    }

    public double[] quadraticSolving (double a, double b, double c) {
        double[] results = new double[2];
        if((Math.pow(b,2)-4*a*c) >= 0) {
            results[0] = ((-b + (Math.sqrt((Math.pow(b, 2) - 4 * a * c)))) / (2 * a));
            results[1] = ((-b - (Math.sqrt((Math.pow(b, 2) - 4 * a * c)))) / (2 * a));
        }

        return results;
    }

    public double exponentialSolving (double a, double b, double d) {
        d = d / a;
        return Math.log(d) / Math.log(b);
    }

    public long faculty(int a) {
        long result = 1;
        for(int i = a; i > 1; i--) {
            result*=i;
        }
        return result;
    }

    public BigInteger binomialCoefficient(int n, int k) {
        BigInteger result = BigInteger.ONE;
        if(n-k < k)
            k = n-k;
        for(int i = 1; i < k+1; i++) {
            result = result.multiply(BigInteger.valueOf(n - (k - i)));
            result = result.divide(BigInteger.valueOf(i));
        }
        return result;
    }

    public double binomialPDF(int n, double p, int k) {
        BigInteger coeff = binomialCoefficient(n, k);
        return coeff.doubleValue() * Math.pow(p,k) * Math.pow((1-p),(n-k));
    }

    public double binomialCDF(int n, double p, int k) {
        double result = 0;
        for(int i = 0; i < k+1; i++) {
            result+=this.binomialPDF(n,p,i);
        }
        return result;
    }

    public int binomialSearchForK(int n, double p, int k, double x, String type) {
        if(type.equals("<") || type.equals("<=")) {
            for(int i = 1; i < n; i++) {
                if(binomialCDF(n,p,i) > x) {
                    k = i - 1;
                    break;
                }
            }
            return k;
        } else if(type.equals(">") || type.equals(">=")) {
            for(int i = 1; i < n; i++) {
                if(binomialCDF(n,p,i) > x) {
                    k = i;
                    break;
                }
            }
            return k;
        }
        else {
            return 0;
        }
    }

    public double distanceBetween2Vectors(double a1, double a2, double a3, double b1, double b2, double b3) {
        return Math.sqrt((double) (Math.pow(b1-a1, 2) + Math.pow(b2-a2, 2) + Math.pow(b3-a3, 2)));
    }
}
