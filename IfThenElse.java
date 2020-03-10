
public class IfThenElse implements Expression
{
    protected Expression e1, e2, e3;
    public IfThenElse(Expression e1, Expression e2, Expression e3)
    {
        this.e1=e1;
        this.e2=e2;
        this.e3=e3;
    }
    public void accept(Visitor v){
        v.visit(this);
    }
}
