package com.finn.juenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VectorUI extends JPanel{

    private Calculator calc;
    private StateMachine sm;
    private ImageIcon infoIcon = new ImageIcon(getClass().getResource("/info3.png"));
    private ImageIcon scaledIcon;
    private Font labelFont;
    private int panelDistance;
    private JLabel vectorDistanceOutputLabel;
    private JTextField pointAField;
    private JTextField pointBField;
    public JButton equationsButton;
    private JScrollPane scrollPane;

    public VectorUI() {
        this.calc = new Calculator();
        this.sm = new StateMachine();
        this.labelFont = new Font("Arial", Font.BOLD, 15);
        this.panelDistance = 40;

        Image image = infoIcon.getImage();
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.scaledIcon = new ImageIcon(scaledImage);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initComponents();
    }

    public void initComponents() {
        scrollPane = new JScrollPane(initComponentsPanel());
        scrollPane.setBorder(null);

        //Methods for init the components
        this.add(initHeadPanel());
        this.add(Box.createVerticalStrut(panelDistance + 10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(panelDistance));
        this.add(initEquationsPanel());

    }

    public JPanel initHeadPanel() {
        JPanel headPanel = new JPanel();
        headPanel.setLayout(new GridBagLayout());

        JLabel headLine = new JLabel("Vector Calculations");
        headLine.setFont(new Font("Arial", Font.BOLD, 25));

        headPanel.setBackground(new Color(0xFF9700));
        headPanel.setPreferredSize(new Dimension(0, 50));  // Höhe = 50px
        headPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // Höhe = 50px
        headPanel.setMinimumSize(new Dimension(0, 50));
        headPanel.add(headLine);

        return headPanel;
    }

    public JPanel initComponentsPanel() {
        JPanel componentsPanel = new JPanel();
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));

        componentsPanel.add(initVectorDistancePanel());

        return componentsPanel;
    }

    public JPanel initVectorDistancePanel() {
        JPanel vectorDistancePanel = new JPanel();
        vectorDistancePanel.setLayout(new BoxLayout(vectorDistancePanel, BoxLayout.Y_AXIS));

        vectorDistancePanel.add(initVectorDistanceInfoPanel());
        vectorDistancePanel.add(Box.createVerticalStrut(panelDistance));
        vectorDistancePanel.add(initVectorDistanceInputPanel());
        vectorDistancePanel.add(Box.createVerticalStrut(panelDistance));
        vectorDistancePanel.add(initVectorDistanceOutputPanel());

        return vectorDistancePanel;
    }

    public JPanel initVectorDistanceInfoPanel() {
        JPanel vectorDistanceInfoPanel = new JPanel();
        vectorDistanceInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        vectorDistanceInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel vectorDistanceInfoLabel = new JLabel("Type in two points you want to calculate the distance between:");
        vectorDistanceInfoLabel.setFont(labelFont);

        JLabel infoIconLabel = new JLabel();
        infoIconLabel.setIcon(scaledIcon);
        infoIconLabel.setToolTipText("Type in your vectors like this: A(a1/a2/a3)");

        vectorDistanceInfoPanel.add(vectorDistanceInfoLabel);
        vectorDistanceInfoPanel.add(infoIconLabel);

        return vectorDistanceInfoPanel;
    }

    public JPanel initVectorDistanceInputPanel() {
        JPanel vectorDistanceInputPanel = new JPanel();
        vectorDistanceInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        vectorDistanceInputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel pointA = new JLabel("Point A:");
        pointA.setFont(labelFont);

        pointAField = new JTextField();
        pointAField.setPreferredSize(new Dimension(200,25));
        pointAField.putClientProperty("JComponent.roundRect", true);
        pointAField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateVectorDistance();
            }
        });

        JLabel pointB = new JLabel("Point B:");
        pointB.setFont(labelFont);

        pointBField = new JTextField();
        pointBField.setPreferredSize(new Dimension(200,25));
        pointBField.putClientProperty("JComponent.roundRect", true);
        pointBField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateVectorDistance();
            }
        });

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateVectorDistance();
            }
        });

        JButton clear = new JButton("Clear");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pointAField.setText("");
                pointBField.setText("");
            }
        });

        vectorDistanceInputPanel.add(pointA);
        vectorDistanceInputPanel.add(pointAField);
        vectorDistanceInputPanel.add(pointB);
        vectorDistanceInputPanel.add(pointBField);
        vectorDistanceInputPanel.add(submit);
        vectorDistanceInputPanel.add(clear);

        return vectorDistanceInputPanel;
    }

    public JPanel initVectorDistanceOutputPanel() {
        JPanel vectorDistanceOutputPanel = new JPanel();
        vectorDistanceOutputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 0));

        vectorDistanceOutputLabel = new JLabel("");
        vectorDistanceOutputLabel.setFont(labelFont);

        vectorDistanceOutputPanel.add(vectorDistanceOutputLabel);

        return vectorDistanceOutputPanel;
    }

    public JPanel initEquationsPanel() {
        JPanel equationsPanel = new JPanel();
        equationsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));

        equationsButton = new JButton("Equation Solver");

        equationsPanel.add(equationsButton);

        return equationsPanel;
    }

    public void calculateVectorDistance() {
        String pointA = pointAField.getText();
        String pointB = pointBField.getText();
        pointA = pointA.replace(" ", "");
        pointB = pointB.replace(" ", "");

        if(sm.vectorPointPatternMatches(pointA)) {
            double distance = calc.distanceBetweenVectorsSolving(pointA, pointB);
            vectorDistanceOutputLabel.setText("Distance:     " + String.valueOf(distance));
        } else {
            vectorDistanceOutputLabel.setText("Please type in your vectors like this: A(a1/a2/a3)");
        }
    }
}
