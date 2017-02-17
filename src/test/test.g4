grammar test;

statement: equationRule;
equationRule: equationRule PLUS equationRule	#Plus
	| equationRule TIMES equationRule			#Times
	| NUM 					#Init
	;
NUM: [0-9]+ ;
PLUS: '+';
TIMES: '*';
EQUAL: '=' ;

WS : [ \t\r\n]+ -> skip ;//skip whitespace
