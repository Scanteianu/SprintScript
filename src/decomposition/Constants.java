package decomposition;

public class Constants {
	//Special Characters
	final static char QUOTE='\"';
	final static char OPEN_PAREN='(';
	final static char CLOSE_PAREN=')';
	final static char OPEN_BRACKET='[';
	final static char CLOSE_BRACKET=']';
	final static char OPEN_BRACE='{';
	final static char CLOSE_BRACE='}';
	final static char END_STATEMENT=';';
	final static char COMMA=',';
	//Keywords
	final static String KEYWORD_IF="if";
	final static String KEYWORD_ELSE="else";
	final static String KEYWORD_WHILE="while";
	final static String KEYWORD_PARALLEL="parallel";
	final static String KEYWORD_METHOD="method";
	final static String KEYWORD_FUNCTION="function";
	final static String KEYWORD_DYNAFUNC="dynafunc";
	final static String KEYWORD_RETURN="return";
	final static String KEYWORD_TAIL_RETURN="tailreturn";
	//data types (and print)
	final static String KEYWORD_PRINT="print";
	final static String KEYWORD_INT="int";
	final static String KEYWORD_FLOAT="float";
	final static String KEYWORD_STRING="string";
	final static String KEYWORD_DICT="dict";
	final static String KEYWORD_ARRAY="array";
	final static String KEYWORD_LINKLIST="linklist";
	//built in java functions
	final static String KEYWORD_LENGTH="len";
	final static String KEYWORD_EQUALS="equals";
	final static String KEYWORD_APPEND="append";
	final static String KEYWORD_ARRAY_REMOVE="arrRemove";
	final static String KEYWORD_PUT="put";
	final static String KEYWORD_GET="get";
	final static String KEYWORD_CONTAINS_KEY="containsKey";
	final static String KEYWORD_DICT_REMOVE="dicRemove";
	public final static String[] DECLARATION_KEYWORDS={KEYWORD_INT, KEYWORD_FLOAT, KEYWORD_STRING,KEYWORD_DICT, KEYWORD_ARRAY, KEYWORD_LINKLIST};
	public final static String[] RESERVED_STATEMENT_KEYWORDS={KEYWORD_PRINT,KEYWORD_INT, KEYWORD_FLOAT, KEYWORD_STRING,KEYWORD_DICT, KEYWORD_ARRAY, KEYWORD_LINKLIST, KEYWORD_RETURN, KEYWORD_TAIL_RETURN };
	public final static String[] CONTROL_FLOW={KEYWORD_IF, KEYWORD_ELSE, KEYWORD_WHILE, KEYWORD_PARALLEL};
	public final static String[] PROGRAM_STRUCTURE={KEYWORD_METHOD, KEYWORD_FUNCTION, KEYWORD_DYNAFUNC};
	
}
