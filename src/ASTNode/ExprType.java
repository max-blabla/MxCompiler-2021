package ASTNode;

public enum ExprType{
        Primary,
        FuncCall, //D
        ExprList, // D
        Lambda,
        MemCall,
        Index,
        Positive,
        Negative,
        LeftSelfPlus,
        RightSelfPlus,
        Not,
        Tidle,
        LeftSelfMinus,
        RightSelfMinus,
        Plus, // D
        Minus,
        Multiply,
        Divide,
        Mod,
        LeftShift,
        RightShift,
        LessThan,
        GreaterThan,
        LessThanEqual,
        GreaterThanEqual,
        Equal,
        NotEqual,
        And,
        Or,
        Xor,
        AndAnd,
        OrOr,
        Assign,
        Null,
        Par,
        New,
        Label, //D
        Literal
};