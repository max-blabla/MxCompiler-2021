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
    :   returnType Identifier LeftParenthes parameterList RightParenthes suite
    ;

conFuncDef
    : Identifier LeftParenthes parameterList RightParenthes suite
    ;

memberFuncDef
    :   funcDef
    ;

memberVarDef
    : varDef
    ;

memberDefinition
    :   conFuncDef
    |   memberFuncDef
    |   memberVarDef Semi*
    ;

classSpace
    :   LeftBrace memberDefinition* RightBrace
    ;

classDef
    :   Class Identifier classSpace
    ;

varDef
    :   type varDeclaration (Comma varDeclaration)*
    ;

varDeclaration
    : Identifier (Assign expression)?
    ;

parameterList
    :   (type Identifier (Comma type Identifier)*)? ;

expressionList
    : (expression (Comma expression)*)?;

suite
    :   LeftBrace statement* RightBrace
    ;

ifStatement
    : If LeftParenthes condition RightParenthes trueStmt = statement
    ;

elseStatement
    : Else falseStmt = statement
    ;

ifElseStatement
    :  ifStatement//if Stmt
       elseStatement?
    ;


forStatement
    : For LeftParenthes (expStatement|varDef)? Semi condition? Semi expStatement? RightParenthes statement
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

expStatement
    : expression
    ;


statement
    :   suite  //block
    |   varDef Semi//varDef
    |   ifElseStatement
    |   forStatement
    |   whileStatement
    |   returnStatement Semi    //return Stmt
    |   expStatement Semi //pure exp
    |   jumpStatement Semi
    |   Semi   //empty Stmt
    ;

lambdaExpression
    : LeftBracket And RightBracket LeftParenthes parameterList RightParenthes RightArrow suite //lambda
    ;

newSingle
    :  New singleType (LeftParenthes RightParenthes)? //new Expr
    ;

newErrorArray
    : New singleType (LeftBracket expression RightBracket)+(LeftBracket RightBracket)+(LeftBracket expression RightBracket)+
    ;

newArrayExp
    : (LeftBracket expression RightBracket)+(LeftBracket RightBracket)*
    ;

newArray
    :  New singleType newArrayExp//newArrayExpr
    ;

indexExpr
    : (LeftBracket expression RightBracket)+
    ;

funcExpr
    :  LeftParenthes expressionList RightParenthes
    ;

condition
    : expression
    ;

leftSelfExprssion
    : op=('+'|'-') expression // unaryExpr
    | op=('++'|'--') expression
    | op='!' expression
    | op='~' expression
    ;

expression
    :   primary
    |   lambdaExpression
    |   expression  indexExpr//indexExpr []
    |   expression  funcExpr// functionExpr ()
    |   expression op='.' expression //memExpr
    |   leftSelfExprssion
    |   <assoc = right> expression op=('++'|'--')  //unaryExpr
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
    :  Identifier
    |  This
    ;

primary
    :   parenthes
    |   label
    |  newErrorArray
    |   newArray
    |   newSingle
    |   literal
    ;

literal
    : literalType = (Integer|StringConst|Numeric|True| False|Null)
    ;


returnType
    :   Void
    |   type
    ;

singleType
    : builtinType
    | Identifier
    ;

type
    :   singleType (LeftBracket RightBracket)*
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
Newline : ('\r''\n'?|'\n')->skip;
BlockComment : '/*'.*?'*/'->skip;
LineComment :'//'~[\r\n]*->skip;
fragment
    DIGIT : [0-9];
    UCLTR : [A-Z];
    ZERO : '0';
    LCLTR : [a-z];
    UNDERLINE : '_';




