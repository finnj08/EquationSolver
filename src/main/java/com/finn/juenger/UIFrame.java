package com.finn.juenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.*;

public class UIFrame extends JFrame {
    Calculator calc = new Calculator();
    ImageIcon icon = new ImageIcon(getClass().getResource("/calc.png"));
    private final int panelDistances  = 50;
    private final Font labelFont = new Font("Arial", Font.BOLD, 15);
    private JComboBox equationType;
    private JTextField inputField;
    private JLabel output;
    private JPanel binomialParametersPanel;
    private JTextField parameterFieldP;
    private JTextField parameterFieldN;
    public String input;
    private Pattern linearPattern = Pattern.compile("(\\+|-)?[0-9]+(\\.[0-9]+)?x((\\+|-)[0-9]+(\\.[0-9]+)?)?=(\\+|-)?[0-9]+(\\.[0-9]+)?");
    private Pattern quadraticPattern = Pattern.compile("(\\+|-)?[0-9]+(\\.[0-9]+)?x\\^2(\\+|-)[0-9]+(\\.[0-9]+)?x((\\+|-)[0-9]+(\\.[0-9]+)?)?=(\\+|-)?[0-9]+(\\.[0-9]+)?");
    private Pattern binomialFormulaPattern = Pattern.compile("P\\(X(<|>)=?(g|k)\\)(<|<)=?0\\.[0-9]+");
    private Matcher m;

    public UIFrame(String[] equationTypes) {
        this.setTitle("Equation Solver");
        this.setBounds(300, 100, 800, 500);
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        initComponents(equationTypes);
        this.setVisible(true);
    }

    private void initComponents(String[] equationTypes) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(initHeadPanel());
        panel.add(Box.createVerticalStrut(20));
        //panel.add(Box.createVerticalGlue());
        panel.add(initSelectionPanel(equationTypes));
        panel.add(Box.createVerticalStrut(20));
        //panel.add(Box.createVerticalGlue());
        panel.add(initInputPanel());
        panel.add(Box.createVerticalStrut(20));
        //panel.add(Box.createVerticalGlue());
        panel.add(initOutputPanel());
        panel.add(Box.createVerticalStrut(20));
        //panel.add(Box.createVerticalGlue());
        panel.add(initBinomialParametersPanel());
        binomialParametersPanel.setVisible(false);

        this.add(panel);
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

    private JPanel initSpacePanel() {
        JPanel spacePanel = new JPanel();
        spacePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        return spacePanel;
    }

    private JPanel initSelectionPanel(String[] equationTypes) {
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        selectionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel selectEquationType = new JLabel("Please select your prefered type of the equation you want to solve:");
        selectEquationType.setFont(labelFont);

        equationType = new JComboBox(equationTypes);
        equationType.setMaximumSize(new Dimension(200, 50));
        equationType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binomialParametersPanel.setVisible(equationType.getSelectedItem() == "Binomial Problem Solving");

                equationType.getParent().getParent().revalidate();
                equationType.getParent().getParent().repaint();
            }
        });

        selectionPanel.add(selectEquationType);
        selectionPanel.add(equationType);

        //selectionPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return selectionPanel;
    }

    private JPanel initInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel inputInfo = new JLabel("Please type in your equation here:");
        inputInfo.setFont(labelFont);

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200,25));
        inputField.putClientProperty("JComponent.roundRect", true);
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        JButton inputSubmit = new JButton("Submit");
        inputSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        JButton inputClear = new JButton("Clear");
        inputClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                parameterFieldN.setText("");
                parameterFieldP.setText("");
            }
        });

        inputPanel.add(inputInfo);
        inputPanel.add(inputField);
        inputPanel.add(inputSubmit);
        inputPanel.add(inputClear);

        //inputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return inputPanel;
    }

    private JPanel initOutputPanel() {
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        outputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        output = new JLabel("");
        output.setFont(labelFont);

        outputPanel.add(output);

        //outputPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return outputPanel;
    }

    private JPanel initBinomialParametersPanel() {
        binomialParametersPanel = new JPanel();
        binomialParametersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        binomialParametersPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel parameterN = new JLabel("n = ");
        parameterN.setFont(labelFont);

        JLabel parameterP = new JLabel("p = ");
        parameterP.setFont(labelFont);

        parameterFieldP = new JTextField();
        parameterFieldP.setPreferredSize(new Dimension(50,25));
        parameterFieldP.putClientProperty("JComponent.roundRect", true);

        parameterFieldN = new JTextField();
        parameterFieldN.setPreferredSize(new Dimension(50,25));
        parameterFieldN.putClientProperty("JComponent.roundRect", true);

        binomialParametersPanel.add(parameterN);
        binomialParametersPanel.add(parameterFieldN);
        binomialParametersPanel.add(parameterP);
        binomialParametersPanel.add(parameterFieldP);

        //binomialParametersPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));

        return binomialParametersPanel;
    }

    private void calculate() {
        input = inputField.getText();

        if (equationType.getSelectedItem().equals("Select Type")) {
            output.setText("Please select a type of equation in the dropdown list.");
        } else if (equationType.getSelectedItem().equals("Linear Equation")) {
            if(patternMatches(linearPattern, input, m)) {
                output.setText(String.valueOf("Result:     x = " + calc.linearEquation(input)));
            } else {
                output.setText("Please type in the equation in a regular form.");
            }
        } else if (equationType.getSelectedItem().equals("Quadratic Equation")) {
            if(patternMatches(quadraticPattern, input, m)) {
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
                double parameterP = Double.parseDouble(parameterFieldP.getText());

                if (patternMatches(binomialFormulaPattern, input, m) && parameterN >= 1 && parameterP < 1 && parameterP > 0) {
                    output.setText("Result:     k = " + String.valueOf(calc.binomialProblemSolving(parameterN, parameterP, input)));
                } else {
                    output.setText("Please type in the equation in a regular form.");
                }
            } catch (Exception e) {
                output.setText("Please type in analogous parameters.");
            }
        }
    }

    private boolean patternMatches(Pattern p, String s, Matcher m) {
        return p.matcher(s).matches();
    }
}
