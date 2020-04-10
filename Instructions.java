import java.util.ArrayList;
public class Instructions implements Expression
{
    protected ArrayList<Expression> inst;

    public Instructions()
    {
        this.inst = new ArrayList<Expression>();
    }
    
    public void accept(Visitor v){
        v.visit(this);
    }
}
