public class MoreOrEqual extends Comparison
{
    public MoreOrEqual(Expression e1, Expression e2)
    {
        super(e1,e2);
    }
    
    public void accept(Visitor v) {
        v.visit(this);
    }
}
