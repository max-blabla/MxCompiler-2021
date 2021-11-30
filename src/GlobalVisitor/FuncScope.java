<<<<<<< HEAD
package GlobalVisitor;

import SemanticChecker.L_VarInfo;

import java.util.HashMap;

public class FuncScope extends BaseScope{
    Integer RetType;
    Boolean IsNull;
    Boolean IsLeft;
    Boolean HasReturn;
    String FuncName;
    public FuncScope(){}
    public void SetRetType(Integer retType){
        RetType = retType;
    }
    public void SetIsLeft(Boolean isLeft){
        IsLeft = isLeft;

    }
    public void SetFuncName(String funcName){
        FuncName = funcName;
    }
    public String GetFuncName(){return FuncName;}
    public void SetHasReturn(Boolean hasReturn){HasReturn = hasReturn;}
    public void SetIsNull(Boolean isNull){
       IsNull = isNull;
    }
    public Boolean GetIsNull(){
        return IsNull;
    }
    public Integer GetRetType(){
        return RetType;
    }
    public Boolean GetIsLeft(){
        return IsLeft;
    }
    public Boolean GetHasReturn(){return HasReturn;}
=======
package GlobalVisitor;

import SemanticChecker.L_VarInfo;

import java.util.HashMap;

public class FuncScope extends BaseScope{
    Integer RetType;
    Boolean IsNull;
    Boolean IsLeft;
    Boolean HasReturn;
    String FuncName;
    public FuncScope(){}
    public void SetRetType(Integer retType){
        RetType = retType;
    }
    public void SetIsLeft(Boolean isLeft){
        IsLeft = isLeft;

    }
    public void SetFuncName(String funcName){
        FuncName = funcName;
    }
    public String GetFuncName(){return FuncName;}
    public void SetHasReturn(Boolean hasReturn){HasReturn = hasReturn;}
    public void SetIsNull(Boolean isNull){
       IsNull = isNull;
    }
    public Boolean GetIsNull(){
        return IsNull;
    }
    public Integer GetRetType(){
        return RetType;
    }
    public Boolean GetIsLeft(){
        return IsLeft;
    }
    public Boolean GetHasReturn(){return HasReturn;}
>>>>>>> 4c773d54cd780b6299f59d705f4124f10145b5a4
}