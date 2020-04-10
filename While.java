
public class While implements Expression
{
    
    protected Expression e;
    protected Instructions inst;

    public While(Expression e)
    {
        this.e = e;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
