
public class VisitorRenamer extends Visitor
{
    
    private int n;

    public VisitorRenamer()
    {
        this.n = 0;
    }

    public void visit(Negative n){
        n.e.accept(this);
    }
    
    public void visit(Positive p){
        p.e.accept(this);
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
    
    public void visit(Variable v){}
    
    public void visit(VariableDecl vd){
        System.out.print("Renamed " + vd.id);
        vd.id = vd.id+"_"+n;
        System.out.println(" as "+vd.id);
        n++;
    }
    
    public void visit(Scope s){
        for (VariableDecl vd : s.vars.values()){
            vd.accept(this);
        }
        for (Expression e : s.inst){
            e.accept(this);
        }
    }
    
    public void visit(Assignment a){
        a.v.accept(this);a.e.accept(this);        
    }
    
}
