public class Print implements Expression
{
    protected Expression e;
    public Print(Expression e)
    {
        this.e=e;
    }
    public void accept(Visitor v){
        v.visit(this);
    }
}
