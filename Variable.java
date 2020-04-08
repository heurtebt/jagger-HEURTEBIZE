public class Variable implements Expression
{
    protected String id;
    protected VariableDecl decl; 

    public Variable(String id, VariableDecl v){
        this.id = id;
        this.decl = v;
    }

    public Variable(String id){
        this.id = id;
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
