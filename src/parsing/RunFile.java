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
		String wholeFile="";
		try {
			Scanner stdin = new Scanner(System.in);
			System.out.print("please enter program name: ");
			Scanner sc = new Scanner(new File(stdin.nextLine()));
			while(sc.hasNextLine()){
				wholeFile+=sc.nextLine()+"\n";
			}
			Library library = ProgramDecomposer.decomposeProgram(wholeFile);
			long start = System.currentTimeMillis();
			System.out.println("Main exit value: "+RunMethod.executeMethod("main", new StackFrame(), library));
			//read and run main.
			System.out.println((System.currentTimeMillis()-start)/1000.0 +" runtime, milliseconds");
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ParsingDebug.println("done.");
		//ParsingDebug.println(frame.arrLists.get("c").get(0));

	}
	
}
