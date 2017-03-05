grammar SprintParser;

statement:	print | assign | declare ;
print: 		'print(' data ')'		#PrintData
	;

data:		mathLvl4				#MathToData
	|		string					#StringToData
	|		NAME					#NameToData
	|		arrList					#ArrToData
	;

mathLvl1:	mathLvl1 '^' mathLvl1	#Exponent
	|		'(' mathLvl4 ')'		#ParenPromote
	|		'int(' mathLvl4 ')'		#ConvInt
	|		'float(' mathLvl4 ')'	#ConvFloat
	|		INT						#IntOrigin
	|		FLOAT					#FloatOrigin
	;

mathLvl2:	mathLvl2 '*' mathLvl2	#Mult
	|		mathLvl2 '/' mathLvl2	#Div
	|		mathLvl2 '%' mathLvl2	#Mod
	|		mathLvl1				#Lvl1toLvl2
	;

mathLvl3:	mathLvl3 '+' mathLvl3	#Plus
	|		mathLvl3 '-' mathLvl3	#Minus
	|		'!' mathLvl3			#Not
	|		mathLvl3 '==' mathLvl3	#LogicEqual
	|		mathLvl3 '<' mathLvl3	#Less
	|		mathLvl3 '<=' mathLvl3	#Leq
	|		mathLvl3 '>' mathLvl3	#Greater
	|		mathLvl3 '>=' mathLvl3	#Geq
	|		mathLvl2				#Lvl2toLvl3
	;

mathLvl4:	mathLvl4 '&&' mathLvl4	#And
	|		mathLvl4 '||' mathLvl4	#Or
	|		mathLvl3				#Lvl3toLvl4
	;

string: STRING				#StringOrigin
	|	string '+' string	#StringCat
	;


//how to handle Dictionary? -- dictionary only has methods
assign: NAME '=' mathLvl4	#AssMath
	|	NAME '=' string		#AssString
	|	NAME '=' arrList	#AssArr
	|	NAME '[' mathLvl4']' '=' data #AssListElement
	;



declare:	'int' NAME		#DeclInt
		|	'float' NAME	#DeclFloat
		|	'linklist' NAME	#DeclLink
		|	'array' NAME	#DeclArray
		|	'dict' NAME		#DeclDict
		|	'string' NAME	#DeclString
		;

listItem: data ',';
arrList: 'arr[' listItem* ']';

//todo: list handling, object/dictionary handling
INT: '-'? [0-9]+;
FLOAT: '-'? [0-9]* '.' [0-9]+;
NAME: [a-zA-Z] [a-zA-Z_0-9]*;
STRING: '"' .*? '"';
WS : [ \t\r\n]+ -> skip ;//skip whitespace
