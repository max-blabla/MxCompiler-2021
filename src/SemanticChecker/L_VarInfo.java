package  SemanticChecker;
public class L_VarInfo{
    Integer Type;
    //0 表示有值 1 表示为null 2表示为返回值且为void
    Boolean IsNull;
   public L_VarInfo(){}
   public L_VarInfo(Integer type, Boolean isNull){
        Type = type;
        IsNull = isNull;
    }
    public Integer Type(){
       return Type;
    }
    public void SetType(Integer type){
       Type = type;
    }
    public void SetIsNull(Boolean isNull){
       IsNull = isNull;
    }
    public Boolean IsNull(){return IsNull;}
}