package decomposition;

import datastructure.FunctionBlock;
import datastructure.Library;

public class ProgramDecomposer {
	// read input file(s), place in function blocks or function with DP blocks
	//call call function main
	//that's it.
	public static Library decomposeProgram(String s){
		Library library = new Library();
		boolean done = false;
		while(!done){
			while(s.length()>0 && Character.isWhitespace(s.charAt(0)))
				s=s.substring(1);
			if(s.startsWith(Constants.KEYWORD_FUNCTION)){
				s=s.substring(Constants.KEYWORD_METHOD.length());
				while(Character.isWhitespace(s.charAt(0)))
					s=s.substring(1);
				String[] methodAndFriends=BlockExtractor.extractBlock(s);
				FunctionBlock b = new FunctionBlock();
				b.setName(methodAndFriends[0]);
				b.setArgs(BlockExtractor.separateArgs(methodAndFriends[1]));
				b.setBody(methodAndFriends[2]);
				s=methodAndFriends[3];
				library.function.put(b.getName(), b);
				DecompDebug.println("Function");
				DecompDebug.println(b.toString());
			}
			else if(s.startsWith(Constants.KEYWORD_DYNAFUNC))			{
				//TODO
			}
			else if(s.startsWith(Constants.KEYWORD_METHOD)){
				s=s.substring(Constants.KEYWORD_METHOD.length());
				while(Character.isWhitespace(s.charAt(0)))
					s=s.substring(1);
				String[] methodAndFriends=BlockExtractor.extractBlock(s);
				FunctionBlock b = new FunctionBlock();
				b.setName(methodAndFriends[0]);
				b.setArgs(BlockExtractor.separateArgs(methodAndFriends[1]));
				b.setBody(methodAndFriends[2]);
				s=methodAndFriends[3];
				library.methods.put(b.getName(), b);
				DecompDebug.println("Method");
				DecompDebug.println(b.toString());
				
				
			}
			else{
				done=true;
			}
		}
		
		return library;
	}

}
