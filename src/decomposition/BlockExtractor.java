package decomposition;

import java.util.LinkedList;
import java.util.List;

public class BlockExtractor {
	/**
	 * Takes code, and extracts the first block of code.
	 * Uses constants for parens, braces.
	 * @param s
	 * @return index 0 is the name, index 1 is the args/condition, index 2 is the body, index 3 is the rest of the code. 
	 */
	public static String[] extractBlock(String s){
		String[] returns = new String[4];
		returns[0]=s.substring(0, s.indexOf(Constants.OPEN_PAREN));
		String[] condReturn=extractChunk(s, Constants.OPEN_PAREN, Constants.CLOSE_PAREN, Constants.QUOTE);
		returns[1]=condReturn[0];
		s=condReturn[1];
		String[] bodyReturn = extractChunk(s, Constants.OPEN_BRACE, Constants.CLOSE_BRACE, Constants.QUOTE);
		returns[2]=bodyReturn[0];
		returns[3]=bodyReturn[1];
		return returns;
	}
	public static List<String> separateArgs(String s){
		if(s.length()==0)
			return new LinkedList<>();
		while(s.length()>0 && Character.isWhitespace(s.charAt(0)))
			s=s.substring(1);
		if(s.length()==0)
			return new LinkedList<>();
		LinkedList<String> l = new LinkedList<>();
		String[] response=new String[2];
		while(response!=null){
			while(Character.isWhitespace(s.charAt(0)))
				s=s.substring(1);
			response=extractComponent(s,Constants.COMMA, Constants.OPEN_PAREN, Constants.CLOSE_PAREN,Constants.QUOTE);
			if(response==null){
				//DecompDebug.println(s);
				
				l.add(s);
				response=null;
			}
			else{
				l.add(response[0]);
				s=response[1];
			}
				
			
		}
		return l;
		
		
		
	}
	/**
	 * Used to extract a specified chunk (parentheses surrounded, bracket surrounded, brace surrounded, etc. 
	 * Ignores characters inside strings;
	 * Current flaws: can't escape quotes in strings. Single quotes, however, are ok.
	 * @param s
	 * @param startChar
	 * @param endChar
	 * @param quoteChar
	 * @return
	 */
	public static String[] extractChunk(String s, char startChar, char endChar, char quoteChar){
		boolean isQuoted=false;
		int parenCount=1;
		String[] returns=new String[2]; //first one for the block, second one for everything else.
		if(!s.contains(startChar+""))
			return null;
		s=s.substring(s.indexOf(startChar)+1);
		int i =0;
		while(parenCount>0){
			if(s.charAt(i)==quoteChar)
				isQuoted=!isQuoted;
			if(!isQuoted && s.charAt(i)==startChar)
				parenCount++;
			if(!isQuoted && s.charAt(i)==endChar)
				parenCount--;
			i++;
		}
		returns[0]=s.substring(0, i-1);
		returns[1]=s.substring(i);
		return returns;		
	}
	/**
	 * this allows a statement, or argument to a function to be extracted, regardless of internal function calls
	 * for parsing the structure of a program, substring should be used, as there shouldn't be quotes before method bodies.
	 * if closing parens are desired, a hack can be to use newline as closing character. 
	 * Index 0 = the component
	 * Index 1 = the rest of the input string
	 * Note: divider character (end char) is ommitted
	 * @param s
	 * @param endChar
	 * @param parenOpen
	 * @param parenClose
	 * @param quoteChar
	 * @return
	 */
	public static String [] extractComponent(String s, char endChar, char parenOpen, char parenClose, char quoteChar){
		String [] returns =new String[2];
		
		boolean finished=false;
		int parenCounter=0;
		boolean isQuotes=false;
		int i=0;
		while(!finished){
			if(i>=s.length())
				return null;
			if(s.charAt(i)==quoteChar)
				isQuotes=!isQuotes;
			if(s.charAt(i)==parenOpen&&!isQuotes)
				parenCounter++;
			if(s.charAt(i)==parenClose&&!isQuotes)
				parenCounter--;
			
			if(s.charAt(i)==endChar && parenCounter==0&&!isQuotes)
				finished=true;
			if(!finished)
				i++;
		}
		returns[1]=null;
		returns[0]=s.substring(0, i);
		if(i+1<s.length())
			returns[1]=s.substring(i+1);
		return returns;
	}
}
