public class ConstantString implements Expression
{    
    protected String i;
    public ConstantString(String i)
    {
        this.i=i;
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
