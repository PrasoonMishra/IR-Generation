package visitor;

import syntaxtree.*;
import java.util.*;

public class tacoMaker <R,A> extends GJDepthFirst<R,A>{
	   /**
	    * f0 -> MainClass()
	    * f1 -> ( TypeDeclaration() )*
	    * f2 -> <EOF>
	    */
	
		public int count=0,f=0,div=0;
		public String reset = "0";
		public String plist = null;
		public ArrayList<String> trace = new ArrayList<String>();
	

	
	   public R visit(Goal n, A argu) {
	      R _ret=null;
	      argu = (A) reset;
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
	      System.out.println("class "+n.f1.f0.toString());
	      System.out.println("{");
	      System.out.println("public static void main(String[] "+n.f11.f0.toString()+")");
	      System.out.println("{");
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
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
	      n.f14.accept(this, argu);
	      n.f15.accept(this, argu);
	      n.f16.accept(this, argu);
	      System.out.println("}");
	      System.out.println("}");
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
	      System.out.println("class "+n.f1.f0.toString());
	      System.out.println("{");
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      System.out.println("}");
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
	      System.out.println("class "+n.f1.f0.toString()+" extends "+n.f3.f0.toString());
	      System.out.println("{");
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      n.f7.accept(this, argu);
	      System.out.println("}");
	      return _ret;
	   }

	   /**
	    * f0 -> Type()
	    * f1 -> Identifier()
	    * f2 -> ";"
	    */
	   public R visit(VarDeclaration n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      System.out.println(n.f1.f0.toString()+";");
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
	      System.out.print("public ");
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      System.out.print(n.f2.f0.toString()+"(");
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      System.out.println(")");
	      System.out.println("{");
	      n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      n.f7.accept(this, argu);
	      n.f8.accept(this, argu);
	      n.f9.accept(this, argu);
	      String s1 = (String) n.f10.accept(this, argu);
	      System.out.println("return "+s1+";");
	      n.f11.accept(this, argu);
	      n.f12.accept(this, argu);
	      System.out.println("}");
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
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      System.out.print(n.f1.f0.toString());
	      return _ret;
	   }

	   /**
	    * f0 -> ","
	    * f1 -> FormalParameter()
	    */
	   public R visit(FormalParameterRest n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      System.out.print(",");
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
	      String s1 = (String) n.f0.accept(this, argu);
	      System.out.print(s1+" ");
	      return _ret;
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
	      String s1 = "int[] ";
	      return (R) s1;
	   }

	   /**
	    * f0 -> "boolean"
	    */
	   public R visit(BooleanType n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String s1 = "boolean ";
	      return (R) s1;
	   }

