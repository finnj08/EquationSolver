package com.finn.juenger;
import java.util.*;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    static Calculator calc = new Calculator();
    static Scanner scan = new Scanner(System.in);
    static UIFrame frame;
    static String[] equationTypes = {"Select Type", "Linear Equation", "Quadratic Equation", "Binomial Problem Solving"};

    static void main(String[] args) {
        FlatLightLaf.setup();
        javax.swing.SwingUtilities.invokeLater(() -> {
            frame = new UIFrame(equationTypes);
        });
    }
}
