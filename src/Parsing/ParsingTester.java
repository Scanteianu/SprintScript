package Parsing;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import test.TestVisitorImplementation;
import test.testLexer;
import test.testParser;
import test.testVisitor;

public class ParsingTester {

	public static void main(String[] args) {
		evaluateExpression("print(3.0/2 +!3/2)");

	}
	private static void evaluateExpression(String sent){
		 SprintParserLexer lexer = new SprintParserLexer(new ANTLRInputStream(sent));
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		 SprintParserParser parser = new SprintParserParser(tokens);
		 ParseTree tree = parser.print();
		 SprintVisitor visitor = new SprintVisitor();
	     visitor.visit(tree);
	}
}
