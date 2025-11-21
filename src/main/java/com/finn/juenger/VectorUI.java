package com.finn.juenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VectorUI extends JPanel implements ActionListener{

    private Calculator calc;
    private StateMachine sm;
    private ImageIcon infoIcon = new ImageIcon(getClass().getResource("/info3.png"));
    private ImageIcon scaledIcon;
    private Font labelFont;
    private int panelDistance;
    private JLabel vectorDistanceOutputLabel;
    private JTextField distancePointAField;
    private JTextField distancePointBField;
    private JTextField vectorLengthField;
    private JButton vectorLengthButton;
    private JLabel vectorLengthOutputLabel;
    public JButton equationsButton;
    private JScrollPane scrollPane;
    private JButton vectorDistanceButton;
    private JButton clear1;
    private JButton clear2;
    private JComboBox planeType1;
    private JComboBox planeType2;
    private String[] planeTypes = {"Select Type", "Parametric Form", "Normal Form", "Cartesian Form"};
    private JTextField planeEquationField;
    private JLabel planeConversionOutputLabel;
    private JButton planeConversionButton;
    private JButton clear3;


    public VectorUI() {
        this.calc = new Calculator();
        this.sm = new StateMachine();
        this.labelFont = new Font("Arial", Font.BOLD, 15);
        this.panelDistance = 20;

        Image image = infoIcon.getImage();
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        this.scaledIcon = new ImageIcon(scaledImage);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        initComponents();
    }

    public void initComponents() {
        scrollPane = new JScrollPane(initComponentsPanel());
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

        //Methods for init the components
        this.add(initHeadPanel());
        this.add(Box.createVerticalStrut(panelDistance + 10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(panelDistance));
        this.add(initEquationsPanel());
        this.add(Box.createVerticalStrut(panelDistance));

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
        componentsPanel.add(Box.createVerticalStrut(panelDistance));
        componentsPanel.add(initVectorLengthPanel());
        componentsPanel.add(Box.createVerticalStrut(panelDistance));
        componentsPanel.add(initPlaneConversionPanel());

        return componentsPanel;
    }

    public JPanel initVectorDistancePanel() {
        JPanel vectorDistancePanel = new JPanel();
        vectorDistancePanel.setLayout(new BoxLayout(vectorDistancePanel, BoxLayout.Y_AXIS));
        //vectorDistancePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE - 80));


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
        vectorDistanceInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel vectorDistanceInfoLabel = new JLabel("Type in two points you want to calculate the distance between:");
        vectorDistanceInfoLabel.setFont(labelFont);

        JLabel infoIconLabel = new JLabel();
        infoIconLabel.setIcon(scaledIcon);
        infoIconLabel.setToolTipText("Type in your points like this: A(a1/a2/a3)");

        vectorDistanceInfoPanel.add(vectorDistanceInfoLabel);
        vectorDistanceInfoPanel.add(infoIconLabel);

        return vectorDistanceInfoPanel;
    }

    public JPanel initVectorDistanceInputPanel() {
        JPanel vectorDistanceInputPanel = new JPanel();
        vectorDistanceInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        vectorDistanceInputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel pointA = new JLabel("Point A:");
        pointA.setFont(labelFont);

        distancePointAField = new JTextField();
        distancePointAField.setPreferredSize(new Dimension(150,25));
        distancePointAField.putClientProperty("JComponent.roundRect", true);
        distancePointAField.addActionListener(this);

        JLabel pointB = new JLabel("Point B:");
        pointB.setFont(labelFont);

        distancePointBField = new JTextField();
        distancePointBField.setPreferredSize(new Dimension(150,25));
        distancePointBField.putClientProperty("JComponent.roundRect", true);
        distancePointBField.addActionListener(this);

        vectorDistanceButton = new JButton("Submit");
        vectorDistanceButton.addActionListener(this);

        clear1 = new JButton("Clear");
        clear1.addActionListener(this);

        vectorDistanceInputPanel.add(pointA);
        vectorDistanceInputPanel.add(distancePointAField);
        vectorDistanceInputPanel.add(pointB);
        vectorDistanceInputPanel.add(distancePointBField);
        vectorDistanceInputPanel.add(vectorDistanceButton);
        vectorDistanceInputPanel.add(clear1);

        return vectorDistanceInputPanel;
    }

    public JPanel initVectorDistanceOutputPanel() {
        JPanel vectorDistanceOutputPanel = new JPanel();
        vectorDistanceOutputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 0));
        vectorDistanceOutputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        vectorDistanceOutputPanel.setPreferredSize(new Dimension(0, 80)); // Feste Höhe

        this.vectorDistanceOutputLabel = new JLabel("");
        this.vectorDistanceOutputLabel.setFont(labelFont);

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
        String pointA = distancePointAField.getText();
        String pointB = distancePointBField.getText();
        pointA = pointA.replace(" ", "");
        pointA = pointA.replace(",", ".");
        pointB = pointB.replace(" ", "");
        pointB = pointB.replace(",", ".");

        if(sm.vectorPointPatternMatches(pointA) && sm.vectorPointPatternMatches(pointB)) {
            double distance = calc.distanceBetweenVectorsSolving(pointA, pointB);
            vectorDistanceOutputLabel.setText("Distance:     " + String.valueOf(distance));
        } else {
            vectorDistanceOutputLabel.setText("Please type in your points like this: A(a1/a2/a3)");
        }
    }

    public JPanel initVectorLengthPanel() {
        JPanel vectorLengthPanel = new JPanel();
        vectorLengthPanel.setLayout(new BoxLayout(vectorLengthPanel, BoxLayout.Y_AXIS));

        vectorLengthPanel.add(initVectorLengthInfoPanel());
        vectorLengthPanel.add(Box.createVerticalStrut(panelDistance));
        vectorLengthPanel.add(initVectorLengthInputPanel());
        vectorLengthPanel.add(Box.createVerticalStrut(panelDistance));
        vectorLengthPanel.add(initVectorLengthOutputPanel());

        return vectorLengthPanel;
    }

    public JPanel initVectorLengthInfoPanel() {
        JPanel vectorLengthInfoPanel = new JPanel();
        vectorLengthInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        vectorLengthInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel vectorLengthInfoLabel = new JLabel("Type in a vector of which you want to calculate the length:");
        vectorLengthInfoLabel.setFont(labelFont);

        JLabel infoIconLabel = new JLabel();
        infoIconLabel.setIcon(scaledIcon);
        infoIconLabel.setToolTipText("Type in your points like this: v = (a1/a2/a3)");

        vectorLengthInfoPanel.add(vectorLengthInfoLabel);
        vectorLengthInfoPanel.add(infoIconLabel);

        return vectorLengthInfoPanel;
    }

    public JPanel initVectorLengthInputPanel() {
        JPanel vectorLengthInputPanel = new JPanel();
        vectorLengthInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        vectorLengthInputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel vectorLengthInputLabel = new JLabel("Vector:");
        vectorLengthInputLabel.setFont(labelFont);

        vectorLengthField = new JTextField();
        vectorLengthField.setPreferredSize(new Dimension(150,25));
        vectorLengthField.putClientProperty("JComponent.roundRect", true);
        vectorLengthField.addActionListener(this);

        vectorLengthButton = new JButton("Submit");
        vectorLengthButton.addActionListener(this);

        clear2 = new JButton("Clear");
        clear2.addActionListener(this);

        vectorLengthInputPanel.add(vectorLengthInputLabel);
        vectorLengthInputPanel.add(vectorLengthField);
        vectorLengthInputPanel.add(vectorLengthButton);
        vectorLengthInputPanel.add(clear2);

        return vectorLengthInputPanel;
    }

    public JPanel initVectorLengthOutputPanel() {
        JPanel vectorLengthOutputPanel = new JPanel();
        vectorLengthOutputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 0));
        vectorLengthOutputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        vectorLengthOutputPanel.setPreferredSize(new Dimension(0, 80)); // Feste Höhe

        vectorLengthOutputLabel = new JLabel("");
        vectorLengthOutputLabel.setFont(labelFont);

        vectorLengthOutputPanel.add(vectorLengthOutputLabel);
        return vectorLengthOutputPanel;
    }

    public void calculateVectorLength() {
        String input = vectorLengthField.getText();
        input = input.replace(" ", "");
        input = input.replace(",", ".");

        if (sm.vectorPatternMatches(input)) {
            double length = calc.vectorLengthSolving(input);
            vectorLengthOutputLabel.setText("Length:     " + String.valueOf(length));
        } else {
            vectorLengthOutputLabel.setText("Please type in your vectors like this: v = (a1/a2/a3)");
        }
    }

    public JPanel initPlaneConversionPanel() {
        JPanel planeConversionPanel = new JPanel();
        planeConversionPanel.setLayout(new BoxLayout(planeConversionPanel, BoxLayout.Y_AXIS));

        //Methods for initialing the components
        planeConversionPanel.add(initPlaneConversionInfoPanel());
        planeConversionPanel.add(Box.createVerticalStrut(panelDistance));
        planeConversionPanel.add(initPlaneConversionInputPanel());
        planeConversionPanel.add(Box.createVerticalStrut(panelDistance));
        planeConversionPanel.add(initPlaneConversionOutputPanel());

        return planeConversionPanel;
    }

    public JPanel initPlaneConversionInfoPanel() {
        JPanel planeConversionInfoPanel = new JPanel();
        planeConversionInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        planeConversionInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel planeConversionInfoLabel = new JLabel("Please select your preferred planes you want to convert and type in your plane: ");
        planeConversionInfoLabel.setFont(labelFont);

        planeConversionInfoPanel.add(planeConversionInfoLabel);

        return planeConversionInfoPanel;
    }

    public JPanel initPlaneConversionInputPanel() {
        JPanel planeConversionInputPanel = new JPanel();
        planeConversionInputPanel.setLayout(new BoxLayout(planeConversionInputPanel, BoxLayout.Y_AXIS));
        planeConversionInputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel selection1 = new JPanel();
        selection1.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        selection1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel input = new JPanel();
        input.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        input.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JPanel selection2 = new JPanel();
        selection2.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 0));
        selection2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel planeType1Label = new JLabel("Plane Type: ");
        planeType1Label.setFont(labelFont);

        planeType1 = new JComboBox(planeTypes);
        planeType1.setMaximumSize(new Dimension(200, 50));

        JLabel infoIcon = new JLabel();
        infoIcon.setIcon(scaledIcon);
        if (planeType1.getSelectedItem().equals(planeTypes[1])) {
            infoIcon.setToolTipText("E: x = (a1/a2/a3) + t * (b1/b2/b3) + s * (c1/c2/c3");
        } else if (planeType1.getSelectedItem().equals(planeTypes[2])) {
            infoIcon.setToolTipText("E: x = [(a1/a2/a3) - (a1/a2/a3)] * n(n1/n2/n3) = 0");
        } else if (planeType1.getSelectedItem().equals(planeTypes[3])) {
            infoIcon.setToolTipText("E: ax1 + bx2 + bx3 = c");
        } else if (planeType1.getSelectedItem().equals(planeTypes[0])) {
            infoIcon.setToolTipText("Please select a type of plane in the dropdown list.");
        }

        JLabel planeEquationLabel = new JLabel("Type in your plane equation:");
        planeEquationLabel.setFont(labelFont);

        planeEquationField = new JTextField();
        planeEquationField.setPreferredSize(new Dimension(150,25));
        planeEquationField.putClientProperty("JComponent.roundRect", true);
        planeEquationField.addActionListener(this);

        planeConversionButton = new JButton("Submit");
        planeConversionButton.addActionListener(this);

        clear3 = new JButton("Clear");
        clear3.addActionListener(this);

        JLabel planeType2Label = new JLabel("convert into:");
        planeType2Label.setFont(labelFont);

        planeType2 = new JComboBox(planeTypes);
        planeType2.setMaximumSize(new Dimension(200, 50));

        selection1.add(planeType1Label);
        selection1.add(planeType1);
        selection1.add(infoIcon);
        input.add(planeEquationLabel);
        input.add(planeEquationField);
        input.add(planeConversionButton);
        input.add(clear3);
        selection2.add(planeType2Label);
        selection2.add(planeType2);

        planeConversionInputPanel.add(selection1);
        planeConversionInputPanel.add(Box.createVerticalStrut(panelDistance));
        planeConversionInputPanel.add(input);
        planeConversionInputPanel.add(Box.createVerticalStrut(panelDistance));
        planeConversionInputPanel.add(selection2);


        return planeConversionInputPanel;
    }

    public JPanel initPlaneConversionOutputPanel() {
        JPanel planeConversionOutputPanel = new JPanel();
        planeConversionOutputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 17, 0));
        planeConversionOutputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        planeConversionOutputPanel.setPreferredSize(new Dimension(0, 80));

        planeConversionOutputLabel = new JLabel();
        planeConversionOutputLabel.setFont(labelFont);

        return planeConversionOutputPanel;
    }

    //Method not finished
    public void calculatePlaneConversion() {
        String input = planeEquationField.getText();
        input = input.replace(" ", "");
        input = input.replace(",", ".");

        if (planeType1.getSelectedItem() == planeTypes[0] | planeType2.getSelectedItem() == planeTypes[1]) {
            planeConversionOutputLabel.setText("Please select one of the plane types at each of the dropdown lists");
        } else if (planeType1.getSelectedItem() == planeTypes[1]) {
            if(sm.parametricFormPlaneMatches(input)) {
                if (planeType2.getSelectedItem() == planeTypes[2]) {
                    planeConversionOutputLabel.setText(calc.convertParametricFormToNormalForm(input));
                } else if (planeType2.getSelectedItem() == planeTypes[3]) {
                    planeConversionOutputLabel.setText(calc.convertNormalFormToCartesianForm(calc.convertParametricFormToNormalForm(input)));
                }
            } else {
                planeConversionOutputLabel.setText("Please type in your plane equation correctly (Info Icon)");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == distancePointBField || e.getSource() == vectorDistanceButton || e.getSource() == distancePointAField) {
            calculateVectorDistance();
        }
        if (e.getSource() == clear1) {
            distancePointAField.setText("");
            distancePointBField.setText("");
        }
        if (e.getSource() == vectorLengthField || e.getSource() == vectorLengthButton) {
            calculateVectorLength();
        }
        if (e.getSource() == clear2) {
            vectorLengthField.setText("");
        }
        if (e.getSource() == planeEquationField | e.getSource() == planeConversionButton) {
            calculatePlaneConversion();
        }
        if (e.getSource() == clear3) {
            planeEquationField.setText("");
        }
    }
}
