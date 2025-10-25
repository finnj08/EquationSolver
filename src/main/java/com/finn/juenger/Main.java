package com.finn.juenger;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    static Calculator calc = new Calculator();
    static UIFrame frame;
    static String[] equationTypes = {"Select Type", "Linear Equation", "Quadratic Equation", "Exponential Equation", "Binomial Problem Solving"};

    static void main(String[] args) {
        FlatLightLaf.setup();
        javax.swing.SwingUtilities.invokeLater(() -> {
            frame = new UIFrame(equationTypes);
        });
    }
}
