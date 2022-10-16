package visitor;
import syntaxtree.*;
import java.util.*;

public class symbolTableBuilder<R,A> extends GJDepthFirst<R,A>{
	/**
	 * f0 -> MainClass()
	 * f1 -> ( TypeDeclaration() )*
	 * f2 -> <EOF>
	 */
	
	public Map<String, Map<String, Map<String,String>>> table = new HashMap<>();
	public Map<String,Map<String,ArrayList<String>>> signTable = new HashMap<>();
	public Map<String,ArrayList<String>> tree = new HashMap<>();
	
	ArrayList<String> signature;
	
	//class->method_name -> var-> type
	//class -> "field" -> var -> type
	
	public R visit(Goal n, A argu) {
		R _ret=null;
		R r1;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		//System.out.println(signTable);
		//System.out.println(table);
		symbolTable finalTable = new symbolTable(table,signTable,tree);
		r1 = (R) finalTable;
		return r1;
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
		      String[] a = {n.f1.f0.toString(),"main"};
		      argu = (A) a;
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
		      String[] s = {n.f1.f0.toString(),"-fields"};
		      argu = (A)s;
		      
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
			  //todo copying superclass method signature 
			  
		      R _ret=null;
		      
		      String[] s = {n.f1.f0.toString(),"-fields"};
		      argu = (A)s;
		      
		      // Copying fields of super class
		      Map<String,String> temp = new HashMap<>();
		      if(table.containsKey(n.f3.f0.toString())) {
		    	  if(table.get(n.f3.f0.toString()).containsKey("-fields")) {
		    		  temp = table.get(n.f3.f0.toString()).get(s[1]);
				      for (Map.Entry<String,String> entry : temp.entrySet()) {
				    	  if(!table.containsKey(s[0])) {
						      table.put(s[0], new HashMap(){{put(s[1],new HashMap(){{put(entry.getKey(),entry.getValue());}});}});		    	  				    		  
				    	  }
				    	  else {
				    		  table.get(s[0]).get(s[1]).put(entry.getKey(),entry.getValue());
				    	  }
				      }
		    	  }
		      }
		      else {
		    	  System.out.println("Does not Type Check");
		    	  System.exit(0);
		      }
		      
		      //copying methods from superclass
		      Map<String,ArrayList<String>> tempMethod = new HashMap<>();
		      if(signTable.containsKey(n.f3.f0.toString())) {
		    	  tempMethod = signTable.get(n.f3.f0.toString());
		    	  for (Map.Entry<String,ArrayList<String>> entry : tempMethod.entrySet()) {
			    	  if(!signTable.containsKey(s[0])) {
					      signTable.put(s[0], new HashMap(){{put(entry.getKey(),entry.getValue());}});		    	  				    		  
			    	  }
			    	  else {
			    		  signTable.get(s[0]).put(entry.getKey(),entry.getValue());
			    	  }
			      }
		    	  
		      }
		      else {
		    	  System.out.println("Does not Type Check");
		    	  System.exit(0);
		      }
		      
		      // building tree hierarchy
		      if(tree.containsKey(n.f3.f0.toString())) {
		    	  tree.get(n.f3.f0.toString()).add(n.f1.f0.toString());
		      }
		      else {
		    	  ArrayList<String> buff = new ArrayList<String>();
		    	  buff.add(n.f1.f0.toString());
		    	  tree.put(n.f3.f0.toString(),buff);
		      }
		      
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
			  R r1;
			  String[] s = (String[])argu;
			  
		      r1 = n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      
		      //if class not initialized
		      if(!table.containsKey(s[0])) {
			      table.put(s[0], new HashMap(){{put(s[1],new HashMap(){{put(n.f1.f0.toString(),(String)r1);}});}});		    		  		    	  
		      }
		      else {
		    	  if(s[1]=="-fields") {
		    		  //check if field type clashes with superclass field type
		    		  if(table.get(s[0]).get(s[1]).containsKey(n.f1.f0.toString()) && table.get(s[0]).get(s[1]).get(n.f1.f0.toString())!=(String)r1) {
		    			  System.out.println("Does not Type Check");
		    			  System.exit(0);
		    		  }
		    		  else {
			    		  table.get(s[0]).get(s[1]).put(n.f1.f0.toString(),(String)r1);		    		  		    			  
		    		  }
		    	  }
		    	  else {
		    		  //check if method variable name clashes with field or method variables
		    		  if((table.get(s[0]).containsKey(s[1]) && table.get(s[0]).get(s[1]).containsKey(n.f1.f0.toString())) || (table.get(s[0]).containsKey("-fields") && table.get(s[0]).get("-fields").containsKey(n.f1.f0.toString()))) 
		    		  {
		    				  System.out.println("Does not Type Check");
		    				  System.exit(0);
		    		  }
		    		  else 
		    		  {
		    			if(table.get(s[0]).containsKey(s[1])){
		    				  table.get(s[0]).get(s[1]).put(n.f1.f0.toString(),(String)r1);
		    			}
		    			else {
		    			  table.get(s[0]).put(s[1],new HashMap(){{put(n.f1.f0.toString(),(String)r1);}});		    			  
		    			}
		    		  }
		    	  }
		      }
		      
		      

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
		      R r1;
		      String[] s = (String[])argu;
		      s[1] = n.f2.f0.toString();
		      argu = (A)s;
		      
		      signature = new ArrayList<String>();
		      
		      
		      n.f0.accept(this, argu);
		      r1 = n.f1.accept(this, argu);
		      signature.add((String)r1);
		      
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
		      
		      if(!signTable.containsKey(s[0])) {
		    	  signTable.put(s[0],new HashMap(){{put(s[1],signature);}});
		      }
		      else {
		    	  if(signTable.get(s[0]).containsKey(s[1])) {
		    		  ArrayList<String> temp = signTable.get(s[0]).get(s[1]);
		    		  if(temp.size()==signature.size()) {
		    			  int iter;
		    			  for(iter=0;iter<temp.size();iter++) {
		    				  if(temp.get(iter)!=signature.get(iter))
		    					  break;
		    			  }
		    			  if(iter<temp.size()) {
		    				  System.out.println("Does not Type Check");
		    				  System.exit(0);
		    			  }
		    			 
		    		  }
		    		  else {
		    			  System.out.println("Does not Type Check");
	    				  System.exit(0);
		    		  }
		    		  
		    	  }
		    	  else {
		    		  signTable.get(s[0]).put(s[1],signature);
		    	  }
		    	  
		      }
		      
		      
		      
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
		      R r1;		      
		      String[] s = (String[])argu;
		      
		      r1 = n.f0.accept(this, argu);   
		      n.f1.accept(this, argu);

		      // check if parameter clashes with field or method variables	  
		      if(table.containsKey(s[0]) && ( table.get(s[0]).containsKey(s[1]) && table.get(s[0]).get(s[1]).containsKey(n.f1.f0.toString()) || table.get(s[0]).containsKey("-fields") && table.get(s[0]).get("-fields").containsKey(n.f1.f0.toString())))   {
		    	  System.out.println("Does not Type Check");
		    	  System.exit(0);
		      }
		      else {
		    	  if(!table.containsKey(s[0])) {
				      table.put(s[0], new HashMap(){{put(s[1],new HashMap(){{put(n.f1.f0.toString(),(String)r1);}});}});		    		  		    	  		    		  
		    	  }
		    	  else {
		    		  if(!table.get(s[0]).containsKey(s[1])) {
		    			  table.get(s[0]).put(s[1],new HashMap(){{put(n.f1.f0.toString(),(String)r1);}});		    			  		    			  
		    		  }
		    		  else {
	    				  table.get(s[0]).get(s[1]).put(n.f1.f0.toString(),(String)r1);		    			  
		    		  }
		    	  }
		      }
		      
		      signature.add((String) r1);
		      

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
			  //R _ret=null;
			  R r1 =null;
		      r1 = n.f0.accept(this, argu);
		      return r1;
		   }

