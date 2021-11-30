<<<<<<< HEAD
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
=======
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
>>>>>>> 4c773d54cd780b6299f59d705f4124f10145b5a4
}