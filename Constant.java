public class Constant implements Expression
{    
    protected float i;
    protected Type t=Type.FLOAT;
    public Constant(float i)
    {
        this.i=i;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
