
public class Assignment implements Expression
{
    protected Variable v;
    protected Expression e;
    /**
     * Constructor for objects of class Assignment
     */
    public Assignment(Variable v, Expression e)
    {
        this.v=v;
        this.e=e;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
