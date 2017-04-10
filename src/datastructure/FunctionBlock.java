package datastructure;

import java.util.LinkedList;
import java.util.List;

public class FunctionBlock {
	private String name;
	private List<String> args = new LinkedList<String>();
	private String body="";
	private boolean isTabled=false;
	
	public FunctionBlock(String name, List<String> args, String body, boolean isTabled) {
		super();
		this.name = name;
		this.args = args;
		this.body = body;
		this.isTabled = isTabled;
	}
	public boolean isTabled() {
		return isTabled;
	}
	public void setTabled(boolean isTabled) {
		this.isTabled = isTabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getArgs() {
		return args;
	}
	public void setArgs(List<String> args) {
		this.args = args;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public FunctionBlock(String name, List<String> args, String body) {
		this.name = name;
		this.args = args;
		this.body = body;
	}
	public FunctionBlock() {
	}
	public String toString(){
		String s="";
		s+="Function Block\n\nName:"+this.name+"\n\nArgs:";
		for(String arg: args)
			s+=arg+",";
		s+="\n\nBody:"+this.body+"\n\n";
		return s;
		
	}
	
}
