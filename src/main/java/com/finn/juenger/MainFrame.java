package com.finn.juenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

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
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(equationUI, "Page 1");
        mainPanel.add(vectorUI, "Page 2");

        vectorUI.equationsButton.addActionListener(this);
        equationUI.vectorFrameButton.addActionListener(this);

        this.add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vectorUI.equationsButton) {
            cardLayout.show(mainPanel, "Page 1");
        } else if (e.getSource() == equationUI.vectorFrameButton) {
            cardLayout.show(mainPanel, "Page 2");
        }
    }
}
