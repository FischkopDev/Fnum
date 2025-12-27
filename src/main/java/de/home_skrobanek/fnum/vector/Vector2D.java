package de.home_skrobanek.fnum.vector;

public class Vector2D extends Vector{

    public Vector2D(double x, double y){
        super(x, y);
    }

    public double getX(){
        return getValue(0);
    }

    public double getY(){
        return getValue(1);
    }
}
