package datastructure;

import java.util.ArrayList;
import java.util.HashMap;

public class StackFrame {
	public HashMap<String, Long> integer=new HashMap<>();
	public HashMap<String, Double> floats=new HashMap<>();
	public HashMap<String, String> strings=new HashMap<>();
	public HashMap<String, LinkedListNode> funcLists=new HashMap<>();
	public HashMap<String, ArrayList<Object>> arrLists=new HashMap<>();
	public HashMap<String, HashMap<Object,Object>> dicts=new HashMap<>();
}