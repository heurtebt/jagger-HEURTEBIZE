public abstract class Binary implements Expression
{
    protected Expression e1;
    protected Expression e2;    
    public Binary(Expression e1, Expression e2)
    {
        this.e1=e1;
        this.e2=e2;
    }

    public abstract void accept(Visitor v);
}
