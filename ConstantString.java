public class ConstantString implements Expression
{    
    protected String i;
    protected Type t = Type.STRING;
    public ConstantString(String i)
    {
        this.i=i.replace("\"","");
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
