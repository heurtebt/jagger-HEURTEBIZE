public abstract class Unary implements Expression
{
    protected Expression e;
    protected Type t = Type.FLOAT;
    public Unary(Expression e){
        this.e=e;
    }

    public abstract void accept(Visitor v);
}
