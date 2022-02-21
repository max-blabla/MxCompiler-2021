grammar Mx;
@header{package MxParser;}

program
    :   definition*
    |   EOF
    ;


/*comment
    : SingleComment
    | MultiComment
    ;*/


definition
    :   funcDef
    |   varDef Semi*
    |   classDef Semi*
    ;

funcDef
    :   returnType? id LeftParenthes parameterList RightParenthes suite
    ;


classSpace
    :   LeftBrace definition* RightBrace
    ;

classDef
    :   Class id classSpace
    ;

varDef
    :   type varDeclaration (Comma varDeclaration)*
    ;

varDeclaration
    : id (Assign expression)?
    ;

parameterList
    : (varDef (Comma varDef)*)? ;

expressionList
    : (expression (Comma expression)*)?;

suite
    :   LeftBrace statement* RightBrace
    ;

ifStatement
    : If LeftParenthes condition RightParenthes trueStmt = statement (Else falseStmt = statement)?
    ;


forInit
    : (expression|varDef)?
    ;

forStatement
    : For LeftParenthes forInit Semi condition? Semi incr = expression? RightParenthes statement
    ;

whileStatement
    : While LeftParenthes condition RightParenthes statement
    ;

returnStatement
    : Return expression?
    ;

jumpStatement
    : Break
    | Continue
    ;

varStatement
    :  varDef Semi
    ;

exprStatement
    :  expression Semi
    ;



statement
    :   suite  //block
    |   varStatement//varDef
    |   ifStatement
    |   forStatement
    |   whileStatement
    |   returnStatement Semi    //return Stmt
    |   exprStatement //pure exp
    |   jumpStatement Semi
    |   Semi   //empty Stmt
    ;

lambdaExpression
    : LeftBracket And RightBracket (LeftParenthes parameterList RightParenthes)? RightArrow suite LeftParenthes expressionList RightParenthes //lambda
    ;

newErrorArray
    : New singleType (LeftBracket expression RightBracket)*(LeftBracket RightBracket)+(LeftBracket expression RightBracket)+
    ;

newType
    :  New singleType((LeftParenthes RightParenthes) |(LeftBracket expression RightBracket)*(LeftBracket RightBracket)*)//newArrayExpr
    ;


condition
    : expression
    ;

indexExpr
    : LeftBracket expression RightBracket
    ;

expression
    :   primary
    |   lambdaExpression
    |   expression op='.' expression //memExpr
    |   expression  LeftParenthes expressionList RightParenthes// functionExpr ()
    |   expression  indexExpr//indexExpr []
    |   op=('+'|'-') expression // unaryExpr
    |   op=('++'|'--') expression // unaryExpr
    |   op='!' expression // unaryExpr
    |   op='~' expression // unaryExpr
    |   <assoc = right> expression rop=('++'|'--')  //unaryExpr
    |   expression op=('*'|'/'|'%') expression //binaryExpr
    |   expression op=('+'|'-') expression //binaryExpr
    |   expression op=('<<'|'>>') expression //binaryExpr
    |   expression op=('>'|'<'|'<='|'>=') expression //binaryExpr
    |   expression op=('=='|'!=') expression //binaryExpr
    |   expression op='&' expression //binaryExpr
    |   expression op='^' expression //binaryExpr
    |   expression op='|' expression //binaryExpr
    |   expression op='&&' expression //binaryExpr
    |   expression op='||' expression //binaryExpr
    |   <assoc = right> expression op='=' expression //assignExpr
    ;

parenthes
    : LeftParenthes expression RightParenthes
    ;

label
    :  id
    |  This
    ;

primary
    :   parenthes
    |   label
    |   newErrorArray
    |   newType
    |   literal
    ;

literal
    : literalType = (Integer|Numeric|True|False|Null|StringConst)
    ;


returnType
    :   Void
    |   type
    ;



singleType
    : builtinType
    | id
    ;

type
    :   singleType (LeftBracket RightBracket)*
    ;

id
    : Identifier
    ;

builtinType
    :   Int
    |   String
    |   Bool
    ;










//lexer


//注释信道
//先匹配函数与类，再匹配控制
//最后匹配单行语句
//SingleComment : Note .*? ;
Null : 'null';
This : 'this';
True : 'true';
False : 'false';
Class : 'class';
If : 'if';
Else : 'else';
Int : 'int';
Bool : 'bool';
String : 'string';
Void : 'void';


While : 'while';
For : 'for';
Break : 'break';
Continue : 'continue';
Return : 'return';
New : 'new';



Dot : '.';
LeftParenthes: '(';
RightParenthes: ')';
LeftBracket : '[';
RightBracket : ']';
LeftBrace : '{';
RightBrace : '}';





GreaterThan : '>';
LessThan : '<';
GreaterEqual : '>=';
LessEqual : '<=';

NotEqual : '!=';
Equal : '==';
Assign : '=';

SelfPlus : '++';
SelfMinus : '--';
Plus: '+';
Minus : '-';

Divide : '/';
Mod : '%';
Times : '*';

LeftShift : '<<';
RightShift : '>>';

AndAnd : '&&';
OrOr: '||';
Or : '|';
And : '&';
Tidle : '~';
Xor : '^';
Not : '!';

Colon : ':';
Quot : '"';
Comma : ',';
Semi: ';';
Question : '?';
RightArrow : '->';

//0目 单目 双目

BackSlash : '\\\\';
DbQuotation:'\\"';


Identifier  : (UCLTR|LCLTR) (DIGIT|UCLTR|LCLTR|UNDERLINE)*;
StringConst : Quot (BackSlash|DbQuotation|.)*? Quot;
Integer :   ZERO|[1-9] DIGIT*;
Numeric : Integer Dot DIGIT+;



WhiteSpace : [\t ]+->skip;	//skip spaces,tabs,newlines,\r(Windows)
Newline : ('\r''\n'?|'\n')->skip;
BlockComment : '/*'.*?'*/'->skip;
LineComment :'//'~[\r\n]*->skip;
fragment
    DIGIT : [0-9];
    UCLTR : [A-Z];
    ZERO : '0';
    LCLTR : [a-z];
    UNDERLINE : '_';




