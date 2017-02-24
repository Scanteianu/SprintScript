package decomposition;

public class Constants {
	final static char QUOTE='\"';
	final static char OPEN_PAREN='(';
	final static char CLOSE_PAREN=')';
	final static char OPEN_BRACKET='[';
	final static char CLOSE_BRACKET=']';
	final static char OPEN_BRACE='{';
	final static char CLOSE_BRACE='}';
	final static char END_STATEMENT=';';
	final static char COMMA=',';
	
	final static String KEYWORD_IF="if";
	final static String KEYWORD_ELSE="else";
	final static String KEYWORD_WHILE="while";
	final static String KEYWORD_PARALLEL="parallel";
	final static String KEYWORD_METHOD="method";
	final static String KEYWORD_FUNCTION="function";
	final static String KEYWORD_DYNAFUNC="dynafunc";
	final static String KEYWORD_RETURN="return";
	final static String KEYWORD_TAIL_RETURN="tailreturn";
	
	final static String[] CONTROL_FLOW={KEYWORD_IF, KEYWORD_ELSE, KEYWORD_WHILE, KEYWORD_PARALLEL};
	final static String[] PROGRAM_STRUCTURE={KEYWORD_METHOD, KEYWORD_FUNCTION, KEYWORD_DYNAFUNC};
	
}