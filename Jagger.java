/* Jagger.java */
/* Generated By:JavaCC: Do not edit this line. Jagger.java */
import java.io.*;
public class Jagger implements JaggerConstants {
    public static Scope initial_scope;
    public static void main(String args[]) throws ParseException, FileNotFoundException
    {
        File f = new File(args[0]);
        InputStream is = new FileInputStream(f);
        Jagger parser = new Jagger(is);
        Scope initial_scope = new Scope();
        parser.mainloop();
    }

// Main lopp: read expressions on a line until end of file.
//     mainloop → (expression <EOF>)* <EOF>
  static final public void mainloop() throws ParseException {Expression a;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LET:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      a = scope();
      jj_consume_token(0);
System.out.println("\nPretty Print :-------------\n\n");
        VisitorPrettyPrinter pp = new VisitorPrettyPrinter();
        a.accept(pp);
        System.out.println("\n\n---------------------------\nBinder :-------------\n\n");
        VisitorBinder vb = new VisitorBinder();
        a.accept(vb);
        System.out.println("\n\n---------------------------\nRenamer :-------------\n\n");
        VisitorRenamer vr = new VisitorRenamer();
        a.accept(vr);
        System.out.println("\n\n---------------------------\nType Checker :-------------\n\n");
        VisitorTypeChecker tp = new VisitorTypeChecker();
        a.accept(tp);
        if(!tp.hasError()){
             System.out.println("OK\n\n---------------------------\nEvaluator :-------------\n\n");
                               VisitorEvaluator eval = new VisitorEvaluator();
                         a.accept(eval);
        }else{
            System.out.println("The Type Checker found an error!\n\n---------------------------");
                    }
    }
    jj_consume_token(0);
}

//Scope
//S -> let (D)* in (Ep)+ end
  static final public Expression scope() throws ParseException {Expression e; Scope s= new Scope(); initial_scope = s;
    jj_consume_token(LET);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      declaration();
    }
    jj_consume_token(IN);
    label_3:
    while (true) {
      e = statement();
s.inst.add(e);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUMBER:
      case STRING:
      case IF:
      case PRINT:
      case LET:
      case VARNAME:
      case 19:
      case 28:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
    }
    jj_consume_token(END);
{if ("" != null) return s;}
    throw new Error("Missing return statement in function");
}

//Declaration
//D -> var VARNAME := (Comp|ITE)
  static final public void declaration() throws ParseException {Token t; Expression e;
    jj_consume_token(VAR);
    t = jj_consume_token(VARNAME);
    jj_consume_token(ASSIGN);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IF:{
      e = ifthenelse();
      break;
      }
    case NUMBER:
    case STRING:
    case VARNAME:
    case 19:
    case 28:{
      e = comparison();
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
initial_scope.addDeclaration(t.toString(),new VariableDecl(t.toString(),e));
}

//Statement
//S -> E
  static final public Expression statement() throws ParseException {Expression e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PRINT:{
      e = print();
      break;
      }
    case IF:{
      e = ifthenelse();
      break;
      }
    case NUMBER:
    case STRING:
    case VARNAME:
    case 19:
    case 28:{
      e = comparison();
      break;
      }
    case LET:{
      e = scope();
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

//Print
//P -> 'print(' Comp ')'
  static final public Expression print() throws ParseException {Expression a;
    jj_consume_token(PRINT);
    jj_consume_token(19);
    a = comparison();
    jj_consume_token(20);
{if ("" != null) return new Print(a);}
    throw new Error("Missing return statement in function");
}

//If-Then-Else
//ITE -> 'if' (Comp|ITE) 'then' S 'else' S
  static final public Expression ifthenelse() throws ParseException {Expression a,b,c;
    jj_consume_token(IF);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IF:{
      a = ifthenelse();
      break;
      }
    case NUMBER:
    case STRING:
    case VARNAME:
    case 19:
    case 28:{
      a = comparison();
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(THEN);
    b = statement();
    jj_consume_token(ELSE);
    c = statement();
{if ("" != null) return new IfThenElse(a,b,c);}
    throw new Error("Missing return statement in function");
}

// Comparison
// Comp -> E ('='E | '<>'E | '<'E | '>'E | '>='E | '<='E)*
  static final public Expression comparison() throws ParseException {Expression a,b;
    a = AddSub();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 21:
    case 22:
    case 23:
    case 24:
    case 25:
    case 26:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 21:{
        jj_consume_token(21);
        b = comparison();
a = new Equal(a,b);
        break;
        }
      case 22:{
        jj_consume_token(22);
        b = comparison();
a = new NotEqual(a,b);
        break;
        }
      case 23:{
        jj_consume_token(23);
        b = comparison();
a = new Less(a,b);
        break;
        }
      case 24:{
        jj_consume_token(24);
        b = comparison();
a = new More(a,b);
        break;
        }
      case 25:{
        jj_consume_token(25);
        b = comparison();
a = new MoreOrEqual(a,b);
        break;
        }
      case 26:{
        jj_consume_token(26);
        b = comparison();
a = new LessOrEqual(a,b);
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      ;
    }
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
}

// AddSub
// E -> T ('+'T | '-'T)*
  static final public Expression AddSub() throws ParseException {Expression a,b;
    a = term();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 27:
    case 28:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 27:{
        jj_consume_token(27);
        b = AddSub();
a = new Add(a,b);
        break;
        }
      case 28:{
        jj_consume_token(28);
        b = AddSub();
a = new Substract(a,b);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      ;
    }
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
}

// Term.
// T -> U ('*'U | '/'U)*
  static final public Expression term() throws ParseException {Expression a,b;
    a = unary();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 29:
      case 30:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 29:{
        jj_consume_token(29);
        b = factor();
a = new Multiply(a,b);
        break;
        }
      case 30:{
        jj_consume_token(30);
        b = factor();
a = new Divide(a,b);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return a;}
    throw new Error("Missing return statement in function");
}

//Unary.
//U -> ('-'F) | F
  static final public Expression unary() throws ParseException {Expression a;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 28:{
      jj_consume_token(28);
      a = factor();
{if ("" != null) return new Negative(a);}
      break;
      }
    case NUMBER:
    case STRING:
    case VARNAME:
    case 19:{
      a = factor();
{if ("" != null) return a;}
      break;
      }
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

// Factor of an expression.
// F -> <VARNAME>|<NUMBER>|<STRING>| "(" E ")"
  static final public Expression factor() throws ParseException {Token t; Expression e;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case VARNAME:{
      t = jj_consume_token(VARNAME);
{if ("" != null) return new Variable(t.toString());}
      break;
      }
    case NUMBER:{
      t = jj_consume_token(NUMBER);
{if ("" != null) return new Constant(Float.parseFloat(t.toString()));}
      break;
      }
    case STRING:{
      t = jj_consume_token(STRING);
{if ("" != null) return new ConstantString(t.toString());}
      break;
      }
    case 19:{
      jj_consume_token(19);
      e = comparison();
      jj_consume_token(20);
{if ("" != null) return e;}
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public JaggerTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static {
	   jj_la1_init_0();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x2000,0x10000,0x100a32c0,0x100a02c0,0x100a32c0,0x100a02c0,0x7e00000,0x7e00000,0x18000000,0x18000000,0x60000000,0x60000000,0x100a00c0,0xa00c0,};
	}

  /** Constructor with InputStream. */
  public Jagger(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Jagger(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new JaggerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Jagger(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new JaggerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new JaggerTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Jagger(JaggerTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(JaggerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[31];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 14; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 31; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
