package com.finn.juenger;

import java.math.BigInteger;
import java.util.ArrayList;

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

    public double vectorLength(double a1, double a2, double a3) {
        return Math.sqrt(Math.pow(a1, 2) + Math.pow(a2, 2) + Math.pow(a3, 2));
    }

    //returns an ArrayList with two float arrays inside, one for the positionVector and one for the normalVector of the plane
    public ArrayList convertParametricFormToNormalForm(float[] positionVector, float[] directionVector1, float[] directionVector2) {
        float[] normalVector = new float[3];

        normalVector[0] = directionVector1[1] * directionVector2[2] - directionVector1[2] * directionVector2[1];
        normalVector[1] = directionVector1[2] * directionVector2[0] - directionVector1[0] * directionVector2[2];
        normalVector[2] = directionVector1[0] * directionVector2[1] - directionVector1[1] * directionVector2[0];

        ArrayList result = new ArrayList();
        result.add(positionVector);
        result.add(normalVector);

        return result;
    }

    public float[] convertNormalFormToCartesianForm(float[] positionVector, float[] normalVector) {
        float[] result = new float[4];
        result[0] = normalVector[0];
        result[1] = normalVector[1];
        result[2] = normalVector[2];
        result[3] = positionVector[0] * normalVector[0] + positionVector[1] * normalVector[1] + positionVector[2] * normalVector[2];

        return result;
    }

    public ArrayList convertCartesianFormToParametricForm(float[] input) {
        ArrayList result = new ArrayList();
        float[] positionVector = new float[3];
        float[] directionVector1 = new float[3];
        float[] directionVector2 = new float[3];

        do {
            for (int i = 0; i < 3; i++) {
                positionVector[i] = (float) Math.random() * 100;
                if (i == 2) {
                    positionVector[i] = (input[3] - positionVector[0] * input[0] - positionVector[1] * input[1]) / input[2];
                }
            }
            for (int i = 0; i < 3; i++) {
                directionVector1[i] = (float) Math.random() * 100;
                if (i == 2) {
                    directionVector1[i] = (input[3] - directionVector1[0] * input[0] - directionVector1[1] * input[1]) / input[2];
                }
            }
            for (int i = 0; i < 3; i++) {
                directionVector2[i] = (float) Math.random() * 100;
                if (i == 2) {
                    directionVector2[i] = (input[3] - directionVector2[0] * input[0] - directionVector2[1] * input[1]) / input[2];
                }
            }
            for (int i = 0; i < 3; i++) {
                directionVector1[i] = directionVector1[i] - positionVector[i];
            }
            for (int i = 0; i < 3; i++) {
                directionVector2[i] = directionVector2[i] - positionVector[i];
            }
        }
        while(checkVectorsDirection(directionVector1, directionVector2));

        result.add(positionVector);
        result.add(directionVector1);
        result.add(directionVector2);

        return result;
    }

    public boolean checkVectorsDirection(float[] vector1, float[] vector2) {
        boolean state1 = vector1[0] / vector2[0] == vector1[1] / vector2[1];
        boolean state2 = (vector1[2] / vector2[2] == vector1[0] / vector2[0]) && state1;
        return state2;
    }
}
