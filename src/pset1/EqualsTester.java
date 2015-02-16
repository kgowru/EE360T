package pset1;
import static org.junit.Assert.*;
import org.junit.Test;
public class EqualsTester {
    /*
     * P1: For any non-null reference value x, x.equals(null) should return false.
     */
    //Object Class
    @Test public void t0() {
        assertFalse(new Object().equals(null));
    }

    //C Class
    @Test public void t1() {
        assertFalse(new C(4).equals(null));
    }

    //D Class
    @Test public void t2() {
        assertFalse(new D(3,4).equals(null));
    }

    /*
     * P2: It is reflexive: for any non-null reference value x, x.equals(x)
     * should return true.
     */
    //Object Class
    @Test public void reflexTest0 (){
        Object x = new Object();
        assertTrue(x.equals(x));
    }

    //C Class
    @Test public void reflexTest1 (){
        C x = new C(5);
        assertTrue(x.equals(x));
    }

    //D Class
    @Test public void reflexTest2 (){
        D x = new D(5,4);
        assertTrue(x.equals(x));
    }

    /*
     * P3: It is symmetric: for any non-null reference values x and y, x.equals(y)
     * should return true if and only if y.equals(x) returns true.
     */
    //Object vs Object
    @Test public void symTest0(){
        Object x = new Object();
        Object y = new Object();
        assertTrue(x.equals(y) == y.equals(x));

    }

    //Object vs C Class
    @Test public void symTest1(){
        Object x = new Object();
        C y = new C(3);
        assertTrue(x.equals(y) == y.equals(x));
    }

    //Object vs D Class
    @Test public void symTest2(){
        Object x = new Object();
        D y = new D(3,4);
        assertTrue(x.equals(y) == y.equals(x));
    }

    //D Class vs C Class
    @Test public void symTest3(){
        D x = new D(3,4);
        C y = new C(3);
        assertTrue(x.equals(y) == y.equals(x));
    }

    //C Class vs C Class
    @Test public void symTest4(){
        C x = new C(4);
        C y = new C(3);
        assertTrue(x.equals(y) == y.equals(x));
    }

    //D Class vs D Class
    @Test public void symTest5(){
        D x = new D(4,3);
        D y = new D(3,4);
        assertTrue(x.equals(y) == y.equals(x));
    }

    //Object = Object
    @Test public void symTest6(){
        Object x = new Object();
        Object y = x;
        assertTrue(x.equals(y) == y.equals(x));
    }

    //C Class = C Class
    @Test public void symTest7(){
        C x = new C(3);
        C y = x;
        assertTrue(x.equals(y) == y.equals(x));
    }

    //D Class = D Class
    @Test public void symTest8(){
        D x = new D(3,4);
        D y = x;
        assertTrue(x.equals(y) == y.equals(x));
    }

    /*
     * P4: It is transitive: for any non-null reference values x, y, and z,
     * if x.equals(y) returns true and y.equals(z) returns true, then
     * x.equals(z) should return true.
     */
    // you do not need to write tests for P4
}