grammar SprintUnpack;

assignLine: NAME '=' expression;
expression: expressionComponent*;
expressionComponent: NAME* sign* STRING*;
sign:	'+' | '-' | '*' | '/' | '^'|
		'=='| '<' | '>' | '<='|	'>='|
		'(' | ')' | '[' | ']'
		;
number: INT | FLOAT;

//todo: list handling, object/dictionary handling
INT: '-'? [0-9]+;
FLOAT: '-'? [0-9]* '.' [0-9]+;
NAME: [a-zA-Z] [a-zA-Z_0-9]*;
STRING: '"' .*? '"';
WS : [ \t\r\n]+ -> skip ;//skip whitespace
