
public class Positive extends Unary
{
    public Positive(Expression e)
    {
        super(e);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
