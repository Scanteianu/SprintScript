package parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ParsingTester {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("input.txt"));
			while(sc.hasNextLine()){
				evaluateExpression(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	private static void evaluateExpression(String sent){
		 SprintParserLexer lexer = new SprintParserLexer(new ANTLRInputStream(sent));
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		 SprintParserParser parser = new SprintParserParser(tokens);
		 ParseTree tree = parser.print();
		 SprintVisitorNumbers visitor = new SprintVisitorNumbers();
	     visitor.visit(tree);
	}
}
