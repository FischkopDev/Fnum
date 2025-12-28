import de.home_skrobanek.fnum.vector.Vector;
import de.home_skrobanek.fnum.vector.Vector2D;
import de.home_skrobanek.fnum.vector.Vector3D;

public class VectorExample {

    public static void main(String[] args){
        //General class for vectors
        Vector x = new Vector(2, 4, 1, 3);
        Vector y = new Vector(1, 2, 5, 2);

        //sub-class for Vector 2 and 3D
        Vector2D a2D;
        Vector3D a3D;

        //Standard operations
        x.addVector(y);
        System.out.println(x.toString());

        //multiplication
        System.out.println(x.multiplyVector(y));
    }
}