		   /**
		    * f0 -> "int"
		    * f1 -> "["
		    * f2 -> "]"
		    */
		   public R visit(ArrayType n, A argu) {
		      String s = "array";
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return (R) s;
		   }

		   /**
		    * f0 -> "boolean"
		    */
		   public R visit(BooleanType n, A argu) {
			   String s = "boolean";
		      n.f0.accept(this, argu);
		      return (R) s;
		   }

		   /**
		    * f0 -> "int"
		    */
		   public R visit(IntegerType n, A argu) {
			   String s = "int";
		      n.f0.accept(this, argu);
		      return (R) s;
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
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
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
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      n.f6.accept(this, argu);
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
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
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
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
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
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
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
		    * f0 -> "System.out.println"
		    * f1 -> "("
		    * f2 -> Expression()
		    * f3 -> ")"
		    * f4 -> ";"
		    */
		   public R visit(PrintStatement n, A argu) {
		      R _ret=null;
		      String[] s = (String[])argu;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
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
		      n.f0.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "&"
		    * f2 -> PrimaryExpression()
		    */
		   public R visit(AndExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "<"
		    * f2 -> PrimaryExpression()
		    */
		   public R visit(CompareExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "+"
		    * f2 -> PrimaryExpression()
		    */
		   public R visit(PlusExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "-"
		    * f2 -> PrimaryExpression()
		    */
		   public R visit(MinusExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "*"
		    * f2 -> PrimaryExpression()
		    */
		   public R visit(TimesExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "["
		    * f2 -> PrimaryExpression()
		    * f3 -> "]"
		    */
		   public R visit(ArrayLookup n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> PrimaryExpression()
		    * f1 -> "."
		    * f2 -> "length"
		    */
		   public R visit(ArrayLength n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
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
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      n.f5.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> Expression()
		    * f1 -> ( ExpressionRest() )*
		    */
		   public R visit(ExpressionList n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
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
		      n.f1.accept(this, argu);
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
		      n.f0.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> <INTEGER_LITERAL>
		    */
		   public R visit(IntegerLiteral n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> "true"
		    */
		   public R visit(TrueLiteral n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> "false"
		    */
		   public R visit(FalseLiteral n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> <IDENTIFIER>
		    */
		   public R visit(Identifier n, A argu) {
		      R _ret=null;
		      R r1;
		      n.f0.accept(this, argu);
		      r1 = (R) n.f0.toString();
		      return r1;
		   }

		   /**
		    * f0 -> "this"
		    */
		   public R visit(ThisExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      return _ret;
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
		      n.f3.accept(this, argu);
		      n.f4.accept(this, argu);
		      return _ret;
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
		      return _ret;
		   }

		   /**
		    * f0 -> "!"
		    * f1 -> ( MessageSend() | PrimaryExpression() )
		    */
		   public R visit(NotExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      return _ret;
		   }

		   /**
		    * f0 -> "("
		    * f1 -> Expression()
		    * f2 -> ")"
		    */
		   public R visit(BracketExpression n, A argu) {
		      R _ret=null;
		      n.f0.accept(this, argu);
		      n.f1.accept(this, argu);
		      n.f2.accept(this, argu);
		      return _ret;
		   }
}


