grammar Mx;

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
    |   varDef Semi
    |   classDef Semi
    ;

funcDef
    :   returnType Identifier LeftParenthes parameterList RightParenthes suite
    ;

memberDef
    :   Identifier LeftParenthes parameterList RightParenthes suite
    |   definition
    ;

classSpace
    :   LeftBrace memberDef* RightBrace
    ;

classDef
    :   Class Identifier classSpace
    ;

varDef
    :   type varDeclaration (Comma varDeclaration)*
    ;

varDeclaration
    : Identifier (Assign expression)?
    | (LeftBracket RightBracket)+ Identifier (Assign expression)?
    ;

parameterList
    :   (type Identifier (Comma type Identifier)*)? ;

expressionList
    : expression (Comma expression)*;

suite
    :   LeftBrace statement* RightBrace
    ;

statement
    :   suite  //block
    |   varDef Semi//varDef
    |   If LeftParenthes expression RightParenthes trueStmt = statement //if Stmt
        (Else falseStmt = statement)?
    |   For LeftParenthes (expression|varDef)? Semi expression? Semi expression? RightParenthes statement //for Stmt
    |   While LeftParenthes expression RightParenthes statement
    |   Return expression? Semi    //return Stmt
    |   expression Semi //pure Stmt
    |   Semi   //empty Stmt
    ;

expression
    :   primary
    |   LeftBracket And RightBracket LeftParenthes //lambda
    |   New type (LeftParenthes RightParenthes)? //new Expr
    |   New type (LeftBracket expression RightBracket)(LeftBracket expression? RightBracket)* //newArrayExpr
    |   expression LeftBracket expression RightBracket //indexExpr []
    |   expression LeftParenthes expressionList? RightParenthes // functionExpr ()
    |   expression op='.' expression //memExpr
    |   op=('+'|'-') expression // unaryExpr
    |   op=('++'|'--') expression //unaryExpr
    |   <assoc = right> expression op=('++'|'--')  //unaryExpr
    |   op='!' expression //unaryExpr
    |   op='~' expression //unaryExpr
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

primary
    :   LeftParenthes expression RightParenthes
    |   Identifier
    |   Break
    |   Continue
    |   This
    |   literal
    ;

literal
    :   Integer
    |   StringConst
    |   Numeric
    |   True
    |   False
    |   Null
    ;

returnType
    :   Void
    |   type
    ;

type
    :   builtinType (LeftBracket RightBracket)*
    |   Identifier (LeftBracket RightBracket)*
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
Integer : ZERO|[1-9] DIGIT*;
Numeric : Integer Dot DIGIT+;



WhiteSpace : [\t ]+->skip;	//skip spaces,tabs,newlines,\r(Windows)
Newline : ('\r''\n'?|'\n'){System.out.println("match newline");}->skip;
BlockComment : '/*'.*?'*/'->skip;
LineComment :'//'~[\r\n]*->skip;
fragment
    DIGIT : [0-9];
    UCLTR : [A-Z];
    ZERO : '0';
    LCLTR : [a-z];
    UNDERLINE : '_';




