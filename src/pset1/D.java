package pset1;

public class D extends C {

    int g;

    public D(int f, int g) {
        super(f);
        this.g = g;
    }

    @Override
    public boolean equals(Object o) {
        // assume this method is implemented for you
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        D tmpD = (D) o;
        return (tmpD.g == this.g && super.equals(tmpD));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        Integer intG = new Integer(g);
        Integer intF = new Integer(f);
        hash = hash + (intG != 0 ? intG.hashCode() : 0);
        hash = hash + (intF != 0 ? intF.hashCode() : 0);
        hash = hash + (super.hashCode() != 0 ? super.hashCode() : 0);
        return (hash == 0 ? super.hashCode() : hash);
    }
}