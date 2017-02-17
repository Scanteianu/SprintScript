package test;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import test.testParser.EquationRuleContext;



public class TestPackageMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		evaluateShit("3+3");
	}
	private static void printShit(String sent) {
	    // Get our lexer
	    testLexer lexer = new testLexer(new ANTLRInputStream(sent));
	 
	    // Get a list of matched tokens
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	 
	    // Pass the tokens to the parser
	    testParser parser = new testParser(tokens);
	 
	    // Specify our entry point
	    testParser.EquationRuleContext testSentenceContext = parser.equationRule();
	 
	    // Walk it and attach our listener
	    ParseTreeWalker walker = new ParseTreeWalker();
	    MyTestListener listener = new MyTestListener();
	    walker.walk(listener, testSentenceContext);
	}
	private static void evaluateShit(String sent){
		 testLexer lexer = new testLexer(new ANTLRInputStream(sent));
		 CommonTokenStream tokens = new CommonTokenStream(lexer);
		 testParser parser = new testParser(tokens);
		 ParseTree tree = parser.statement();
		 testVisitor visitor = new TestVisitorImplementation();
	     visitor.visit(tree);
	}

}
