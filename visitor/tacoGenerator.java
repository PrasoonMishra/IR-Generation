package visitor;

import syntaxtree.*;
import java.util.*;


public class tacoGenerator <R,A> extends GJDepthFirst<R,A>{
	
	  int count = 0,div1=0,div2=0,j;
	  String plist = "";
	  String v,k;
	  public ArrayList<String> trace = new ArrayList<String>();
	  public Map<String,String> tempType = new HashMap<>();
	  ArrayList <Pair <String,Integer> > taco = new ArrayList <Pair <String,Integer> > ();
	  ArrayList<Integer> traceSize = new ArrayList<Integer>();
	  symbolTable lookup;

	
	  /**
	    * f0 -> MainClass()
	    * f1 -> ( TypeDeclaration() )*
	    * f2 -> <EOF>
	    */
	   public R visit(Goal n, A argu) {
	      R _ret=null;
	      lookup = (symbolTable)argu;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      return _ret;
	   }

	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "{"
	    * f3 -> "public"
	    * f4 -> "static"
	    * f5 -> "void"
	    * f6 -> "main"
	    * f7 -> "("
	    * f8 -> "String"
	    * f9 -> "["
	    * f10 -> "]"
	    * f11 -> Identifier()
	    * f12 -> ")"
	    * f13 -> "{"
	    * f14 -> PrintStatement()
	    * f15 -> "}"
	    * f16 -> "}"
	    */
	   public R visit(MainClass n, A argu) {
	      R _ret=null;
	      String[] a = {n.f1.f0.toString(),"main","0"};
	      argu = (A) a;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      taco.add(new Pair("class "+n.f1.f0.toString()+"{",1));
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      n.f7.accept(this, argu);
	      n.f8.accept(this, argu);
	      n.f9.accept(this, argu);
	      n.f10.accept(this, argu);
	      n.f11.accept(this, argu);
	      n.f12.accept(this, argu);
	      n.f13.accept(this, argu);
	      taco.add(new Pair("public static void main(String[] "+n.f11.f0.toString()+"){",1));
	      n.f14.accept(this, argu);
	      n.f15.accept(this, argu);
	      taco.add(new Pair("}",1));
	      n.f16.accept(this, argu);
	      taco.add(new Pair("}",1));

	      System.out.println(taco.get(0).getKey());
	      System.out.println(taco.get(1).getKey());
	      for (Map.Entry<String,String> entry : tempType.entrySet()){
	      		v = entry.getValue();
	      		k = entry.getKey();
	      		if(v.equals("array"))
	      			v = "int[]";
	      		System.out.println(v+" "+k+";");	
	      }
	      for(j=2;j<taco.size();j++){
	      	if(taco.get(j).getValue() == 1)
	      		System.out.println(taco.get(j).getKey());
	      	else
	      		System.out.print(taco.get(j).getKey());
	      }
	      taco.clear();
	      tempType.clear();
            


	      return _ret;
	   }

	   /**
	    * f0 -> ClassDeclaration()
	    *       | ClassExtendsDeclaration()
	    */
	   public R visit(TypeDeclaration n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      return _ret;
	   }

	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "{"
	    * f3 -> ( VarDeclaration() )*
	    * f4 -> ( MethodDeclaration() )*
	    * f5 -> "}"
	    */
	   public R visit(ClassDeclaration n, A argu) {
	      R _ret=null;
	      String a[] = {n.f1.f0.toString(),"","0"};
	      argu = (A) a;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      taco.add(new Pair("class "+n.f1.f0.toString()+"{",1));
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      taco.add(new Pair("}",1));

	      System.out.println(taco.get(0).getKey());
	      for (Map.Entry<String,String> entry : tempType.entrySet()){
	      		v = entry.getValue();
	      		k = entry.getKey();
	      		if(v.equals("array"))
	      			v = "int[]";
	      		System.out.println(v+" "+k+";");	
	      }
	      for(j=1;j<taco.size();j++){
	      	if(taco.get(j).getValue() == 1)
	      		System.out.println(taco.get(j).getKey());
	      	else
	      		System.out.print(taco.get(j).getKey());
	      }
	      taco.clear();
	      tempType.clear();

	      return _ret;
	   }

	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "extends"
	    * f3 -> Identifier()
	    * f4 -> "{"
	    * f5 -> ( VarDeclaration() )*
	    * f6 -> ( MethodDeclaration() )*
	    * f7 -> "}"
	    */
	   public R visit(ClassExtendsDeclaration n, A argu) {
	      R _ret=null;
	      String a[] = {n.f1.f0.toString(),"","0"};
	      argu = (A) a;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      taco.add(new Pair("class "+n.f1.f0.toString()+" extends "+n.f3.f0.toString()+"{",1));
	      n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      n.f7.accept(this, argu);
	      taco.add(new Pair("}",1));

	      System.out.println(taco.get(0).getKey());
	      for (Map.Entry<String,String> entry : tempType.entrySet()){
	      		v = entry.getValue();
	      		k = entry.getKey();
	      		if(v.equals("array"))
	      			v = "int[]";
	      		System.out.println(v+" "+k+";");	
	      }
	      for(j=1;j<taco.size();j++){
	      	if(taco.get(j).getValue() == 1)
	      		System.out.println(taco.get(j).getKey());
	      	else
	      		System.out.print(taco.get(j).getKey());
	      }
	      taco.clear();
	      tempType.clear();
	      
	      return _ret;
	   }

