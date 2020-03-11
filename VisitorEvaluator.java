public class VisitorEvaluator extends Visitor
{
    private float evf;
    private String evs;
    Type t;
    public String evaluator(){
        return evf+evs;
    }

    public void visit(Negative n){
        switch(t){
            case FLOAT:{
                n.e.accept(this);
                float tmp = evf;
                evf = -tmp;
                break;
            }
            case
        }
    }

    public void visit(Positive p){
        p.e.accept(this);
        float tmp = evf;
        evf = tmp;
    }

    public void visit(Add a){
        a.e1.accept(this);
        float tmp1 = evf;
        a.e2.accept(this);
        float tmp2 = evf;
        evf = tmp1+tmp2;
    }

    public void visit(Substract s){
        s.e1.accept(this);
        float tmp1 = evf;
        s.e2.accept(this);
        float tmp2 = evf;
        evf = tmp1-tmp2;
    }

    public void visit(Multiply m){
        m.e1.accept(this);
        float tmp1 = evf;
        m.e2.accept(this);
        float tmp2 = evf;
        evf = tmp1*tmp2; 
    }

    public void visit(Divide d){
        d.e1.accept(this);
        float tmp1 = evf;
        d.e2.accept(this);
        float tmp2 = evf;
        evf = tmp1/tmp2; 
    }

    public void visit(Equal eq){
        eq.e1.accept(this);
        float tmp1 = evf;
        eq.e2.accept(this);
        float tmp2 = evf;        
        evf = (tmp1==tmp2)? 1 : 0; 
    }

    public void visit(NotEqual neq){
        neq.e1.accept(this);
        float tmp1 = evf;
        neq.e2.accept(this);
        float tmp2 = evf;
        evf = (tmp1==tmp2)? 0 : 1; 
    }

    public void visit(More m){
        m.e1.accept(this);
        float tmp1 = evf;
        m.e2.accept(this);
        float tmp2 = evf;
        evf = (tmp1>tmp2)? 1 : 0;  
    }

    public void visit(MoreOrEqual meq){
        meq.e1.accept(this);
        float tmp1 = evf;
        meq.e2.accept(this);
        float tmp2 = evf;
        evf = (tmp1>=tmp2)? 1 : 0;  
    }

    public void visit(Less l){
        l.e1.accept(this);
        float tmp1 = evf;
        l.e2.accept(this);
        float tmp2 = evf;        
        evf = (tmp1<tmp2)? 1 : 0; 
    }

    public void visit(LessOrEqual leq){
        leq.e1.accept(this);
        float tmp1 = evf;
        leq.e2.accept(this);
        float tmp2 = evf;
        evf = (tmp1<=tmp2)? 1 : 0; 
    }

    public void visit(Constant c){
        evf = c.i;
        t=Type.FLOAT;
    }

    public void visit(ConstantString cs){
        evs = cs.i;
        t=Type.STRING;
    }

    public void visit(IfThenElse ite){
        ite.e1.accept(this);
        Float tmp= evf;
        switch (tmp.intValue()){
            case 1:{
                ite.e2.accept(this);
                break;
            }
            case 0:{
                ite.e3.accept(this);
                break;
            }
            default:
            evf = -1;
        }
    }

    public void visit(Print p){
        p.e.accept(this);
    }

}
