import java.util.LinkedHashMap;
import java.util.ArrayList;
public class Scope implements Expression
{    
    protected ArrayList<Expression> inst;
    protected LinkedHashMap<String, VariableDecl> vars;
    
    public Scope()
    {
        this.vars = new LinkedHashMap<String,VariableDecl>();
        this.inst = new ArrayList<Expression>();      
    }
    
    public void addDeclaration(String id, VariableDecl v){
		if(this.vars.containsKey(id))
			System.out.println("Error, "+id+" already exist in this scope.");
		else
			this.vars.put(id, v);
	}

    public void accept(Visitor v){
        v.visit(this);
    }
}
