package parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import datastructure.Library;
import datastructure.StackFrame;
import decomposition.ProgramDecomposer;
import decomposition.RunMethod;

public class RunFile {
	public static void main(String[] args) {
		StackFrame frame = new StackFrame();
		String wholeFile="";
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			while(sc.hasNextLine()){
				wholeFile+=sc.nextLine()+"\n";
			}
			Library library = ProgramDecomposer.decomposeProgram(wholeFile);
			
			System.out.println("Main exit value: "+RunMethod.executeMethod("main", new StackFrame(), library));
			//read and run main.
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ParsingDebug.println("done.");
		//ParsingDebug.println(frame.arrLists.get("c").get(0));

	}
	
}
