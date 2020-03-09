public class VisitorEvaluator extends Visitor
{
    private float ev;
    public float evaluator(){
        return ev;
    }

    public void visit(Negative n){
        n.e.accept(this);
        float tmp = ev;
        ev = -tmp;
    }

    public void visit(Positive p){
        p.e.accept(this);
        float tmp = ev;
        ev = tmp;
    }

    public void visit(Add a){
        a.e1.accept(this);
        float tmp1 = ev;
        a.e2.accept(this);
        float tmp2 = ev;
        ev = tmp1+tmp2;
    }

    public void visit(Substract s){
        s.e1.accept(this);
        float tmp1 = ev;
        s.e2.accept(this);
        float tmp2 = ev;
        ev = tmp1-tmp2;
    }

    public void visit(Multiply m){
        m.e1.accept(this);
        float tmp1 = ev;
        m.e2.accept(this);
        float tmp2 = ev;
        ev = tmp1*tmp2; 
    }

    public void visit(Divide d){
        d.e1.accept(this);
        float tmp1 = ev;
        d.e2.accept(this);
        float tmp2 = ev;
        ev = tmp1/tmp2; 
    }

    public void visit(Equal eq){
        eq.e1.accept(this);
        float tmp1 = ev;
        eq.e2.accept(this);
        float tmp2 = ev;        
        ev = (tmp1==tmp2)? 1 : 0; 
    }

    public void visit(NotEqual neq){
        neq.e1.accept(this);
        float tmp1 = ev;
        neq.e2.accept(this);
        float tmp2 = ev;
        ev = (tmp1==tmp2)? 0 : 1; 
    }

    public void visit(More m){
        m.e1.accept(this);
        float tmp1 = ev;
        m.e2.accept(this);
        float tmp2 = ev;
        ev = (tmp1>tmp2)? 1 : 0;  
    }

    public void visit(MoreOrEqual meq){
        meq.e1.accept(this);
        float tmp1 = ev;
        meq.e2.accept(this);
        float tmp2 = ev;
        ev = (tmp1>=tmp2)? 1 : 0;  
    }

    public void visit(Less l){
        l.e1.accept(this);
        float tmp1 = ev;
        l.e2.accept(this);
        float tmp2 = ev;        
        ev = (tmp1<tmp2)? 1 : 0; 
    }

    public void visit(LessOrEqual leq){
        leq.e1.accept(this);
        float tmp1 = ev;
        leq.e2.accept(this);
        float tmp2 = ev;
        ev = (tmp1<=tmp2)? 1 : 0; 
    }

    public void visit(Constant c){
        ev = c.i;

    }
}
