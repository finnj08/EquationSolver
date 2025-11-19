package com.finn.juenger;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    static Calculator calc = new Calculator();
    static MainFrame frame;

    public static void main(String[] args) {
        FlatLightLaf.setup();
        javax.swing.SwingUtilities.invokeLater(() -> {
            frame = new MainFrame();
        });
    }
}
