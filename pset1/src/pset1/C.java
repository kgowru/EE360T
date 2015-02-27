package pset1;

public class C {

    int f;

    public C(int f)
    {
        this.f = f;
    }

    @Override
    public boolean equals(Object o) {
        // assume this method is implemented for you
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;

        C tmpC = (C) o;
        return (tmpC.f == this.f && super.equals(tmpC));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        Integer intF = new Integer(f);
        hash = hash + (intF != 0 ? intF.hashCode() : 0);
        hash = hash + (super.hashCode() != 0 ? super.hashCode() : 0);
        return (hash == 0 ? super.hashCode() : hash);
    }

}