package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class StackFrame {
	public HashMap<String, Long> integer=new HashMap<>();
	public HashMap<String, Double> floats=new HashMap<>();
	public HashMap<String, String> strings=new HashMap<>();
	public HashMap<String, LinkedListNode> funcLists=new HashMap<>();
	public HashMap<String, ArrayList<Object>> arrLists=new HashMap<>();
	public HashMap<String, HashMap<Object,Object>> dicts=new HashMap<>();
	public boolean equals(Object o){
		if(! (o instanceof StackFrame))
			return false;
		StackFrame other=(StackFrame) o;
		if(!integer.equals(other.integer))
			return false;
		if(!floats.equals(other.floats))
			return false;
		if(!strings.equals(other.strings))
			return false;
		if(!funcLists.equals(other.funcLists))
			return false;
		if(!arrLists.equals(other.arrLists))
			return false;
		if(!dicts.equals(other.dicts))
			return false;
		return true;
	}
	public StackFrame clone(){
		StackFrame frame=new StackFrame();
		frame.integer=(HashMap<String, Long>) integer.clone();
		frame.arrLists=(HashMap<String, ArrayList<Object>>) arrLists.clone();
		frame.dicts=(HashMap<String, HashMap<Object, Object>>) dicts.clone();
		frame.funcLists=(HashMap<String, LinkedListNode>) funcLists.clone();
		frame.strings=(HashMap<String, String>) strings.clone();
		frame.floats=(HashMap<String, Double>) floats.clone();
		return frame;
	}
	public int hashCode(){
		return Objects.hash(integer,floats,strings,dicts,arrLists,funcLists);
	}
}