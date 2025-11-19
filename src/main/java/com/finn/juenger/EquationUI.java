package com.finn.juenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquationUI extends JPanel implements ActionListener {

    private Calculator calc;
    private StateMachine sm;
    private int panelDistances;
    private Font labelFont;
    private JComboBox equationType;
    private JTextField inputField;
    private JLabel output;
    private JPanel binomialParametersPanel;
    private JTextField parameterFieldP;
    private JTextField parameterFieldN;
    private JLabel parameterP;
    private JLabel parameterN;
    public String input;
    public JButton vectorFrameButton;
    private String[] equationTypes = {"Select Type", "Linear Equation", "Quadratic Equation", "Exponential Equation", "Binomial Problem Solving"};
    private JButton inputSubmit;
    private JButton inputClear;

    public EquationUI() {
        calc = new Calculator();
        sm = new StateMachine();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.labelFont = new Font("Arial", Font.BOLD, 15);
        this.panelDistances = 10;

        initComponents(equationTypes);
        //this.setVisible(true);
    }

    private void initComponents(String[] equationTypes) {
        this.add(initHeadPanel());
        this.add(Box.createVerticalStrut(panelDistances + 10));
        this.add(initSelectionPanel(equationTypes));
        this.add(Box.createVerticalStrut(panelDistances));
        this.add(initInputPanel());
        this.add(Box.createVerticalStrut(panelDistances));
        this.add(initOutputPanel());
        this.add(Box.createVerticalStrut(panelDistances));
        this.add(initBinomialParametersPanel());
        this.add(Box.createVerticalStrut(panelDistances));
        this.add(initVectorPanel());
        binomialParametersPanel.setVisible(true);
        setBinomialComponentsVisible(false);
    }

    private JPanel initHeadPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setLayout(new GridBagLayout());

        JLabel headLine = new JLabel("Equation Solver");
        headLine.setFont(new Font("Arial", Font.BOLD, 25));

        headPanel.setBackground(new Color(0xFF9700));
        headPanel.setPreferredSize(new Dimension(0, 50));  // Höhe = 50px
        headPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Höhe = 50px
        headPanel.setMinimumSize(new Dimension(0, 50));
        headPanel.add(headLine);

        return headPanel;
    }

    private JPanel initSelectionPanel(String[] equationTypes) {
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        selectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel selectEquationType = new JLabel("Please select your prefered type of the equation you want to solve:");
        selectEquationType.setFont(labelFont);

        equationType = new JComboBox(equationTypes);
        equationType.setMaximumSize(new Dimension(200, 50));
        equationType.addActionListener(this);

        selectionPanel.add(selectEquationType);
        selectionPanel.add(equationType);

        //selectionPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return selectionPanel;
    }

    private JPanel initInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel inputInfo = new JLabel("Please type in your equation here:");
        inputInfo.setFont(labelFont);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200,25));
        inputField.putClientProperty("JComponent.roundRect", true);
        inputField.addActionListener(this);

        inputSubmit = new JButton("Submit");
        inputSubmit.addActionListener(this);

        inputClear = new JButton("Clear");
        inputClear.addActionListener(this);

        inputPanel.add(inputInfo);
        inputPanel.add(inputField);
        inputPanel.add(inputSubmit);
        inputPanel.add(inputClear);

        //inputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return inputPanel;
    }

    private JPanel initOutputPanel() {
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 0));
        outputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        output = new JLabel("");
        output.setFont(labelFont);

        outputPanel.add(output);

        //outputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return outputPanel;
    }

    private JPanel initBinomialParametersPanel() {
        binomialParametersPanel = new JPanel();
        binomialParametersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        binomialParametersPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        binomialParametersPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 80));

        parameterN = new JLabel("n = ");
        parameterN.setFont(labelFont);

        parameterP = new JLabel("p = ");
        parameterP.setFont(labelFont);

        parameterFieldP = new JTextField();
        parameterFieldP.setPreferredSize(new Dimension(50,25));
        parameterFieldP.putClientProperty("JComponent.roundRect", true);
        parameterFieldP.addActionListener(this);

        parameterFieldN = new JTextField();
        parameterFieldN.setPreferredSize(new Dimension(50,25));
        parameterFieldN.putClientProperty("JComponent.roundRect", true);
        parameterFieldN.addActionListener(this);

        binomialParametersPanel.add(parameterN);
        binomialParametersPanel.add(parameterFieldN);
        binomialParametersPanel.add(parameterP);
        binomialParametersPanel.add(parameterFieldP);

        //binomialParametersPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return binomialParametersPanel;
    }

    private JPanel initVectorPanel() {
        JPanel vectorPanel = new JPanel();
        vectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        vectorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        vectorFrameButton = new JButton("Vector Calculations");

        vectorPanel.add(vectorFrameButton);

        return vectorPanel;
    }

    private void setBinomialComponentsVisible(boolean visible) {
        parameterN.setVisible(visible);
        parameterFieldN.setVisible(visible);
        parameterP.setVisible(visible);
        parameterFieldP.setVisible(visible);

    }

    private void calculate() {
        input = inputField.getText();
        input = input.replace(" ", "");
        input = input.replace(",", ".");

        if (equationType.getSelectedItem().equals("Select Type")) {
            output.setText("Please select a type of equation in the dropdown list.");
        } else if (equationType.getSelectedItem().equals("Linear Equation")) {
            if(sm.linearPatternMatches(input)) {
                output.setText(String.valueOf("Result:     x = " + calc.linearEquation(input)));
            } else {
                output.setText("Please type in the equation in a regular form.");
            }
        } else if (equationType.getSelectedItem().equals("Quadratic Equation")) {
            if(sm.quadraticPatternMatches(input)) {
                if(calc.quadraticEquation(input)[0] == calc.quadraticEquation(input)[1]) {
                    output.setText("This Equation has no solution: L = { }");
                } else {
                    output.setText(String.valueOf("Result:     x1 = " + calc.quadraticEquation(input)[0] + "     x2 = " + calc.quadraticEquation(input)[1]));
                }
            } else {
                output.setText("Please type in the equation in a regular form.");
            }
        } else if (equationType.getSelectedItem().equals("Binomial Problem Solving")) {
            try {
                int parameterN = Integer.parseInt(parameterFieldN.getText());
                String parameterPstring = parameterFieldP.getText();
                parameterPstring = parameterPstring.replace(",",".");
                double parameterP = Double.parseDouble(parameterPstring);

                if (sm.binomialFormulaPatternWithKMatches(input) && parameterN >= 1 && parameterP < 1 && parameterP > 0) {
                    output.setText("Result:     k = " + String.valueOf(calc.binomialProblemSolving(parameterN, parameterP, input)));
                } else if(sm.binomialFormulaPatternWithGMatches(input) && parameterN >= 1 && parameterP < 1 && parameterP > 0) {
                    output.setText("Result:     g = " + String.valueOf(calc.binomialProblemSolving(parameterN, parameterP, input)));
                } else {
                    output.setText("Please type in the equation in a regular form.");
                }
            } catch (Exception e) {
                output.setText("Please type in analogous parameters.");
            }
        } else if (equationType.getSelectedItem().equals("Exponential Equation")) {
            if (sm.ExponentialPatternMatches(input)) {
                if(Double.isNaN(calc.exponentialEquation(input))) {
                    output.setText("This Equation has no solution: L = { }");
                } else {
                    output.setText("Result:     x = " + calc.exponentialEquation(input));
                }
            }
            else {
                output.setText("Please type in the equation in a regular form.");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputSubmit || e.getSource() == inputField || e.getSource() == parameterFieldN || e.getSource() == parameterFieldP) {
            calculate();
        } else if (e.getSource() == inputClear) {
            inputField.setText("");
            parameterFieldN.setText("");
            parameterFieldP.setText("");
        } else if (e.getSource() == equationType) {
            setBinomialComponentsVisible(equationType.getSelectedItem() == "Binomial Problem Solving");
            equationType.getParent().getParent().revalidate();
            equationType.getParent().getParent().repaint();
        }
    }
}