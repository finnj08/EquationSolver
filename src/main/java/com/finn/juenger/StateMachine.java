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

    //Constructor
    public StateMachine() {
        this.linearPattern = Pattern.compile("(\\+|-)?[0-9]+((\\.|,)[0-9]+)?x((\\+|-)[0-9]+((\\.|,)[0-9]+)?)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        this.quadraticPattern = Pattern.compile("(\\+|-)?[0-9]+((\\.|,)[0-9]+)?x\\^2(\\+|-)[0-9]+((\\.|,)[0-9]+)?x((\\+|-)[0-9]+((\\.|,)[0-9]+)?)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        this.exponentialPattern = Pattern.compile("((\\+|-)?[0-9]+((\\.|,)[0-9]+)?\\*)?(([0-9]+((\\.|,)[0-9]+)?)|e)\\^x((\\+|-)[0-9]+((\\.|,)[0-9]+)?)?=(\\+|-)?[0-9]+((\\.|,)[0-9]+)?");
        this.binomialFormulaPatternWithK = Pattern.compile("P\\(X(<|>)=?k\\)(<|<)=?0(\\.|,)[0-9]+");
        this.binomialFormulaPatternWithG = Pattern.compile("P\\(X(<|>)=?g\\)(<|<)=?0(\\.|,)[0-9]+");
        this.vectorPointPattern = Pattern.compile("[A-Z][0-9]*\\([0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?\\)");
        this.vectorPattern = Pattern.compile("[v-z][0-9]*\\([0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?/[0-9]+((\\.|,)[0-9]+)?\\)");
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
}
