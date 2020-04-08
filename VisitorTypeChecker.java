public class VisitorTypeChecker extends Visitor
{
    private boolean b;
    private Type t;

    public VisitorTypeChecker(){
        this.b=true;
    }

    public boolean hasError(){
        return !this.b;
    }

    public void visit(Negative n){
        n.e.accept(this);
        if (!t.equals(Type.FLOAT)){
            this.b=false;
        }
    }

    public void visit(Positive p){
        p.e.accept(this);
        if (!t.equals(Type.FLOAT)){
            this.b=false;
        }
    }

    public void visit(Add a){
        a.e1.accept(this);        
        Type ttmp1 = t;
        a.e2.accept(this);        
        Type ttmp2 = t;
        if(!(ttmp1.equals(Type.FLOAT) && ttmp2.equals(Type.FLOAT))){
            a.t=Type.STRING;
            t=Type.STRING;
        }
    }

    public void visit(Substract s){
        s.e1.accept(this);           
        Type ttmp1 = t;
        s.e2.accept(this);
        Type ttmp2 = t;
        if(!(ttmp1.equals(Type.FLOAT) && ttmp2.equals(Type.FLOAT))){
            this.b=false;
        }
        t=Type.FLOAT;
    }    

    public void visit(Multiply m){
        m.e1.accept(this);           
        Type ttmp1 = t;
        m.e2.accept(this);
        Type ttmp2 = t;
        if(!(ttmp1.equals(Type.FLOAT) && ttmp2.equals(Type.FLOAT))){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(Divide d){
        d.e1.accept(this);           
        Type ttmp1 = t;
        d.e2.accept(this);
        Type ttmp2 = t;
        if(!(ttmp1.equals(Type.FLOAT) && ttmp2.equals(Type.FLOAT))){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(Equal eq){
        eq.e1.accept(this);           
        Type ttmp1 = t;
        eq.e2.accept(this);
        Type ttmp2 = t;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(NotEqual neq){
        neq.e1.accept(this);           
        Type ttmp1 = t;
        neq.e2.accept(this);
        Type ttmp2 = t;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(More m){
        m.e1.accept(this);           
        Type ttmp1 = t;
        m.e2.accept(this);
        Type ttmp2 = t;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(MoreOrEqual meq){
        meq.e1.accept(this);           
        Type ttmp1 = t;
        meq.e2.accept(this);
        Type ttmp2 = t;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(Less l){
        l.e1.accept(this);           
        Type ttmp1 = t;
        l.e2.accept(this);
        Type ttmp2 = t;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(LessOrEqual leq){
        leq.e1.accept(this);           
        Type ttmp1 = t;
        leq.e2.accept(this);
        Type ttmp2 = t;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
        t=Type.FLOAT;
    }

    public void visit(Constant c){
        t= c.t;
    }

    public void visit(IfThenElse ite){
        ite.e1.accept(this);
        if(!t.equals(Type.FLOAT)){
            this.b=false;
        }
        ite.e2.accept(this);           
        Type ttmp1 = t;
        ite.e3.accept(this);
        Type ttmp2 = t;        
        ite.t = ttmp1;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
        }
    }

    public void visit(Print p){
        p.e.accept(this);
    }

    public void visit(ConstantString cs){
        t= cs.t;
    }

    public void visit(Variable v){
        v.decl.accept(this);
        switch(t){
            case FLOAT:
            System.out.println("FLOAT var "+v.id);
            break;
            case STRING:
            System.out.println("STRING var "+v.id);
            break;
            default:
            this.b=false;
        }
    }

    public void visit(VariableDecl vd){
        t= vd.t;
    }

    public void visit(Scope s){
        for (VariableDecl vd : s.vars.values()){
            vd.value.accept(this);
            vd.t=this.t;
        }
        for (Expression e : s.inst){
            e.accept(this);
        }
    }

    public void visit(Assignment a){
        a.v.accept(this);
        Type tt=this.t;
        a.e.accept(this);
        if (this.t!=tt)
            this.b=false;
    }
    
    public void visit(While w){
        w.e.accept(this);
        for (Expression e : w.inst){
            e.accept(this);
        }
    }

} 
