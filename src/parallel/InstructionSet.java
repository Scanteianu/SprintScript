package parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import datastructure.LinkedListNode;
import datastructure.StackFrame;
import parsing.ParsingDebug;

public class InstructionSet {
	StackFrame frame;
	LinkedList<InstructionThread> threads = new LinkedList<InstructionThread>();
	public void addInstructionThread(InstructionThread it){
		threads.add(it);
	}
	public StackFrame runAll(StackFrame original){
		frame=original;
		executeThreads();
		updateFrame();
		return frame;//is this overkill or nah??
	}
	private void executeThreads(){
		//ParsingDebug.println("pre run ");
		for(Thread t: threads)
			t.start();
		//ParsingDebug.println("run commenced");
		for(Thread t: threads)
			try {
				t.join();
			} catch (InterruptedException e) {
				ParsingDebug.println("Thread Failure in Parallel!!!");
				e.printStackTrace();
			}
		//ParsingDebug.println("run done");
	}
	private void updateFrame(){
		for(InstructionThread t:threads){
			if(t.getReturnObject() instanceof Long){
				frame.integer.put(t.getVarName(), (Long) t.getReturnObject());
			}
			if(t.getReturnObject() instanceof Double){
				frame.floats.put(t.getName(), (Double) t.getReturnObject());
			}
			if(t.getReturnObject() instanceof String){
				frame.strings.put(t.getVarName(), (String) t.getReturnObject());
			}
			if(t.getReturnObject() instanceof ArrayList<?>){
				frame.arrLists.put(t.getVarName(), (ArrayList<Object>) t.getReturnObject());
			}
			if(t.getReturnObject() instanceof HashMap<?,?>){
				frame.dicts.put(t.getVarName(), (HashMap<Object,Object>) t.getReturnObject());
			}
			if(t.getReturnObject() instanceof LinkedListNode){
				frame.funcLists.put(t.getVarName(), (LinkedListNode)t.getReturnObject());
			}
		}
	}

}
