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
            System.out.println("Error : You can only substract FLOATs.");
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
            System.out.println("Error : You can only multiply FLOATs.");
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
            System.out.println("Error : You can only divide FLOATs.");
        }
        t=Type.FLOAT;
    }

    public void visit(Equal eq){
        eq.e1.accept(this);           
        Type ttmp1 = t;
        eq.e2.accept(this);
        Type ttmp2 = t;
        if(ttmp1.equals(Type.VOID)||ttmp2.equals(Type.VOID)){
            this.b=false;
            System.out.println("Error : You cannot compare VOID type.");
        }
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : You cannot compare different type expressions.");
        }
        t=Type.FLOAT;
    }

    public void visit(NotEqual neq){
        neq.e1.accept(this);           
        Type ttmp1 = t;
        neq.e2.accept(this);
        Type ttmp2 = t;
        if(ttmp1.equals(Type.VOID)||ttmp2.equals(Type.VOID)){
            this.b=false;
            System.out.println("Error : You cannot compare VOID type.");
        }
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : You cannot compare different type expressions.");
        }
        t=Type.FLOAT;
    }

    public void visit(More m){
        m.e1.accept(this);           
        Type ttmp1 = t;
        m.e2.accept(this);
        Type ttmp2 = t;
        if(ttmp1.equals(Type.VOID)||ttmp2.equals(Type.VOID)){
            this.b=false;
            System.out.println("Error : You cannot compare VOID type.");
        }
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : You cannot compare different type expressions.");
        }
        t=Type.FLOAT;
    }

    public void visit(MoreOrEqual meq){
        meq.e1.accept(this);           
        Type ttmp1 = t;
        meq.e2.accept(this);
        Type ttmp2 = t;
        if(ttmp1.equals(Type.VOID)||ttmp2.equals(Type.VOID)){
            this.b=false;
            System.out.println("Error : You cannot compare VOID type.");
        }
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : You cannot compare different type expressions.");
        }
        t=Type.FLOAT;
    }

    public void visit(Less l){
        l.e1.accept(this);           
        Type ttmp1 = t;
        l.e2.accept(this);
        Type ttmp2 = t;
        if(ttmp1.equals(Type.VOID)||ttmp2.equals(Type.VOID)){
            this.b=false;
            System.out.println("Error : You cannot compare VOID type.");
        }
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : You cannot compare different type expressions.");
        }
        t=Type.FLOAT;
    }

    public void visit(LessOrEqual leq){
        leq.e1.accept(this);           
        Type ttmp1 = t;
        leq.e2.accept(this);
        Type ttmp2 = t;
        if(ttmp1.equals(Type.VOID)||ttmp2.equals(Type.VOID)){
            this.b=false;
            System.out.println("Error : You cannot compare VOID type.");
        }
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : You cannot compare different type expressions.");
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
            System.out.println("Error : Condition argument must be of type FLOAT.");
        }
        ite.e2.accept(this);           
        Type ttmp1 = t;
        ite.e3.accept(this);
        Type ttmp2 = t;        
        ite.t = ttmp1;
        if(!ttmp1.equals(ttmp2)){
            this.b=false;
            System.out.println("Error : The type of the statements cannot be different.");
        }
    }

    public void visit(Print p){
        p.e.accept(this);
        this.t=Type.VOID;
    }

    public void visit(ConstantString cs){
        t= cs.t;
    }

    public void visit(Variable v){
        try{
            v.decl.accept(this);
            switch(t){
                case FLOAT:
                System.out.println("FLOAT var "+v.id+".");
                break;
                case STRING:
                System.out.println("STRING var "+v.id+".");
                break;
                case VOID:
                this.b=false;
                System.out.println("Error : found var "+v.id+" of type VOID.");
                break;
                default:
                this.b=false;
                System.out.println("Error : Not able to find the type of var "+v.id+".");                
            }
        }catch(java.lang.NullPointerException e){
            this.t=null;
            this.b=false;
            System.out.println("Error : Not able to find the type of var "+v.id+".");
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
        s.inst.accept(this);
        this.t=Type.VOID;
    }

    public void visit(Assignment a){
        a.v.accept(this);
        Type tt=this.t;
        a.e.accept(this);
        if (this.t!=tt){
            this.b=false;System.out.println("Error : Trying to change var "+a.v.id+" type.");}
    }

    public void visit(While w){
        w.e.accept(this);
        w.inst.accept(this);
        this.t=Type.VOID;
    }

    public void visit(Instructions i){
        for (Expression e : i.inst){
            e.accept(this);
        }
    }

} 
