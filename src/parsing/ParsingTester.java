package parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.StackFrame;
import decomposition.Constants;
import decomposition.VariableReplacement;

public class ParsingTester {

	public static void main(String[] args) {
		StackFrame frame = new StackFrame();
		try {
			Scanner sc = new Scanner(new File("input2.txt"));
			while(sc.hasNextLine()){
				ParsingDebug.println(evaluateExpression(sc.nextLine(), frame));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ParsingDebug.println("done.");
		//ParsingDebug.println(frame.arrLists.get("c").get(0));

	}
	private static Object evaluateExpression(String sent, StackFrame frame){
		 VariableReplacement vr= new VariableReplacement();
		 vr.setFrame(frame);
		 sent=sent.trim();
		 boolean replace=true;
		 for(String s: Constants.DECLARATION_KEYWORDS){
			 if(sent.startsWith(s)){
				 replace=false;
				 
			 }
		 }
		 if(replace)
			 sent=vr.replacedVars(sent);
		 //ParsingDebug.println("var replaced : "+sent);
		 SprintParserLexer lexer = new SprintParserLexer(new ANTLRInputStream(sent));
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		 SprintParserParser parser = new SprintParserParser(tokens);
		 ParseTree tree = parser.statement();
		 SprintVisitorVars visitor = new SprintVisitorVars();
		
		 visitor.setFrame(frame);
	     return visitor.visit(tree);

	     //ParsingDebug.println("parsed: "+sent);
	     
	}
}
