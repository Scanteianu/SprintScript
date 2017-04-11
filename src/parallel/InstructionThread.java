package parallel;

import datastructure.Library;
import datastructure.StackFrame;
import decomposition.RunMethod;

public class InstructionThread extends Thread{
	private Object returnObject=null;
	private String name;
	private StackFrame frame;
	private Library library;
	
	
	public Object getReturnObject() {
		return returnObject;
	}

	public InstructionThread(String name, StackFrame frame, Library library) {
		super();
		this.name = name;
		this.frame = frame;
		this.library = library;
	}


	public void run(){
		returnObject=RunMethod.executeMethod(name, frame, library);
		
	}
}
