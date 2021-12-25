package ASTNode;

import MxParser.MxParser;

import java.util.List;

public class FuncDeclAST extends DeclAST{
    TypeAST RetType;
    ParamListAST ParamList;
    IdAST FuncName;
    SuiteAST FuncSuite;



    @Override
    public void InsertSon(BaseAST Son) {
        if(Son instanceof TypeAST){
            RetType = (TypeAST) Son;
            RetType.Father = this;
        }
        else if(Son instanceof  ParamListAST){
            ParamList = (ParamListAST) Son;
            ParamList.Father = this;
        }
        else if(Son instanceof IdAST){
            FuncName = (IdAST) Son;
            FuncName.Father = this;
        }
        else if(Son instanceof  SuiteAST){
            FuncSuite = (SuiteAST) Son;
            FuncSuite.Father = this;
        }
        else new BuildError("FuncDeclAST","InsertSon","");
    }

    public String getFuncName(){
        return FuncName.Label;
    }

    public String getFuncType(){
        return RetType.Type;
    }

    public List<StmtAST> getStmtList(){
        return FuncSuite.StmtList;
    }

    public List<VarDeclAST> getParamList(){return ParamList.ParamTypeList;}
};