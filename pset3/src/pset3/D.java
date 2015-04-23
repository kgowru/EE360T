package pset3;

/**
 * Created by kgowru on 3/29/15.
 */
public class D {
    public static void main(String[] a) {
        foo(a);
        bar(a); }
    static void foo(String[] a) {
        if (a == null) return;
        bar(a);
    }
    static void bar(String[] a) {}
}