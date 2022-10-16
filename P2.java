

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.Map;

import syntaxtree.*;
import visitor.*;

public class P2 {
   public static void main(String [] args) throws FileNotFoundException {
      try {
    	  int a=0;
         Node root = new BuritoJavaParser(System.in).Goal();
         //System.out.println("Program parsed successfully");
         symbolTable r1 = (symbolTable) root.accept(new symbolTableBuilder(),a); // Your assignment part is invoked here.
         root.accept(new typeChecker(),r1);
         root.accept(new tacoGenerator(),r1);
         //System.out.println(r1.Table);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



