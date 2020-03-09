public abstract class Unary implements Expression
{
    protected Expression e;
    public Unary(Expression e){
        this.e=e;
    }

    public abstract void accept(Visitor v);
}
