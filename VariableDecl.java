public class VariableDecl implements Expression
{
    protected String id;
    protected Type t;
    protected Expression value;
    public VariableDecl(String id, Expression value)
    {
        this.id=id;
        this.value=value;
    }
    public void accept(Visitor v){
        v.visit(this);
    }
}
