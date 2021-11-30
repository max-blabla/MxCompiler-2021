<<<<<<< HEAD
package SemanticChecker;
public enum ExpType{

    Root(0),//
    Null(0),//
    PrimaryExpression(0),//
    PrimaryNewSingle(0),//
    PrimaryNewArray(0),//
    PrimaryLeftValue(0),//
    PrimaryRightValue(0),//
    Primary(0),//
    LambdaExpr(0), // 值由return 推断
    FuncExpr(1), // a() L
    ParamList(0), //
    IndexExpr(1), // a[a] L
    SelfRight(2), // x++ x-- R
    SelfLeft(2), // ++x --x R
    SignChange(2), // + - R
    Not(2), // ! R
    BitNot(2), // ~ R
    Mem(1),// L
    Plus(4),// + L
    Minus(4),// - L
    Multiply(3),// * / % L
    Shift(5),// << >> L
    Comparison(6),// <= >= < > L
    Equal(7),// == != L
    BitAnd(8),// & L
    BitXor(9),// ^ L
    BitOr(10),// | L
    And(11),// && L
    Or(12),// || L
    Assign(13) // = R;
    ;
    private int value;
    ExpType(int value){
        this.value = value;
    }
    boolean Comp(ExpType r){
        return this.value>=r.value;
    }
    boolean Comp(int r){
        return this.value>=r;
    }
    boolean IsEqual(ExpType r){
        return this.value==r.value;
    }
    boolean IsEqual(int r){
        return this.value==r;
    }
=======
package SemanticChecker;
public enum ExpType{

    Root(0),//
    Null(0),//
    PrimaryExpression(0),//
    PrimaryNewSingle(0),//
    PrimaryNewArray(0),//
    PrimaryLeftValue(0),//
    PrimaryRightValue(0),//
    Primary(0),//
    LambdaExpr(0), // 值由return 推断
    FuncExpr(1), // a() L
    ParamList(0), //
    IndexExpr(1), // a[a] L
    SelfRight(2), // x++ x-- R
    SelfLeft(2), // ++x --x R
    SignChange(2), // + - R
    Not(2), // ! R
    BitNot(2), // ~ R
    Mem(1),// L
    Plus(4),// + L
    Minus(4),// - L
    Multiply(3),// * / % L
    Shift(5),// << >> L
    Comparison(6),// <= >= < > L
    Equal(7),// == != L
    BitAnd(8),// & L
    BitXor(9),// ^ L
    BitOr(10),// | L
    And(11),// && L
    Or(12),// || L
    Assign(13) // = R;
    ;
    private int value;
    ExpType(int value){
        this.value = value;
    }
    boolean Comp(ExpType r){
        return this.value>=r.value;
    }
    boolean Comp(int r){
        return this.value>=r;
    }
    boolean IsEqual(ExpType r){
        return this.value==r.value;
    }
    boolean IsEqual(int r){
        return this.value==r;
    }
>>>>>>> 4c773d54cd780b6299f59d705f4124f10145b5a4
}