	   /**
	    * f0 -> Type()
	    * f1 -> Identifier()
	    * f2 -> ";"
	    */
	   public R visit(VarDeclaration n, A argu) {
	      R _ret=null;
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      taco.add(new Pair(s1+" "+n.f1.f0.toString()+";",1));
	      return _ret;
	   }

	   /**
	    * f0 -> "public"
	    * f1 -> Type()
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( FormalParameterList() )?
	    * f5 -> ")"
	    * f6 -> "{"
	    * f7 -> ( VarDeclaration() )*
	    * f8 -> ( Statement() )*
	    * f9 -> "return"
	    * f10 -> Expression()
	    * f11 -> ";"
	    * f12 -> "}"
	    */
	   public R visit(MethodDeclaration n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      a[1] = n.f2.f0.toString();
	      argu = (A) a;
	      n.f0.accept(this, argu);
	      String s1 = (String) n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      taco.add(new Pair("public "+s1+" "+n.f2.f0.toString()+"(",0));
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      taco.add(new Pair(")",1));
	      n.f6.accept(this, argu);
	      taco.add(new Pair("{",1));
	      n.f7.accept(this, argu);
	      n.f8.accept(this, argu);
	      n.f9.accept(this, argu);
	      String s2 = (String) n.f10.accept(this, argu);
	      taco.add(new Pair("return "+s2+";",1));
	      n.f11.accept(this, argu);
	      n.f12.accept(this, argu);
	      taco.add(new Pair("}",1));
	      return _ret;
	   }

