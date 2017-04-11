package parallel;

import datastructure.Library;
import datastructure.StackFrame;
import decomposition.RunMethod;
import parsing.ParsingDebug;

public class InstructionThread extends Thread{
	private Object returnObject=null;
	private String name;
	private String varName;
	private StackFrame frame;
	private Library library;
	
	
	public String getVarName() {
		return varName;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public InstructionThread(String varName, String name, StackFrame frame, Library library) {
		super();
		this.name = name;
		this.frame = frame;
		this.library = library;
		this.varName = varName;
	}


	public void run(){
		//ParsingDebug.println("going:" +varName);
		returnObject=RunMethod.executeMethod(name, frame, library);
		//ParsingDebug.println(varName+": "+returnObject.toString());
		
	}
}
