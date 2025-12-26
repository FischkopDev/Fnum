package de.home_skrobanek.fnum.ieee;

public class FloatingPoint {

    //Base
    private int beta;
    //Length of m
    private int r;
    // length of exponent
    private int s;

    private short sign, signExp;

    private double[] m;
    private double[] exp;

    public FloatingPoint(short sign, double[] m, int beta, short signExp, double[] exp){
        assert beta >= 2 && m.length > 0 && exp.length > 0;
        //Assert for signs
        assert sign == 1 || sign == -1;
        assert signExp == 1 || signExp == -1;

        //Assert for rules
        assert m[0] != 0;

        this.m = m;
        this.exp = exp;
        this.sign = sign;
        this.beta = beta;
        this.signExp = signExp;

        //getting limits
        r = m.length;
        s = exp.length-1;
    }

    public double getCompleteM(){
        double m_delta = 0;

        for(int i = 1; i < r; i++){
            m_delta += m[i]*Math.pow(beta, -1*i);
        }

        return m_delta*sign;
    }

    public double getCompleteExp(){
        double exp_delta = 0;

        for(int i = 0; i < i-1; i++){
            exp_delta += exp[i]* Math.pow(beta, i);
        }
        return signExp*exp_delta;
    }

    public double getFloatValue(){
        return getCompleteM() * Math.pow(beta, getCompleteExp());
    }

}