	   /**
	    * f0 -> FormalParameter()
	    * f1 -> ( FormalParameterRest() )*
	    */
	   public R visit(FormalParameterList n, A argu) {
	      R _ret=null;
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> Type()
	    * f1 -> Identifier()
	    */
	   public R visit(FormalParameter n, A argu) {
	      R _ret=null;
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String p1 = s1+" "+n.f1.f0.toString();
	      taco.add(new Pair(p1,0));
	      return _ret;
	   }

	   /**
	    * f0 -> ","
	    * f1 -> FormalParameter()
	    */
	   public R visit(FormalParameterRest n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      taco.add(new Pair(",",0));
	      n.f1.accept(this, argu);
	      return _ret;
	   }

	   /**
	    * f0 -> ArrayType()
	    *       | BooleanType()
	    *       | IntegerType()
	    *       | Identifier()
	    */
	   public R visit(Type n, A argu) {
	      R _ret=null;
	      R r1 = n.f0.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> "int"
	    * f1 -> "["
	    * f2 -> "]"
	    */
	   public R visit(ArrayType n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      String r1 = "int[]";
	      return (R)r1;
	   }

	   /**
	    * f0 -> "boolean"
	    */
	   public R visit(BooleanType n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String r1 = "boolean";
	      return (R)r1;
	   }

	   /**
	    * f0 -> "int"
	    */
	   public R visit(IntegerType n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String r1 = "int";
	      return (R)r1;
	   }

	   /**
	    * f0 -> Block()
	    *       | AssignmentStatement()
	    *       | ArrayAssignmentStatement()
	    *       | FieldAssignmentStatement()
	    *       | IfStatement()
	    *       | WhileStatement()
	    *       | ForStatement()
	    *       | PrintStatement()
	    */
	   public R visit(Statement n, A argu) {
		      R _ret=null;
		      String[] a = (String[])argu;
		      String tbuff = a[2];
		      a[2] = "0";
		      int limit;
		      argu = (A) a;
		      n.f0.accept(this, argu);
		      if(tbuff.equals("1")||tbuff.equals("2")) {
		    	  limit = trace.size();
		    	  for(int i=trace.size()-traceSize.get(traceSize.size()-1);i<limit;i++) {
		    		  taco.add(new Pair(trace.get(i),1));
		    	  }
		    	  for(int i=trace.size()-traceSize.get(traceSize.size()-1);i<limit;i++) {
		    		  trace.remove(trace.size()-1);
		    	  }
		    	  
		    	  traceSize.remove(traceSize.size()-1);		    	  
		      }
		      if(tbuff.equals("2")) {
		    	  limit = trace.size();
		    	  for(int i=trace.size()-traceSize.get(traceSize.size()-1);i<limit;i++) {
		    		  taco.add(new Pair(trace.get(i),1));
		    	  }
		    	  for(int i=trace.size()-traceSize.get(traceSize.size()-1);i<limit;i++) {
		    		  trace.remove(trace.size()-1);
		    	  }
		    	  
		    	  traceSize.remove(traceSize.size()-1);			    	  
		      }
		      return _ret;
	   }

	   /**
	    * f0 -> "{"
	    * f1 -> ( Statement() )*
	    * f2 -> "}"
	    */
	   public R visit(Block n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      taco.add(new Pair("{",1));
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      taco.add(new Pair("}",1));
	      return _ret;
	   }

	   /**
	    * f0 -> Identifier()
	    * f1 -> "="
	    * f2 -> Expression()
	    * f3 -> ";"
	    */
	   public R visit(AssignmentStatement n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String p1 = n.f0.f0.toString()+" = "+s1+";";
	      taco.add(new Pair(p1,1));
	      return _ret;
	   }

	   /**
	    * f0 -> Identifier()
	    * f1 -> "["
	    * f2 -> Expression()
	    * f3 -> "]"
	    * f4 -> "="
	    * f5 -> Expression()
	    * f6 -> ";"
	    */
	   public R visit(ArrayAssignmentStatement n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      String s2 = (String) n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      String p1 = n.f0.f0.toString()+"["+s1+"] = "+s2+";";
	      taco.add(new Pair(p1,1));
	      return _ret;
	   }

	   /**
	    * f0 -> Expression()
	    * f1 -> "."
	    * f2 -> Identifier()
	    * f3 -> "="
	    * f4 -> Expression()
	    * f5 -> ";"
	    */
	   public R visit(FieldAssignmentStatement n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String s2 = (String) n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      if(s1.equals("this")) {
	    	  s1 = a[0];
	      }
	      String p1 = s1+"."+n.f2.f0.toString()+" = "+s2+";";
	      taco.add(new Pair(p1,1));
	      return _ret;
	   }

	   /**
	    * f0 -> "if"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> Statement()
	    * f5 -> "else"
	    * f6 -> Statement()
	    */
	   public R visit(IfStatement n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s1 = (String) n.f2.accept(this, argu);
	      String p1 = "if("+s1+"){";
	      taco.add(new Pair(p1,1));
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      taco.add(new Pair("}",1));
	      taco.add(new Pair("else {",1));
	      n.f6.accept(this, argu);
	      taco.add(new Pair("}",1));
	      return _ret;
	   }

	   /**
	    * f0 -> "while"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> Statement()
	    */
	   public R visit(WhileStatement n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      a[2]="1";
	      A argu1 = (A) a;
	      div1 = trace.size();
	      String s1 = (String) n.f2.accept(this, argu1);
	      div2 = trace.size();
	      traceSize.add(div2-div1);
	      n.f3.accept(this, argu);
	      taco.add(new Pair("while("+s1+"){",1));
	      n.f4.accept(this, argu1);
	      taco.add(new Pair("}",1));
	      return _ret;
	   }

	   /**
	    * f0 -> "for"
	    * f1 -> "("
	    * f2 -> Identifier()
	    * f3 -> "="
	    * f4 -> Expression()
	    * f5 -> ";"
	    * f6 -> Expression()
	    * f7 -> ";"
	    * f8 -> Identifier()
	    * f9 -> "="
	    * f10 -> Expression()
	    * f11 -> ")"
	    * f12 -> Statement()
	    */
	   public R visit(ForStatement n, A argu) {
	      R _ret=null;
	      String dummy = "dummy"+count;
	      count++;
	      tempType.put(dummy,"int");
	      String[] a = (String[]) argu;
	      a[2] = "2";
	      A argu1 = (A) a;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String s1 = (String) n.f4.accept(this, argu);
	      taco.add(new Pair(n.f2.f0.toString()+" = "+s1+";",1));
	      n.f5.accept(this, argu);
	      div1 = trace.size();
	      String s2 = (String) n.f6.accept(this, argu1);
	      div2 = trace.size();
	      traceSize.add(div2-div1);
	      n.f7.accept(this, argu);
	      n.f8.accept(this, argu);
	      n.f9.accept(this, argu);
	      div1 = trace.size();
	      String s3 = (String) n.f10.accept(this, argu1);
	      div2 = trace.size();
	      traceSize.add(div2-div1);
	      n.f11.accept(this, argu);
	      taco.add(new Pair("for("+n.f2.f0.toString()+" = "+s1+";"+s2+";"+dummy+" = 1){",1));

	      n.f12.accept(this, argu1);
	      taco.add(new Pair("}",1));
	      return _ret;
	   }

	   /**
	    * f0 -> "taco.add(new Pair"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> ";"
	    */
	   public R visit(PrintStatement n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      String p1 = "System.out.println("+s1+");";
	      taco.add(new Pair(p1,1));
	      return _ret;
	   }

	   /**
	    * f0 -> AndExpression()
	    *       | CompareExpression()
	    *       | PlusExpression()
	    *       | MinusExpression()
	    *       | TimesExpression()
	    *       | ArrayLookup()
	    *       | ArrayLength()
	    *       | MessageSend()
	    *       | PrimaryExpression()
	    */
	   public R visit(Expression n, A argu) {
	      R _ret=null;
	      R r1 = n.f0.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(AndExpression n, A argu) {
		    R _ret=null;
		    String[] a = (String[]) argu;
		    String s1 = (String) n.f0.accept(this, argu);
	        n.f1.accept(this, argu);
		    String s2 = (String) n.f2.accept(this, argu);
		    String holder = "t"+count;
            count++;
            String p1 = holder+" = "+s1+" & "+s2+";";
		    taco.add(new Pair(p1,1));
		    if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(p1);
		    tempType.put(holder,tempType.get(s1));       //todo
		    return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "<"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(CompareExpression n, A argu) {
		    R _ret=null;
		    String[] a = (String[]) argu;
		    String s1 = (String) n.f0.accept(this, argu);
	        n.f1.accept(this, argu);
		    String s2 = (String) n.f2.accept(this, argu);
		    String holder = "t"+count;
            count++;
            String p1 = holder+" = "+s1+" < "+s2+";";
		    taco.add(new Pair(p1,1));
		    if(a[2].equals("1")||a[2].equals("2")) {
		    	  trace.add(p1);
		    }
		    tempType.put(holder,"boolean");
		    return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(PlusExpression n, A argu) {
		    R _ret=null;
		    String[] a = (String[]) argu;
		    String s1 = (String) n.f0.accept(this, argu);
	        n.f1.accept(this, argu);
		    String s2 = (String) n.f2.accept(this, argu);
		    String holder = "t"+count;
            count++;
            String p1 = holder+" = "+s1+" + "+s2+";";
		    taco.add(new Pair(p1,1));
		    if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(p1);
		    tempType.put(holder,"int");
		    return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(MinusExpression n, A argu) {
		    R _ret=null;
		    String[] a = (String[]) argu;
		    String s1 = (String) n.f0.accept(this, argu);
	        n.f1.accept(this, argu);
		    String s2 = (String) n.f2.accept(this, argu);
		    String holder = "t"+count;
            count++;
            String p1 = holder+" = "+s1+" - "+s2+";";
		    taco.add(new Pair(p1,1));
		    if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(p1);
		    tempType.put(holder,"int");
		    return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(TimesExpression n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+" * "+s2+";";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,"int");
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "["
	    * f2 -> PrimaryExpression()
	    * f3 -> "]"
	    */
	   public R visit(ArrayLookup n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+"["+s2+"];";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,"int");
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	   public R visit(ArrayLength n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      R s1 = n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+".length;";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,"int");
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( ExpressionList() )?
	    * f5 -> ")"
	    */
	   public R visit(MessageSend n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+"."+n.f2.f0.toString()+"("+plist+");";
	      plist = "";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      if(lookup.SignTable.containsKey(tempType.get(s1)))
	    	  tempType.put(holder,lookup.SignTable.get(tempType.get(s1)).get(n.f2.f0.toString()).get(0));
	      else {
	    	  if(s1.equals("this"))
		    	  tempType.put(holder,lookup.SignTable.get(a[0]).get(n.f2.f0.toString()).get(0));
	    	  else {
	    		  String cbuff = a[0];
	    		  if(lookup.Table.get(a[0]).containsKey("-fields") && lookup.Table.get(a[0]).get("-fields").containsKey(s1)) {
	    			  cbuff = lookup.Table.get(a[0]).get("-fields").get(s1);
	    		  }
	    		  else {
	    			  cbuff = lookup.Table.get(a[0]).get(a[1]).get(s1);
	    		  }
	    		  tempType.put(holder,lookup.SignTable.get(cbuff).get(n.f2.f0.toString()).get(0));
	    	  }
	    		  
	      }
	      
	      
	      return (R) holder;
	   }

	   /**
	    * f0 -> Expression()
	    * f1 -> ( ExpressionRest() )*
	    */
	   public R visit(ExpressionList n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      plist = "";
	      String s1 = (String) n.f0.accept(this, argu);
	      if(s1.equals("this")) {
	    	  String holder = "t"+count;
		      count++;
		      s1 = holder+" = this;";
		      taco.add(new Pair(s1,1));
		      if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(s1);
		      plist = plist + holder;
		      tempType.put(holder,a[0]);
	      }
	      else
	    	  plist = plist + s1;
	      n.f1.accept(this, argu);
	      return _ret;
	   }

	   /**
	    * f0 -> ","
	    * f1 -> Expression()
	    */
	   public R visit(ExpressionRest n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      n.f0.accept(this, argu);
	      String s1 = (String) n.f1.accept(this, argu);
	      if(s1.equals("this")) {
	    	  String holder = "t"+count;
		      count++;
		      s1 = holder+" = this;";
		      taco.add(new Pair(s1,1));
		      if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(s1);
		      plist = plist +","+holder;
		      tempType.put(holder,a[0]);
	      }
	      else
	    	  plist = plist + ","+s1;
	      return _ret;
	   }

	   /**
	    * f0 -> IntegerLiteral()
	    *       | TrueLiteral()
	    *       | FalseLiteral()
	    *       | Identifier()
	    *       | ThisExpression()
	    *       | ArrayAllocationExpression()
	    *       | AllocationExpression()
	    *       | NotExpression()
	    *       | BracketExpression()
	    */
	   public R visit(PrimaryExpression n, A argu) {
	      R _ret=null;
	      R r1 = n.f0.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public R visit(IntegerLiteral n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      n.f0.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+n.f0.toString()+";";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,"int");
	      return (R) holder;
	   }

	   /**
	    * f0 -> "true"
	    */
	   public R visit(TrueLiteral n, A argu) {
		      R _ret=null;
		      String[] a = (String[]) argu;
		      n.f0.accept(this, argu);
		      String holder = "t"+count;
		      count++;
		      String p1 = holder+" = "+n.f0.toString()+";";
		      taco.add(new Pair(p1,1));
		      if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(p1);
		      tempType.put(holder,"boolean");
		      return (R) holder;
	   }

	   /**
	    * f0 -> "false"
	    */
	   public R visit(FalseLiteral n, A argu) {
		      R _ret=null;
		      String[] a = (String[]) argu;
		      n.f0.accept(this, argu);
		      String holder = "t"+count;
		      count++;
		      String p1 = holder+" = "+n.f0.toString()+";";
		      taco.add(new Pair(p1,1));
		      if(a[2].equals("1")||a[2].equals("2"))
		    	  trace.add(p1);
		      tempType.put(holder,"boolean");
		      return (R) holder;
	   }

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public R visit(Identifier n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String holder = n.f0.toString();
	      return (R) holder;
	   }

	   /**
	    * f0 -> "this"
	    */
	   public R visit(ThisExpression n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String holder = n.f0.toString();
	      return (R) holder;
	   }

	   /**
	    * f0 -> "new"
	    * f1 -> "int"
	    * f2 -> "["
	    * f3 -> Expression()
	    * f4 -> "]"
	    */
	   public R visit(ArrayAllocationExpression n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      String s1 = (String) n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = new int["+s1+"];";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,"int[]");
	      return (R) holder;
	   }

	   /**
	    * f0 -> "new"
	    * f1 -> Identifier()
	    * f2 -> "("
	    * f3 -> ")"
	    */
	   public R visit(AllocationExpression n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder + " = new "+n.f1.f0.toString()+"();";
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,n.f1.f0.toString());
	      return (R) holder;
	   }

	   /**
	    * f0 -> "!"
	    * f1 -> ( MessageSend() | PrimaryExpression() )
	    */
	   public R visit(NotExpression n, A argu) {
	      R _ret=null;
	      String[] a = (String[]) argu;
	      n.f0.accept(this, argu);
	      String s1 = (String) n.f1.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder + " = !" + s1+";"; 
	      taco.add(new Pair(p1,1));
	      if(a[2].equals("1")||a[2].equals("2"))
	    	  trace.add(p1);
	      tempType.put(holder,"boolean");
	      return (R) holder;
	   }

	   /**
	    * f0 -> "("
	    * f1 -> Expression()
	    * f2 -> ")"
	    */
	   public R visit(BracketExpression n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String holder = (String) n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      return (R) holder;
	   }
}