	   /**
	    * f0 -> "int"
	    */
	   public R visit(IntegerType n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
	      String s1 = "int ";
	      return (R) s1;
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
	      int i;
	      String a = (String) argu;
	      argu = (A) reset;
	      if(a.equals("1")){
	    	  System.out.println("{");
	      }
	      n.f0.accept(this, argu);
	      if(a.equals("1")) {
	    	  for(i=div;i<trace.size();i++) {
	    		  System.out.println(trace.get(i));
	    	  }
	    	  for(i=0;i<div;i++) {
	    		  System.out.println(trace.get(i));
	    	  }
	    	  System.out.println("}");
	    	  trace.clear();
	    	  div = 0;
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
	      String a = (String) argu;
	      
	      n.f0.accept(this, argu);
	      if(!a.equals("1"));
	      	System.out.println("{");
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      if(!a.equals("1"));
	      	System.out.println("}");
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
	      System.out.println(n.f0.f0.toString()+" = "+s1+";");
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
	      System.out.println(n.f0.f0.toString()+"["+s1+"] = "+s2+";");
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
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String s2 = (String) n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      System.out.println(s1+"."+n.f2.f0.toString()+" = "+s1+";");
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
	      n.f3.accept(this, argu);
	      System.out.println("if("+s1+")");
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      System.out.println("else");
	      n.f6.accept(this, argu);
	      
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
	      String a = "1";
	      A argu1 = (A) a;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s1 = (String) n.f2.accept(this, argu1);
	      div = trace.size();
	      n.f3.accept(this, argu);
	      System.out.println("while("+s1+")");
	      n.f4.accept(this, argu1);
	      
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
	      String a = "1";
	      A argu1 = (A) a;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      String s1 = (String) n.f4.accept(this, argu1);
	      n.f5.accept(this, argu);
	      String s2 = (String) n.f6.accept(this, argu1);
	      div = trace.size();
	      n.f7.accept(this, argu);
	      n.f8.accept(this, argu);
	      n.f9.accept(this, argu);
	      String s3 = (String) n.f10.accept(this, argu1);
	      n.f11.accept(this, argu);
	      System.out.println("for("+n.f2.f0.toString()+" = "+s1+";"+s2+";)");
	      n.f12.accept(this, argu1);
	      return _ret;
	   }

	   /**
	    * f0 -> "System.out.println"
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
	      System.out.println("System.out.println("+s1+");");
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
	      R holder = n.f0.accept(this, argu);
	      return holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(AndExpression n, A argu) {
	      R _ret=null;
	      String a = (String) argu;
	      
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	      
	     
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+" & "+s2+";";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
	      
	      
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "<"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(CompareExpression n, A argu) {
	      R _ret=null;
	      String a = (String) argu;
	      
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	      
	      
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+" < "+s2+";";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
	      
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(PlusExpression n, A argu) {
	      R _ret=null;
	      String a = (String) argu;
	      
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	      
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+" + "+s2+";";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(MinusExpression n, A argu) {
	      R _ret=null;
	      String a = (String) argu;
	      
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	     
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+" - "+s2+";";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(TimesExpression n, A argu) {
	      R _ret=null;
	      String a = (String) argu;
	      
	      String s1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s2 = (String) n.f2.accept(this, argu);
	      
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+" * "+s2+";";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
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
	      String a = (String) argu;
	      
	      String s2 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String s1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      
	      
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s2+"["+s1+"];";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
	      return (R) holder;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	   public R visit(ArrayLength n, A argu) {
	      R _ret=null;
	      String a = (String) argu;
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      
	     
	      
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+n.f0.f0.toString()+".length;";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
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
	      String a = (String) argu;
	      
	      
	      String s1 = (String) n.f0.accept(this, argu);
	      
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      
	      
	      String holder = "t"+count;
	      count++;
	      String p1 = holder+" = "+s1+"."+n.f2.f0.toString()+"("+plist+");";
	      System.out.println(p1);
	      if(a.equals("1"))
	    	  trace.add(p1);
	      
	      n.f5.accept(this, argu);
	      return (R)holder;
	   }

	   /**
	    * f0 -> Expression()
	    * f1 -> ( ExpressionRest() )*
	    */
	   public R visit(ExpressionList n, A argu) {
	      R _ret=null;
	      plist = "";
	      String s1 = (String) n.f0.accept(this, argu);
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
	      n.f0.accept(this, argu);
	      String s1 = (String) n.f1.accept(this, argu);
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
	      R holder = n.f0.accept(this, argu);
	      return holder;
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public R visit(IntegerLiteral n, A argu) {
	      R _ret=null;
	      
	      n.f0.accept(this, argu);
	   
	      String holder = n.f0.toString();
	     
	      return (R) holder;
	   }

	   /**
	    * f0 -> "true"
	    */
	   public R visit(TrueLiteral n, A argu) {
	      R _ret=null;
	      String holder = (String) n.f0.accept(this, argu);
	      return (R) holder;
	   }

	   /**
	    * f0 -> "false"
	    */
	   public R visit(FalseLiteral n, A argu) {
	      R _ret=null;
	      String holder = (String) n.f0.accept(this, argu);
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
	   
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      String s1 = (String) n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      String p1 = holder + " = new int["+s1+"];";
	      System.out.println(p1);
	      
	      return (R)holder;
	      
	   }

	   /**
	    * f0 -> "new"
	    * f1 -> Identifier()
	    * f2 -> "("
	    * f3 -> ")"
	    */
	   public R visit(AllocationExpression n, A argu) {
	      R _ret=null;
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      
	      String holder = "t"+count;
	      count++;
	     
	      System.out.println(holder + " = new "+n.f1.f0.toString()+"();");
	      
	      return (R) holder;
	   }

	   /**
	    * f0 -> "!"
	    * f1 -> ( MessageSend() | PrimaryExpression() )
	    */
	   public R visit(NotExpression n, A argu) {
	      R _ret=null;
	      
	      n.f0.accept(this, argu);
	      String s1 = (String) n.f1.accept(this, argu);
	      String holder = "t"+count;
	      count++;
	      System.out.println(holder+" = "+"!"+s1+";");
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
	      
	      return (R)holder;
	   }

}