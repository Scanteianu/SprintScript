package decomposition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import datastructure.Library;
import datastructure.LinkedListNode;
import datastructure.StackFrame;
import datastructure.TailCall;

public class VariableReplacement {
	private StackFrame frame;
	private Library library;
	/**
	 * replaced primitives, dereferenced lists
	 * returns null if nothing is left to do (ie: a reference copy)
	 * if the user decides suddenly and violently to try to use multiple variables with the same name
	 * or name a variable the same as a function probably (although maybe not)
	 * the program will violently vomit on them, I think
	 * or maybe not (because of the brilliant lookahead system)
	 * whoever invented lookahead was a genius.
	 * @param original
	 * @return
	 */
	public String replacedVars(String original){
		boolean isQuoted=false;
		boolean eliminatedAssign=false;
		int currentVarStart=-1;
		int currentVarEnd=-1;
		boolean prefixIsJustAssign=false;
		String output="";
		for(int i =0; i<original.length(); i++){
			if(original.charAt(i)==Constants.QUOTE){
				isQuoted=!isQuoted;
			}
				
			if(!isQuoted){
				if(Character.isWhitespace(original.charAt(i))){
					output+=original.charAt(i);
				}
				else if(Character.isAlphabetic(original.charAt(i))){
					currentVarStart=i;
					int j=i;
					while(j<original.length()&&(Character.isAlphabetic(original.charAt(j))||Character.isDigit(original.charAt(j))||original.charAt(j)=='_'))
						j++;
					currentVarEnd=j;
					i=j-1;
					String currentVarName=original.substring(currentVarStart,currentVarEnd);
					//DecompDebug.println("variable found: "+currentVarName);
					for(String keyword: Constants.RESERVED_STATEMENT_KEYWORDS){
						if(currentVarName.equals(keyword)){
							output+=currentVarName;
							original=original.substring(currentVarEnd);
							i=-1;
							currentVarName=null;
							break;
						}
					}
					//end of the line (if it's a ref changing hands, this is where it is).
					//input lines should be trimmed, just to ensure this works (also for if/else/while/parallel block)
					if(currentVarName==null){
						
					}
					else if(j==original.length()){
						if(prefixIsJustAssign){
							output=output.trim();
							String keyName=output.substring(0, output.length()-2);
							if(frame.dicts.containsKey(currentVarName)){
								frame.dicts.put(keyName, frame.dicts.get(currentVarName));
								return null;
							}
							else if(frame.arrLists.containsKey(currentVarName)){
								frame.arrLists.put(keyName, frame.arrLists.get(currentVarName));
								return null;
							}
							else if(frame.funcLists.containsKey(currentVarName)){
								//TODO: do this shit.
							}
							else{
								output+=this.derefPrimitive(currentVarName);
								return output;
							}
								
						}
						else{
							output+=this.derefPrimitive(currentVarName);
							return output;
						}
						
						
					}
					else{
						char lookahead=original.charAt(j);
						if(lookahead==Constants.OPEN_BRACKET){//take care of a list deref.
							//as of current hack, we're not allowing the list index to contain other list elements, so... eat it...
							
							String[] beReturns=BlockExtractor.extractChunk(original.substring(j), Constants.OPEN_BRACKET, Constants.CLOSE_BRACKET, '\"');
							//DecompDebug.println(currentVarName+"["+beReturns[0]+"]");
							if(beReturns[1].startsWith("=") && !beReturns[1].startsWith("==")&&!eliminatedAssign){
								try{
									Integer.parseInt(beReturns[0].trim());
								}
								catch(NumberFormatException e){
									beReturns[0]=this.derefPrimitive(beReturns[0].trim());
								}
								output+=original.substring(0, j)+Constants.OPEN_BRACKET+beReturns[0]+Constants.CLOSE_BRACKET;
								
								prefixIsJustAssign=true;
								eliminatedAssign=true;
								original=beReturns[1];
								
								i=-1;
							}
							else{
								Object ldReturn=this.derefList(currentVarName, beReturns[0]);
								//DecompDebug.println(ldReturn);
								if(ldReturn instanceof String)
								{
									output+=(String)ldReturn;
									original=beReturns[1];
									i=-1;
								}
								//begin figuring out what to do to assign ref to list element
								else{
									if(prefixIsJustAssign){
										output=output.trim();
										String keyName=output.substring(0, output.length()-2);
										if(frame.dicts.containsKey(keyName)){
											frame.dicts.put(keyName, (HashMap<Object, Object>) ldReturn);
											return null;
										}
										else if(frame.arrLists.containsKey(currentVarName)){
											frame.arrLists.put(keyName, (ArrayList<Object>) ldReturn);
											return null;
										}
										else if(frame.funcLists.containsKey(currentVarName)){
											//TODO: do this shit.
										}
										
											
									}
								}
								//end figuring out what to do to assign ref to list element
								
							}
							
						}
						else if(lookahead==Constants.OPEN_PAREN){
							//take care of a function call here.
							//make sure to handle built in functions once they start to exist.
							//if, while, else, and parallel may be caught here, but they shouldn't
							//because they always start instruction (so if we trim the instruction, and starts with...)
							//print should be handled in a similar way.
							String[] beReturns=BlockExtractor.extractChunk(original.substring(j), Constants.OPEN_PAREN, Constants.CLOSE_PAREN, '\"');
							Object methReturn=executeMethod(currentVarName, beReturns[0]);
							//TODO: figure out what happens if object is returned.
							
							if(prefixIsJustAssign){
								output=output.trim();
								String keyName=output.substring(0, output.length()-2);
								if(frame.dicts.containsKey(keyName)){
									frame.dicts.put(keyName, (HashMap<Object, Object>) methReturn);
									return null;
								}
								else if(frame.arrLists.containsKey(currentVarName)){
									frame.arrLists.put(keyName, (ArrayList<Object>) methReturn);
									return null;
								}
								else if(frame.funcLists.containsKey(currentVarName)){
									//TODO: do this shit as well.
								}
								
									
							}
							
							
							if(methReturn!=null)
								output+=methReturn.toString();
							original=beReturns[1];
							i=-1;
							//DecompDebug.println("somepont: "+output+original);
							
						}
						else if(lookahead=='=' &&!eliminatedAssign&&original.charAt(j+1)!='='){
							//take care of the original assign case. 
							
							output+=original.substring(0, j+1);
							original=original.substring(j+1);
							i=-1;
							prefixIsJustAssign=true;
							eliminatedAssign=true;
						}
						else{
							output+=derefPrimitive(currentVarName);
							original=original.substring(currentVarEnd);
							i=-1;
						}
					}
					
				}
				//not a variable character
				else{
					output+=original.charAt(i);
					prefixIsJustAssign=false;
					
				}
			}
			else{
				output+=original.charAt(i);
				
				
			}
			
		}
		//DecompDebug.println(output);
		
		return output;
	}
	/**
	 * dereferences a primitive. if the variable passed is not a primitive, returns null.
	 * @param variable
	 * @return
	 */
	public String derefPrimitive(String variable){
		if(frame.strings.containsKey(variable)){
			return "\""+ frame.strings.get(variable)+"\"";
		}
		if(frame.floats.containsKey(variable)){
			return frame.floats.get(variable)+"";
		}
		if(frame.integer.containsKey(variable)){
			return frame.integer.get(variable)+"";
		}
		if(frame.integer.containsKey(variable)){
			return frame.integer.get(variable)+"";
		}
		if(frame.arrLists.containsKey(variable)){
			return "arrList_"+variable;
		}
		if(frame.dicts.containsKey(variable)){
			return "dict_"+variable;
		}
		if(frame.funcLists.containsKey(variable)){
			return "funcList_"+variable;
		}
		return null;
	}
	//for execute function, if the return type is of stack frame, assume recursive return. 
	//this method should return a string for any primitive type. Otherwise, return an object, and handle as such.
	public Object executeMethod(String name, String args){
		return executeMethod(name, args,false);
	}
	public Object executeMethod(String name, String args, boolean isTail){
		StackFrame newFrame=new StackFrame();
		List<String> origArgList=library.methods.get(name).getArgs();
		List<String> passedArgs=BlockExtractor.separateArgs(args);
		//TODO: args dereferenced or object moved
		for(int i=0; i<origArgList.size();i++){
			
			String s=origArgList.get(i);
			RunMethod.executeBlock(s+Constants.END_STATEMENT, newFrame, library);
			s=s.substring(s.indexOf(' '));
			s=s.trim();
			String arg=passedArgs.get(i).trim();
			//do return work here
			String argExecInput="return "+arg+Constants.END_STATEMENT;
			Object argExecOutput=RunMethod.executeBlock(argExecInput, frame, library);
			if(argExecOutput!=null && argExecOutput instanceof String){
				s+='='+argExecOutput.toString();
				RunMethod.executeBlock(s+Constants.END_STATEMENT, newFrame, library);
			}
			else if(argExecOutput!=null && argExecOutput instanceof Long){
				s+='='+argExecOutput.toString();
				RunMethod.executeBlock(s+Constants.END_STATEMENT, newFrame, library);
			}
			else if(argExecOutput!=null && argExecOutput instanceof Double){
				s+='='+argExecOutput.toString();
				RunMethod.executeBlock(s+Constants.END_STATEMENT, newFrame, library);
			}
			else{
				if(frame.arrLists.containsKey(s)){
					newFrame.arrLists.put(s, (ArrayList<Object>)argExecOutput);
				}
				else if(frame.dicts.containsKey(arg)){
					newFrame.dicts.put(s, (HashMap<Object,Object>)argExecOutput);
				}
				else if(frame.funcLists.containsKey(arg)){
					newFrame.funcLists.put(s, (LinkedListNode)argExecOutput);
				}
				else{
					s+='='+arg;
					DecompDebug.println(name+s+argExecOutput);
					RunMethod.executeBlock(s+Constants.END_STATEMENT, newFrame, library);
				}
			}
			
			
		}
		if(isTail){
			TailCall call=new TailCall();
			call.frame=newFrame;
			call.methName=name;
			return call;
		}
		Object temp=RunMethod.executeMethod(name, newFrame, library);
		
		if(temp instanceof String)
			return "\""+temp+"\"";
		if(temp instanceof Long)
			return temp+"";
		if(temp instanceof Double)
			return temp+"";
		return temp;
	}
	/**
	 * returns a string or an object of another kind. If it's not a string, handle as object dereference.
	 * rn only handles an int literal inside brackets or a raw variable name pointing to an int.
	 * doing more seems like a pita for no reason, they can figure their shit out and store the index in an int
	 * on the line before. 
	 * because eat it, user, that's why.
	 * @param name
	 * @param brackets
	 * @return
	 */
	public Object derefList(String name, String brackets){
		//TODO: parse equation inside brackets if it's not a primitive var or integer.
		int i=0;
		try{
			Integer.parseInt(brackets);
		}
		catch(NumberFormatException e){
			try{
				i=(int)(long)frame.integer.get(brackets.trim());
			}
			catch(Exception e1){
				System.err.println("error dereferencing list: "+name+" at index: "+brackets+"\n pls make sure "
						+ "you have either entered an integer or a variable name. Math is currently"
						+ "not performed in brackets. sry bruh.");
			}
		}
		Object temp=frame.arrLists.get(name).get(i);
		if(temp instanceof String)
			return "\""+temp+"\"";
		if(temp instanceof Long)
			return temp+"";
		if(temp instanceof Double)
			return temp+"";
		return temp;
	}
	public StackFrame getFrame() {
		return frame;
	}
	public void setFrame(StackFrame frame) {
		this.frame = frame;
	}
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
}
