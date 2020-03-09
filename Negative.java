
public class Negative extends Unary
{
    public Negative(Expression e)
    {
        super(e);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
