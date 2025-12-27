package de.home_skrobanek.fnum.vector;

public class Vector3D extends Vector{

    public Vector3D(double x, double y, double z){
        super(x, y, z);
    }

    public double getX(){
        return getValue(0);
    }

    public double getY(){
        return getValue(1);
    }

    public double getZ(){
        return getValue(2);
    }
}
