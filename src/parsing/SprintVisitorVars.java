package parsing;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.NotNull;

import datastructure.StackFrame;

public class SprintVisitorVars extends SprintVisitorNumbers {
	private StackFrame frame;

	public StackFrame getFrame() {
		return frame;
	}

	public void setFrame(StackFrame frame) {
		this.frame = frame;
	}
	/*
	 * Here begins the declarations section. Note: linked lists are not implemented yet.
	 */
	
	 public Object visitDeclFloat(@NotNull SprintParserParser.DeclFloatContext ctx) { 
		 frame.floats.put(ctx.NAME().getText(), 0.0);
		 return null;
	 	
	 }
	 public Object visitDeclInt(@NotNull SprintParserParser.DeclIntContext ctx){
		 frame.integer.put(ctx.NAME().getText(), (long)0);
		 return null;
	 }
	 public Object visitDeclString(@NotNull SprintParserParser.DeclStringContext ctx){
		 
		 frame.strings.put(ctx.NAME().getText(), "");
		 return null;
	 }
	 @Override public Object visitDeclArray(@NotNull SprintParserParser.DeclArrayContext ctx) {
		 frame.arrLists.put(ctx.NAME().getText(), new ArrayList<Object>());
		 return null; 
	 }
	 @Override public Object visitDeclDict(@NotNull SprintParserParser.DeclDictContext ctx){
		 frame.dicts.put(ctx.NAME().getText(), new HashMap<Object,Object>());
		 return null; 
	 }
	 /*
	 * Here begins the primitives assignment section.
	 */
	 @Override public Object visitAssMath(@NotNull SprintParserParser.AssMathContext ctx) { 
		 if(frame.floats.containsKey(ctx.NAME().getText())){
			 frame.floats.put(ctx.NAME().getText(), (Double)visit(ctx.mathLvl4()));
		 }
		 else{
			 frame.integer.put(ctx.NAME().getText(), (Long)visit(ctx.mathLvl4()));
		 }
		 return null;
	 }
	 @Override public Object visitAssString(@NotNull SprintParserParser.AssStringContext ctx) {
		 frame.strings.put(ctx.NAME().getText(), (String) visit(ctx.string()));
		 return null;
	 }
	 /**
	  * doesn't handle linked lists. sorry bruh.
	  */
	 @Override public Object visitAssListElement(@NotNull SprintParserParser.AssListElementContext ctx) { 
		 try{
			 frame.arrLists.get(ctx.NAME().getText()).set((int)((long)(Long)visit(ctx.mathLvl4())),visit(ctx.data()));
		 }
		 catch(IndexOutOfBoundsException ex){
			 frame.arrLists.get(ctx.NAME().getText()).add((int)((long)(Long)visit(ctx.mathLvl4())),visit(ctx.data()));
			 
		 }
		// ParsingDebug.println(ctx.getText());
		 return null;
	 
	 }
	 
}
