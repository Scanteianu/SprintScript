package decomposition;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.Library;
import datastructure.StackFrame;
import parsing.SprintParserLexer;
import parsing.SprintParserParser;
import parsing.SprintVisitorVars;

public class RunMethod {
	public static Object executeMethod(String name, StackFrame frame, Library library){
		String current=library.methods.get(name).getBody();
		String rest="";
		do{
			String ecRet[]=BlockExtractor.extractComponent(current, ';', '(', ')', '\"');
			if(ecRet==null)
				break;
			current=ecRet[0].trim();
			rest=ecRet[1];
			
			if(current.startsWith(Constants.KEYWORD_IF)){
				
			}
			else if(current.startsWith(Constants.KEYWORD_WHILE)){
				
			}
			else if(current.startsWith(Constants.KEYWORD_PARALLEL)){
				
			}
			else{
				Object output=evaluateExpression(current, frame);
				if(output!=null){
					if(output.toString().startsWith("var:")){
						//TODO: return variable from stack
					}
					return output.toString();
				}
					
				current=rest;
			}
		
		}while(rest.trim().length()>0);
		return null;
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
		 if(sent==null)
			 return null;
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
