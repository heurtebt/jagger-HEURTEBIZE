import java.util.HashMap;
public class VisitorEvaluator extends Visitor
{
    private float evf;
    private String evs;
    private Type t;    
    private HashMap <String,String> varsMap;

    public VisitorEvaluator(){
        this.evf = 0;
        this.evs = "";
        this.varsMap = new HashMap<String,String>();
    }

    public void visit(Negative n){
        n.e.accept(this);
        float tmp = evf;
        evf = -tmp;
    }

    public void visit(Positive p){
        p.e.accept(this);
        float tmp = evf;
        evf = tmp;
    }

    public void visit(Add a){        
        float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        a.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                a.t = Type.STRING;
                break;
            }
        }
        a.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                a.t = Type.STRING;
                break;
            }
        }
        if (ttmp1.equals(ttmp2)){ 
            switch(ttmp1){
                case FLOAT:{
                    evf = tmp1+tmp2;
                    a.t = Type.FLOAT;  
                    break;
                }
                case STRING:{
                    evs = tmps1+tmps2;                    
                    a.t = Type.STRING; 
                    break;
                }
            }
        }else{
            switch(ttmp1){
                case FLOAT:{
                    evs = tmp1+tmps2;                
                    a.t = Type.STRING; 
                    t = Type.STRING;
                    break;
                }
                case STRING:{
                    evs = tmps1+tmp2;                
                    a.t = Type.STRING; 
                    t = Type.STRING;
                    break;
                }
            }
        }
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
        float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        eq.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                break;
            }
        }
        eq.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                break;
            }
        } 
        switch(ttmp1){
            case FLOAT:{
                evf = (tmp1==tmp2)? 1 : 0; 
                break;
            }
            case STRING:{
                evf = (tmps1.equals(tmps2))? 1 : 0; 
                break;
            }
        }
    }

    public void visit(NotEqual neq){
        float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        neq.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                break;
            }
        }
        neq.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                break;
            }
        } 
        switch(ttmp1){
            case FLOAT:{
                evf = (tmp1==tmp2)? 0 : 1; 
                break;
            }
            case STRING:{
                evf = (tmps1.equals(tmp2))? 0 : 1;  
                break;
            }
        }
    }

    public void visit(More m){float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        m.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                break;
            }
        }
        m.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                break;
            }
        } 
        switch(ttmp1){
            case FLOAT:{
                evf = (tmp1>tmp2)? 1 : 0;
                break;
            }
            case STRING:{
                evf = (tmps1.compareTo(tmps2)>0)? 1 : 0;  
                break;
            }
        }  
    }

    public void visit(MoreOrEqual meq){
        float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        meq.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                break;
            }
        }
        meq.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                break;
            }
        } 
        switch(ttmp1){
            case FLOAT:{
                evf = (tmp1>=tmp2)? 1 : 0;  
                break;
            }
            case STRING:{
                evf = (tmps1.compareTo(tmps2)>=0)? 1 : 0;  
            }
        }
    }

    public void visit(Less l){
        float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        l.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                break;
            }
        }
        l.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                break;
            }
        } 
        switch(ttmp1){
            case FLOAT:{
                evf = (tmp1<tmp2)? 1 : 0;  
                break;
            }
            case STRING:{
                evf = (tmps1.compareTo(tmps2)<0)? 1 : 0; 
            }
        }    
    }

    public void visit(LessOrEqual leq){
        float tmp1=(float)0.0, tmp2=(float)0.0;
        String tmps1="", tmps2="";
        leq.e1.accept(this);        
        Type ttmp1 = t;
        switch(ttmp1){
            case FLOAT:{
                tmp1 = evf;
                break;
            }
            case STRING:{
                tmps1 = evs;
                break;
            }
        }
        leq.e2.accept(this);
        Type ttmp2 = t; 
        switch(ttmp2){
            case FLOAT:{
                tmp2 = evf;               
                break;
            }
            case STRING:{
                tmps2 = evs;
                break;
            }
        } 
        switch(ttmp1){
            case FLOAT:{
                evf = (tmp1<=tmp2)? 1 : 0; 
                break;
            }
            case STRING:{
                evf = (tmps1.compareTo(tmps2)<=0)? 1 : 0; 
            }
        }
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
            case 0:{
                ite.e3.accept(this);
                break;
            }
            default:{
                ite.e2.accept(this);
                break;
            }
        }
    }

    public void visit(Print p){
        p.e.accept(this);
        switch(t){
            case FLOAT:
            System.out.println(this.evf);
            break;
            case STRING:       
            System.out.println(this.evs);
            break;
            default:
            System.out.println("An error occured.");
        }
    }

    public void visit(Variable v){
        this.t = v.decl.t;
        if(this.t.equals(Type.FLOAT))
            this.evf = Float.parseFloat(this.varsMap.get(v.decl.id));
        else
            this.evs = this.varsMap.get(v.decl.id);
    }

    public void visit(VariableDecl vd){
        vd.value.accept(this);
        if(vd.t.equals(Type.FLOAT)){
            this.varsMap.put(vd.id,Float.toString(this.evf));}
        else
            this.varsMap.put(vd.id,this.evs);
    }

    public void visit(Scope s){
        for(VariableDecl vd : s.vars.values()){
            vd.accept(this);
        }
        for (Expression e : s.inst){
            e.accept(this);
        }
    }

    public void visit(Assignment a){
        a.e.accept(this);
        if (this.t.equals(Type.FLOAT))
            this.varsMap.put(a.v.decl.id,Float.toString(this.evf));
        else
            this.varsMap.put(a.v.decl.id,this.evs);
    }
    
    public void visit(While w){
        w.e.accept(this);
        while(this.evf!=0){
            for (Expression e : w.inst){
                e.accept(this);
            }
            w.e.accept(this);
        }
    }

}
