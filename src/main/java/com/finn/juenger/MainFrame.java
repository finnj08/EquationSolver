package com.finn.juenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private ImageIcon icon = new ImageIcon(getClass().getResource("/calc.png"));
    private VectorUI vectorUI;
    private EquationUI equationUI;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainFrame() {
        this.setTitle("Equation Solver");
        this.setBounds(300, 100, 800, 500);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        vectorUI = new VectorUI();
        equationUI = new EquationUI();


        initComponents();
        this.setVisible(true);
    }

    public void initComponents() {
        CardLayout cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(equationUI, "Page 1");
        mainPanel.add(vectorUI, "Page 2");

        vectorUI.equationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Page 1");
            }
        });
        equationUI.vectorFrameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Page 2");
            }
        });

        this.add(mainPanel);
    }
}
