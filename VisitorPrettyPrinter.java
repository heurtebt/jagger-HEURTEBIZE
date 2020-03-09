public class VisitorPrettyPrinter extends Visitor
{
    private String pp;
    public String prettyPrint(){
        return pp;
    }

    public void visit(Negative n){
        n.e.accept(this);
        String tmp = pp;
        pp = "(-"+tmp+")";
    }

    public void visit(Positive p){
        p.e.accept(this);
        String tmp = pp;
        pp = tmp;
    }

    public void visit(Add a){
        a.e1.accept(this);
        String tmp1 = pp;
        a.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"+"+tmp2+")";
    }

    public void visit(Substract s){
        s.e1.accept(this);
        String tmp1 = pp;
        s.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"-"+tmp2+")";
    }

    public void visit(Multiply m){
        m.e1.accept(this);
        String tmp1 = pp;
        m.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"*"+tmp2+")"; 
    }

    public void visit(Divide d){
        d.e1.accept(this);
        String tmp1 = pp;
        d.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"/"+tmp2+")"; 
    }

    public void visit(Equal eq){
        eq.e1.accept(this);
        String tmp1 = pp;
        eq.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"="+tmp2+")"; 
    }

    public void visit(NotEqual neq){
        neq.e1.accept(this);
        String tmp1 = pp;
        neq.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"<>"+tmp2+")"; 
    }

    public void visit(More m){
        m.e1.accept(this);
        String tmp1 = pp;
        m.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+">"+tmp2+")"; 
    }

    public void visit(MoreOrEqual meq){
        meq.e1.accept(this);
        String tmp1 = pp;
        meq.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+">="+tmp2+")"; 
    }

    public void visit(Less l){
        l.e1.accept(this);
        String tmp1 = pp;
        l.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"<"+tmp2+")"; 
    }

    public void visit(LessOrEqual leq){
        leq.e1.accept(this);
        String tmp1 = pp;
        leq.e2.accept(this);
        String tmp2 = pp;
        pp = "("+tmp1+"<="+tmp2+")"; 
    }

    public void visit(Constant c){
        pp = ""+c.i;
        
    }
}
