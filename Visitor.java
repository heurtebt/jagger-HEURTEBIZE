public abstract class Visitor
{

    public abstract void visit(Negative n);
    public abstract void visit(Positive p);
    public abstract void visit(Add a);
    public abstract void visit(Substract s);
    public abstract void visit(Multiply m);
    public abstract void visit(Divide d);
    public abstract void visit(Equal eq);
    public abstract void visit(NotEqual neq);
    public abstract void visit(More m);
    public abstract void visit(MoreOrEqual meq);
    public abstract void visit(Less l);
    public abstract void visit(LessOrEqual leq);
    public abstract void visit(Constant c);
}
