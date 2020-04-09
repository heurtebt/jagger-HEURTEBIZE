import java.util.Stack;
import java.util.LinkedHashMap;
public class VisitorBinder extends Visitor
{
    
    private Stack<LinkedHashMap<String,VariableDecl>> env;
    
    public VisitorBinder()
    {
        this.env = new Stack<LinkedHashMap<String,VariableDecl>>();
    }
    
    public void visit(Negative n){
        n.e.accept(this);
    }

    public void visit(Add a){
        a.e1.accept(this); a.e2.accept(this);
    }
    
    public void visit(Substract s){
        s.e1.accept(this); s.e2.accept(this);
    }
    
    public void visit(Multiply m){
        m.e1.accept(this); m.e2.accept(this);
    }
    
    public void visit(Divide d){
        d.e1.accept(this); d.e2.accept(this);
    }
    
    public void visit(Equal eq){
        eq.e1.accept(this); eq.e2.accept(this);
    }
    
    public void visit(NotEqual neq){
        neq.e1.accept(this); neq.e2.accept(this);
    }
    
    public void visit(More m){
        m.e1.accept(this); m.e2.accept(this);
    }
    
    public void visit(MoreOrEqual meq){
        meq.e1.accept(this); meq.e2.accept(this);
    }
    
    public void visit(Less l){
        l.e1.accept(this); l.e2.accept(this);
    }
    
    public void visit(LessOrEqual leq){
        leq.e1.accept(this); leq.e2.accept(this);
    }
    
    public void visit(Constant c){}
    
    public void visit(IfThenElse ite){
        ite.e1.accept(this); ite.e2.accept(this); ite.e3.accept(this);
    }
    
    public void visit(Print p){
        p.e.accept(this);
    }
    
    public void visit(ConstantString cs){}
    
    public void visit(Variable v){
        boolean match = false;
        for(int i=this.env.size()-1; i >=0; i--){            
            LinkedHashMap<String,VariableDecl> e = this.env.get(i);
            for (String s : e.keySet()){
                System.out.println("Found "+s+".");
            }
            if(e.containsKey(v.id)){
                match = true;
                v.decl=e.get(v.id);
                break;
            }
        }
        if(!match)
            System.out.println(v.id+" does not exist.");
    }
    
    public void visit(VariableDecl vd){}
    
    public void visit(Scope s){
        this.env.push(new LinkedHashMap<String,VariableDecl>());
        for (VariableDecl vd : s.vars.values()){
            vd.value.accept(this);
            this.env.peek().put(vd.id,vd);
        }
        for (Expression e : s.inst){
            e.accept(this);
        }
        this.env.pop();
    }
    
    public void visit(Assignment a){
        a.v.accept(this); a.e.accept(this);
    }
    
    public void visit(While w){
        w.e.accept(this);
        for (Expression e : w.inst){
            e.accept(this);
        }
    }
}
