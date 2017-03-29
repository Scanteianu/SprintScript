package decomposition;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import datastructure.Library;
import datastructure.StackFrame;
import datastructure.TailCall;
import parsing.SprintParserLexer;
import parsing.SprintParserParser;
import parsing.SprintVisitorVars;

public class RunMethod {
	public static Object executeMethod(String name, StackFrame frame, Library library){
		Object returnObj=executeBlock(library.methods.get(name).getBody(),frame,library);
		while(returnObj instanceof TailCall){
			TailCall call=(TailCall)returnObj;
			returnObj=executeBlock(library.methods.get(call.methName).getBody(),call.frame,library);
			//DecompDebug.println("TailSuccess");
		}
		return returnObj;
	}
	public static Object executeBlock(String block, StackFrame frame, Library library){
		String current=block;
		String rest="";
		do{
			String ecRet[]=BlockExtractor.extractComponent(current, ';', '(', ')', '\"');
			if(ecRet==null)
				break;
			current=ecRet[0].trim();
			rest=ecRet[1];
			
			if(current.startsWith(Constants.KEYWORD_IF)){
				String all=current+";\n"+rest;
				String[] blockParsed=BlockExtractor.extractBlock(all);
				String elseStr=null;
				rest=blockParsed[3];
				String condition="return "+blockParsed[1];
				if(blockParsed[3].trim().startsWith(Constants.KEYWORD_ELSE)){
					String shortened=blockParsed[3].substring(blockParsed[3].indexOf(Constants.OPEN_BRACE));
					String ceRet[]=BlockExtractor.extractChunk(shortened, Constants.OPEN_BRACE, Constants.CLOSE_BRACE, Constants.QUOTE);
					elseStr=ceRet[0];
					rest=ceRet[1];
				}
				if(evaluateExpression(condition,frame,library).toString().equals("1")){
					Object returnObj=executeBlock(blockParsed[2],frame,library);
					if(returnObj!=null)
						return outConvert(returnObj, frame);
					
				}
				else if(elseStr!=null){
					Object returnObj=executeBlock(elseStr,frame,library);
					if(returnObj!=null)
						return outConvert(returnObj, frame);
				}
				
				
			}
			else if(current.startsWith(Constants.KEYWORD_WHILE)){
				String all=current+";\n"+rest;
				String[] blockParsed=BlockExtractor.extractBlock(all);
				rest=blockParsed[3];
				String condition="return "+blockParsed[1];
				while(evaluateExpression(condition,frame,library).toString().equals("1")){
					Object returnObj=executeBlock(blockParsed[2],frame,library);
					if(returnObj!=null)
						return outConvert(returnObj, frame);
					
				}
			}
			else if(current.startsWith(Constants.KEYWORD_PARALLEL)){
				
			}
			else if(current.startsWith(Constants.KEYWORD_TAIL_RETURN)){
				VariableReplacement vr= new VariableReplacement();
				vr.setFrame(frame);
				vr.setLibrary(library);
				current=current.substring(Constants.KEYWORD_TAIL_RETURN.length()).trim();
				String ecRetTC[]=BlockExtractor.extractChunk(current, Constants.OPEN_PAREN, Constants.CLOSE_PAREN, Constants.QUOTE);
				return vr.executeMethod(current.substring(0, current.indexOf(Constants.OPEN_PAREN)), ecRetTC[0], true);
				
			}
			else{
				Object output=evaluateExpression(current, frame, library);
				if(output!=null){
					return outConvert(output,frame);
				}
					
				
			}
			current=rest;
		
		}while(rest!=null&&rest.trim().length()>0);
		return null;
	}
	private static Object outConvert(Object output,StackFrame frame){
		if(output.toString().startsWith("var:")){
			//TODO: return variable from stack
			if(output.toString().startsWith("var:arrList_")){
				String vname=output.toString().substring("var:arrList_".length());
				return frame.arrLists.get(vname);
			}
			if(output.toString().startsWith("var:dict_")){
				String vname=output.toString().substring("var:dict_".length());
				return frame.dicts.get(vname);
			}
			if(output.toString().startsWith("var:funcList_")){
				String vname=output.toString().substring("var:funcList_".length());
				return frame.funcLists.get(vname);
			}
		}
		return output;
	}
	private static Object evaluateExpression(String sent, StackFrame frame, Library library){
		 VariableReplacement vr= new VariableReplacement();
		 vr.setFrame(frame);
		 vr.setLibrary(library);
		 sent=sent.trim();
		 boolean replace=true;
		 for(String s: Constants.DECLARATION_KEYWORDS){
			 if(sent.startsWith(s)){
				 replace=false;
				 
			 }
		 }
		 if(replace)
			 sent=vr.replacedVars(sent);
		 if(sent==null||sent.length()==0)
			 return null;
		 //ParsingDebug.println("var replaced : "+sent);
		 try{
		 SprintParserLexer lexer = new SprintParserLexer(new ANTLRInputStream(sent));
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		 SprintParserParser parser = new SprintParserParser(tokens);
		 ParseTree tree = parser.statement();
		 SprintVisitorVars visitor = new SprintVisitorVars();
		
		 visitor.setFrame(frame);
	     return visitor.visit(tree);
		 }
		 catch(Exception e){
			 //ParsingDebug.println("exception: "+e.toString());
			 e.printStackTrace();
			 return null;
		 }

	     //ParsingDebug.println("parsed: "+sent);
	     
	}

}
