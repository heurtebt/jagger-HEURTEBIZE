public class VisitorPrettyPrinter extends Visitor
{
    public void visit(Negative n){
        System.out.print("(-");
        n.e.accept(this);
        System.out.print(")");
    }

    public void visit(Positive p){
        p.e.accept(this);
    }

    public void visit(Add a){
        System.out.print("(");
        a.e1.accept(this);
        System.out.print("+");
        a.e2.accept(this);
        System.out.print(")");
    }

    public void visit(Substract s){
        System.out.print("(");
        s.e1.accept(this);
        System.out.print("-");
        s.e2.accept(this);
        System.out.print(")");
    }

    public void visit(Multiply m){
        System.out.print("(");
        m.e1.accept(this);
        System.out.print("*");
        m.e2.accept(this);
        System.out.print(")");
    }

    public void visit(Divide d){
        System.out.print("(");
        d.e1.accept(this);
        System.out.print("/");
        d.e2.accept(this);
        System.out.print(")");
    }

    public void visit(Equal eq){
        System.out.print("(");
        eq.e1.accept(this);
        System.out.print("=");
        eq.e2.accept(this);
        System.out.print(")");
    }

    public void visit(NotEqual neq){
        System.out.print("(");
        neq.e1.accept(this);
        System.out.print("<>");
        neq.e2.accept(this);
        System.out.print(")");
    }

    public void visit(More m){
        System.out.print("(");
        m.e1.accept(this);
        System.out.print(">");
        m.e2.accept(this);
        System.out.print(")");
    }

    public void visit(MoreOrEqual meq){
        System.out.print("(");
        meq.e1.accept(this);
        System.out.print(">=");
        meq.e2.accept(this);
        System.out.print(")");
    }

    public void visit(Less l){
        System.out.print("(");
        l.e1.accept(this);
        System.out.print("<");
        l.e2.accept(this);
        System.out.print(")");
    }

    public void visit(LessOrEqual leq){
        System.out.print("(");
        leq.e1.accept(this);
        System.out.print("<=");
        leq.e2.accept(this);
        System.out.print(")");
    }

    public void visit(Constant c){
        System.out.print(c.i);    
    }

    public void visit(ConstantString cs){
        System.out.print("\""+cs.i+"\"");      
    }

    public void visit(IfThenElse ite){
        System.out.print("( if ( ");
        ite.e1.accept(this);
        System.out.print(" ) then { ");
        ite.e2.accept(this);
        System.out.print(" } else { ");
        ite.e3.accept(this);
        System.out.print(" } )");
    }

    public void visit(Print p){
        System.out.print("print(");        
        p.e.accept(this);
        System.out.print(")");        
    }

    public void visit(Variable v){
        System.out.print("(var "+v.id+")");
    }

    public void visit(VariableDecl vd){
        System.out.print("new var "+vd.id+" := ");
        vd.value.accept(this);
        System.out.println();
    }
    
    public void visit(Scope s){
        System.out.println("let");
        for(VariableDecl vd : s.vars.values()){
            vd.accept(this);
        }
        System.out.println("in");
        for (Expression e : s.inst){
            e.accept(this);System.out.println();
        }
        System.out.println("end");
    }

}
