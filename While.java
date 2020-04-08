import java.util.ArrayList;
public class While implements Expression
{
    
    protected Expression e;
    protected ArrayList<Expression> inst;

    public While(Expression e)
    {
        this.inst = new ArrayList<Expression>();
        this.e = e;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
