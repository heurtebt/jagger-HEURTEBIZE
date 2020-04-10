import java.util.LinkedHashMap;
import java.util.ArrayList;
public class Scope implements Expression
{    
    protected Instructions inst;
    protected LinkedHashMap<String, VariableDecl> vars;
    protected boolean declError;

    public Scope()
    {
        this.vars = new LinkedHashMap<String,VariableDecl>();
        this.declError = false;
    }

    public void addDeclaration(String id, VariableDecl v){
        if(this.vars.containsKey(id)){
            System.out.println("Error : var "+id+" already exist in this scope.");
            this.declError = true;}
        else
            this.vars.put(id, v);
    }

    public void accept(Visitor v){
        v.visit(this);
    }
}
