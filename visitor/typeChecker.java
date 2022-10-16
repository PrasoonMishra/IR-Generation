package visitor;

import syntaxtree.*;
import java.util.*;

public class typeChecker <R,A> extends GJDepthFirst<R,A>{

		public symbolTable lookup;
		ArrayList<String> p ;
	
	   /**
	    * f0 -> MainClass()
	    * f1 -> ( TypeDeclaration() )*
	    * f2 -> <EOF>
	    */
	   public R visit(Goal n, A argu) {
	      R _ret=null;
	      lookup = (symbolTable) argu;
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
	      String[] s1 = {n.f1.f0.toString(),"main"};
	      argu = (A) s1;
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
	      String[] s1 = {n.f1.f0.toString(),"-fields"};
	      argu = (A) s1;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
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
	      String[] s1 = {n.f1.f0.toString(),"-fields"};
	      argu = (A) s1;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      n.f7.accept(this, argu);
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
	      String[] s1 = (String[]) argu;
	      s1[1] = n.f2.f0.toString();
	      argu = (A) s1;
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
	      return _ret;
	   }

	   /**
	    * f0 -> ","
	    * f1 -> FormalParameter()
	    */
	   public R visit(FormalParameterRest n, A argu) {
	      R _ret=null;
	      n.f0.accept(this, argu);
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
	      R r1;
	      String t1 = (String) n.f0.accept(this, argu);
	      if(!(t1.equals("int") || t1.equals("array") || t1.equals("boolean"))) {
	    	  //todo equate actual value of identifier to t1
	    	  if(!lookup.Table.containsKey(t1)) {
	    		  lookup.terminate("Type-no such type found");
	    	  }
	      }
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "int"
	    * f1 -> "["
	    * f2 -> "]"
	    */
	   public R visit(ArrayType n, A argu) {
	      R _ret=null;
	      String s = "array";
	      R r1 = (R) s;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> "boolean"
	    */
	   public R visit(BooleanType n, A argu) {
	      R _ret=null;
	      String s = "boolean";
	      R r1 = (R) s;
	      n.f0.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> "int"
	    */
	   public R visit(IntegerType n, A argu) {
	      R _ret=null;
	      String s = "int";
	      R r1 = (R) s;
	      n.f0.accept(this, argu);
	      return r1;
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
	      n.f0.accept(this, argu);
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
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
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
	      String t1;
	      String t2;
	      String[] s1 = (String[]) argu;
//	      if(lookup.Table.get(s1[0]).get(s1[1]).containsKey(n.f0.f0.toString())) {
//	    	  t1 = lookup.Table.get(s1[0]).get(s1[1]).get(n.f0.f0.toString());
//	      }
//	      else {
//	    	  lookup.terminate();
//	      }
	      
	      t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      t2 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      
	      if(!t1.equals(t2)) {
	    	  if(!lookup.isSubclass(t1,t2))
	    		  lookup.terminate("Assignment - type not matching");
	    	  else {
	    		  if(lookup.isSubclass(t2,t1))
	    			  lookup.terminate("Cyclic inheritance");
	    	  }
	      }
	      
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
	      String[] s1 = (String[]) argu;
	      String t1 = null;
	      String t2;
	      String t3;
	      
//	      if(lookup.Table.get(s1[0]).get(s1[1]).containsKey(n.f0.f0.toString())) {
//	    	  t1 = lookup.Table.get(s1[0]).get(s1[1]).get(n.f0.f0.toString());
//	      }
//	      else {
//	    	  lookup.terminate();
//	      }
	      
	      t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      t2 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      t3 = (String) n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      
	      if(!(t1.equals("array") && t2.equals("int") && t3.equals("int"))) {
	    	  lookup.terminate("Array assignment- type not matching");
	      }
	      
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
	      String[] s1 = (String[]) argu;
	      String t1;
	      String t2;
	      String t3;
	      t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      t2 = (String) n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      
	      if(lookup.Table.get(t1).get("-fields").containsKey(n.f2.f0.toString())) {
	    	  t3 = lookup.Table.get(t1).get("-fields").get(n.f2.f0.toString());
	    	  if(!t2.equals(t3)) {
	    		  lookup.terminate("class field assignment- type not matching");
	    	  }
	      }
	      else {
	    	  lookup.terminate("class field assignment - no such field");
	      }
	      
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
	      String t1;
	      String[] s1 = (String[]) argu;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      t1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      n.f6.accept(this, argu);
	      
	      
	      
	      if(!t1.equals("boolean")) {
	    	  lookup.terminate("if statement - type not boolean");
	      }
	      
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
	      String t1;
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      t1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      
	      if(!t1.equals("boolean")) {
	    	  lookup.terminate("whiloe statement - type not boolean");
	      }
	      
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
	      String[] s1 = (String[]) argu;
	      String t1=null;
	      String t2=null;
	      String t3;
	      String t4;
	      String t5;
	      if(lookup.Table.get(s1[0]).get(s1[1]).containsKey(n.f2.f0.toString())) {
	    	  t1 = lookup.Table.get(s1[0]).get(s1[1]).get(n.f2.f0.toString());
	      }
	      else {
	    	  lookup.terminate("for statement- no such var1");
	      }
	      
	      if(lookup.Table.get(s1[0]).get(s1[1]).containsKey(n.f8.f0.toString())) {
	    	  t2 = lookup.Table.get(s1[0]).get(s1[1]).get(n.f8.f0.toString());
	      }
	      else {
	    	  lookup.terminate("for statement - no such var2");
	      }
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      t3 = (String) n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      t4 = (String) n.f6.accept(this, argu);
	      n.f7.accept(this, argu);
	      n.f8.accept(this, argu);
	      n.f9.accept(this, argu);
	      t5 = (String) n.f10.accept(this, argu);
	      n.f11.accept(this, argu);
	      n.f12.accept(this, argu);
	      
	      if(!(t1.equals(t3) && t4.equals("boolean") && t2.equals(t5))) {
	    	  lookup.terminate("for statement - condition not boolean type");
	      }
	      
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
	      String t1;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      t1 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      
	      if(!t1.equals(("int"))) {
	    	  lookup.terminate("Print statement - not int");
	      }
	      
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
	      R r1;
	      r1 = n.f0.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(AndExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String t2 = (String) n.f2.accept(this, argu);
	      
	      if(!(t1.equals(t2) && (t1.equals("int") || t1.equals("boolean")))){
	    	  lookup.terminate("and operation- type not matching");
	      }
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "<"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(CompareExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t3 = "boolean";
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String t2 = (String) n.f2.accept(this, argu);
	      
	      if(!(t1.equals(t2) && t1.equals("int"))) {
	    	  lookup.terminate("less than operation - type not matching");
	      }
	      r1 = (R) t3;
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(PlusExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String t2 = (String) n.f2.accept(this, argu);
	      
	      if(!(t1.equals(t2) && t1.equals("int"))) {
	    	  lookup.terminate("plus operation - type not matching");
	      }
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(MinusExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String t2 = (String) n.f2.accept(this, argu);
	      
	      if(!(t1.equals(t2) && t1.equals("int"))) {
	    	  lookup.terminate("minus operation - type not matching");
	      }
	      r1 = (R) t1;
	      
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	   public R visit(TimesExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String t2 = (String) n.f2.accept(this, argu);
	      
	      if(!(t1.equals(t2) && t1.equals("int"))) {
	    	  lookup.terminate("multiplication operation - type not matching");
	      }
	      r1 = (R) t1;
	      
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "["
	    * f2 -> PrimaryExpression()
	    * f3 -> "]"
	    */
	   public R visit(ArrayLookup n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      String t2 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      
	      if(!(t1.equals("array") && t2.equals("int"))) {
	    	  lookup.terminate("array lookup - type not matching");
	      }
	      r1 = (R) t2;
	      
	      return r1;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	   public R visit(ArrayLength n, A argu) {
	      R _ret=null;
	      R r1;
	      String t2 = "int";
	      String t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      
	      if(!t1.equals("array")) {
	    	  lookup.terminate("array length - type not array");
	      }
	      r1 = (R) t2;
	      
	      return r1;
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
	      R r1;
	      String t1;
	      String t2;
	      String t3;
	      
	      p = new ArrayList<String>();
	      t1 = (String) n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      t2 = (String) n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      n.f5.accept(this, argu);
	      
	      t3 = lookup.match(t1,t2,p);
	      r1 = (R) t3;
	      return r1;
	   }

	   /**
	    * f0 -> Expression()
	    * f1 -> ( ExpressionRest() )*
	    */
	   public R visit(ExpressionList n, A argu) {
	      R _ret=null;
	      String t1;
	      
	      t1 = (String) n.f0.accept(this, argu);
	      p.add(t1);
	      
	      n.f1.accept(this, argu);
	      
	      return _ret;
	   }

	   /**
	    * f0 -> ","
	    * f1 -> Expression()
	    */
	   public R visit(ExpressionRest n, A argu) {
	      R _ret=null;
	      String t1;
	      n.f0.accept(this, argu);
	      t1 = (String) n.f1.accept(this, argu);
	      p.add(t1);
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
	      R r1;
	      r1 = n.f0.accept(this, argu);
	      return r1;
	   }

	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public R visit(IntegerLiteral n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = "int";
	      n.f0.accept(this, argu);
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "true"
	    */
	   public R visit(TrueLiteral n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1 = "boolean";
	      n.f0.accept(this, argu);
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "false"
	    */
	   public R visit(FalseLiteral n, A argu) {
		   R _ret=null;
		   R r1;
		   String t1 = "boolean";
		   n.f0.accept(this, argu);
		   r1 = (R) t1;
		   return r1;
	   }

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public R visit(Identifier n, A argu) {
	      R _ret=null;
	      R r1;
	      String[] s1 = (String[]) argu;
	      String t1=null;
	      n.f0.accept(this, argu);
	      
	      t1 = n.f0.toString();
	      
	      if(lookup.Table.containsKey(s1[0])) {
	    	  
	    	  if(lookup.Table.get(s1[0]).containsKey("-fields")) {
				  if(lookup.Table.get(s1[0]).get("-fields").containsKey(n.f0.toString())) {
					  t1 = lookup.Table.get(s1[0]).get("-fields").get(n.f0.toString());
				  }
			  }
	    	  
	    	  if(lookup.Table.get(s1[0]).containsKey(s1[1])) {
		    	  if(lookup.Table.get(s1[0]).get(s1[1]).containsKey(n.f0.toString())) {
		   			  t1 = lookup.Table.get(s1[0]).get(s1[1]).get(n.f0.toString());
		   		  }	    		  
		   	  }
	      }
	      
	      
	      
	      r1= (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "this"
	    */
	   public R visit(ThisExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String[] s1 = (String[]) argu;
	      r1 = (R) s1[0];
	      n.f0.accept(this, argu);
	      return r1;
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
	      R r1;
	      String t1 = "array";
	      String t2;
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      t2 = (String) n.f3.accept(this, argu);
	      n.f4.accept(this, argu);
	      if(!t2.equals("int")){
	    	  lookup.terminate("Array allocation- expression not int");
	      }
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "new"
	    * f1 -> Identifier()
	    * f2 -> "("
	    * f3 -> ")"
	    */
	   public R visit(AllocationExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String[] s1 = (String[]) argu;
	      String t1=null;
	      
	      n.f0.accept(this, argu);
	      n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      n.f3.accept(this, argu);
	      
	      if(lookup.Table.containsKey(n.f1.f0.toString())) {
	    	  t1 = n.f1.f0.toString();
	      }
	      else {
	    	  lookup.terminate("new class object initiation - no such class ");
	      }
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "!"
	    * f1 -> ( MessageSend() | PrimaryExpression() )
	    */
	   public R visit(NotExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1;
	      n.f0.accept(this, argu);
	      t1 = (String) n.f1.accept(this, argu);
	      if(!t1.equals("boolean")) {
	    	  lookup.terminate("not expression - type not boolean");
	      }
	      r1 = (R) t1;
	      return r1;
	   }

	   /**
	    * f0 -> "("
	    * f1 -> Expression()
	    * f2 -> ")"
	    */
	   public R visit(BracketExpression n, A argu) {
	      R _ret=null;
	      R r1;
	      String t1;
	      n.f0.accept(this, argu);
	      t1 = (String) n.f1.accept(this, argu);
	      n.f2.accept(this, argu);
	      
	      r1 = (R) t1;
	      
	      return r1;
	   }
	
}