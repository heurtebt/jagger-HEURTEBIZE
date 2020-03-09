
public abstract class Comparison implements Expression
{
    protected Expression e1;
    protected Expression e2;
    public Comparison(Expression e1, Expression e2)
    {
        this.e1=e1;
        this.e2=e2;
    }
}
