package com.finn.juenger;

import java.util.regex.Pattern;

public class StateMachine {

    //Attributes (regex)
    private Pattern linearPattern;
    private Pattern quadraticPattern;
    private Pattern exponentialPattern;
    private Pattern binomialFormulaPatternWithK;
    private Pattern binomialFormulaPatternWithG;
    private Pattern vectorPointPattern;
    private Pattern vectorPattern;
    private String parametricFormPlane;
    private Pattern parametricFormPlanePattern;
    private String normalFormPlane;
    private Pattern normalFormPlanePattern;
    private String cartesianFormPlane;
    private Pattern cartesianFormPlanePattern;
    private String vector;
    private String floatNumber;

    //Constructor
    public StateMachine() {
        this.floatNumber = "([-+])?[0-9]+(([.,])[0-9]+)?";
        this.linearPattern = Pattern.compile("(\\+|-)?[0-9]+((\\.|,)[0-9]+)?x((\\+|-)[0-9]+((\\.|,)[0-9]+)?)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        this.quadraticPattern = Pattern.compile("(\\+|-)?[0-9]+((\\.|,)[0-9]+)?x\\^2(\\+|-)[0-9]+((\\.|,)[0-9]+)?x((\\+|-)[0-9]+((\\.|,)[0-9]+)?)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        this.exponentialPattern = Pattern.compile("((\\+|-)?[0-9]+((\\.|,)[0-9]+)?\\*)?(([0-9]+((\\.|,)[0-9]+)?)|e)\\^x((\\+|-)[0-9]+((\\.|,)[0-9]+)?)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        this.binomialFormulaPatternWithK = Pattern.compile("P\\(X(<|>)=?k\\)(<|<)=?0(\\.|,)[0-9]+");
        this.binomialFormulaPatternWithG = Pattern.compile("P\\(X(<|>)=?g\\)(<|<)=?0(\\.|,)[0-9]+");
        this.vector = "\\("+ floatNumber + "/"+ floatNumber + "/" + floatNumber + "\\)";
        this.vectorPointPattern = Pattern.compile("[A-Z][0-9]*\\([0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?\\)");
        this.vectorPattern = Pattern.compile("[a-z][0-9]*=\\([0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?\\)");
        this.parametricFormPlane = "[A-Z][0-9]*:" + vector + "\\+" + vector + "\\*[a-z]\\+" + vector + "\\*[a-z]";
        this.parametricFormPlanePattern = Pattern.compile(parametricFormPlane);
        this.normalFormPlane = "[A-Z][0-9]*:\\[[a-z]-" + vector + "\\]([*°])" + vector + "=0";
        this.normalFormPlanePattern = Pattern.compile(normalFormPlane);
        this.cartesianFormPlane = "[A-Z][0-9]*:" + floatNumber + "(x1)|x" + floatNumber + "(x2)|y" + floatNumber + "(x3)|z=" + floatNumber;
        this.cartesianFormPlanePattern = Pattern.compile(cartesianFormPlane);
    }

    //Methods matcher Methods
    public boolean linearPatternMatches(String input) {
        return linearPattern.matcher(input).matches();
    }

    public boolean quadraticPatternMatches(String input) {
        return quadraticPattern.matcher(input).matches();
    }

    public boolean ExponentialPatternMatches(String input) {
        return exponentialPattern.matcher(input).matches();
    }

    public boolean binomialFormulaPatternWithKMatches(String input) {
        return binomialFormulaPatternWithK.matcher(input).matches();
    }

    public boolean binomialFormulaPatternWithGMatches(String input) {
        return binomialFormulaPatternWithG.matcher(input).matches();
    }

    public boolean vectorPointPatternMatches(String input) {
        return vectorPointPattern.matcher(input).matches();
    }

    public boolean vectorPatternMatches(String input) {
        return vectorPattern.matcher(input).matches();
    }

    public boolean parametricFormPlaneMatches(String input) {
        return parametricFormPlanePattern.matcher(input).matches();
    }

    public boolean normalFormPlaneMatches(String input) {
        return normalFormPlanePattern.matcher(input).matches();
    }

    public boolean cartesianFormPlaneMatches(String input) {
        return cartesianFormPlanePattern.matcher(input).matches();
    }
}
