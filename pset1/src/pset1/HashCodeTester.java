package pset1;
import static org.junit.Assert.*;
import org.junit.Test;

public class HashCodeTester {
    /*
     * P5: If two objects are equal according to the equals(Object)
     * method, then calling the hashCode method on each of
     * the two objects must produce the same integer result.
     */
    //Object vs Object
    @Test public void hashTest0(){
        Object x = new Object();
        Object y = new Object();
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //Object vs C Class
    @Test public void hashTest1(){
        Object x = new Object();
        C y = new C(3);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //Object vs D Class
    @Test public void hashTest2(){
        Object x = new Object();
        D y = new D(3,4);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //D Class vs C Class
    @Test public void hashTest3(){
        D x = new D(3,4);
        C y = new C(3);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //C Class vs C Class
    @Test public void hashTest4(){
        C x = new C(4);
        C y = new C(3);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //C Class vs C Class (same int)
    @Test public void hashTest5(){
        C x = new C(4);
        C y = new C(4);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //D Class vs D Class
    @Test public void hashTest6(){
        D x = new D(4,3);
        D y = new D(3,5);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //D Class vs D Class (same ints)
    @Test public void hashTest7(){
        D x = new D(4,3);
        D y = new D(4,3);
        assertTrue((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //Object = Object
    @Test public void hashTest8(){
        Object x = new Object();
        Object y = x;
        assertFalse((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //C Class = C Class
    @Test public void hashTest9(){
        C x = new C(3);
        C y = x;
        assertFalse((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }

    //D Class = D Class
    @Test public void hashTest10(){
        D x = new D(3,4);
        D y = x;
        assertFalse((!x.equals(y) && !y.equals(x)) && x.hashCode() != y.hashCode());
    }
}
