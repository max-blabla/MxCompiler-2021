package  SemanticChecker;
import ErrorInfo.ErrorInfo;
import GlobalVisitor.BaseScope;
import GlobalVisitor.ClassScope;
import GlobalVisitor.GlobalScope;
import com.sun.source.tree.Scope;

import java.util.ListIterator;
import java.util.Objects;
import java.util.Stack;
import java.util.Vector;
class RetInfo{
    Integer RetType;
    Boolean IsLeftValue;
    public void NewType(Integer type){
        RetType = type;
    }
}
public class ExpTree{
    int Cnt = 0;
    ExpNode Root;
    ExpNode This;
    Stack<String> EnvironmentType;
    Stack<BaseScope> Environment;
    GlobalScope GlobalTable;
    public void NewTree(){
        Root = new ExpNode();
        Root.ThisType = -1;
        Root.OpType = ExpType.Root;
        Root.IsThisLeft = Root.IsNull = true;
        This = Root;
    }
    //可以直接先查然后告诉type
    //对于函数运算符或者new运算也可以这样
    //并且函数运算符多一个Param节点，彼此交流用Vec
    //然后new 也一样
    //++ --  + - ! ~ 这几个都是单目运算
    public void SetEnvironment(Stack<BaseScope> environment){

        Environment = environment;
/*        for(BaseScope BS: Environment){
            BS.ShowVar();
        }*/
    }
    public void SetEnvironmentType(Stack<String> environmentType){
        EnvironmentType = environmentType;
    }
    public void SetGlobalTable(GlobalScope globalTable){
        GlobalTable = globalTable;
    }
    private void InnerSemanticCheck(ExpNode root){

        //包括调节优先级
        switch(root.OpType){
            case Root : {
                InnerSemanticCheck(root.SonVec.get(0));
                ExpNode Son = root.SonVec.get(0);
                root.ThisType = Son.ThisType;
                root.IsThisLeft = Son.IsThisLeft;
                break;
            }
            case Primary:
            case PrimaryExpression:{
                InnerSemanticCheck(root.SonVec.get(0));
                ExpNode Son = root.SonVec.get(0);
                root.ThisType = Son.ThisType;
                root.IsThisLeft = Son.IsThisLeft;
                root.Id = Son.Id;
                break;
            }
            case LambdaExpr:
            case PrimaryNewSingle:
            case PrimaryRightValue: {
                //什么都不做
                break;
            }
            case PrimaryLeftValue:{
                // primary 自带两层...

                if(root.Father.Father.OpType!=ExpType.Mem){
                    ListIterator<BaseScope> iter = Environment.listIterator(Environment.size());
                    boolean IsExisted = false;
                    while(iter.hasPrevious()){
                        BaseScope Scope = iter.previous();
                        if(Scope.FindVar(root.Id)){
                            IsExisted = true;
                            root.ThisType = Scope.GetType(root.Id);
                            break;
                        }
                    }
                    int Index  = EnvironmentType.search("class");
                    if(Index != -1) {
                        ClassScope CS = Environment.get(EnvironmentType.size()-Index).GetClassDerivation();
                        if (!IsExisted && CS.FindVar(root.Id)) {
                            IsExisted = true;
                            root.ThisType = CS.GetType(root.Id);
                        } else if (!IsExisted && CS.FindFunc(root.Id)) {
                            IsExisted = true;
                            root.Id = CS.GetClassName() + "." + root.Id;
                        }
                    }

                    int index = EnvironmentType.search("global");

                    GlobalScope GS = Environment.get(EnvironmentType.size()-EnvironmentType.search("global")).GetGlobalDerivation();
                    if(!IsExisted&&GS.FindVar(root.Id)){
                        IsExisted = true;
                        root.ThisType = GS.GetType(root.Id);
                    }
                    else if(!IsExisted&&GS.FindFunc(root.Id)){
                        IsExisted = true;
                        root.ThisType = GS.GetFunc(root.Id).get(0);
                    }
                    if(!IsExisted){
                        ErrorInfo errorInfo = new ErrorInfo("Id Not Found", "");
                        throw new RuntimeException();
                    }
                }
            }
            case PrimaryNewArray:{
                for(ExpNode Son : root.SonVec){
                    InnerSemanticCheck(Son);
                    if(Son.ThisType!=2){
                        ErrorInfo errorInfo = new ErrorInfo("Expression In Array Is Only Int", "");
                        throw new RuntimeException();
                    }
                }
                break;
            }
            case Mem:{
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                int Type = 0;
                if(LeftSon.SonVec.get(0).OpType == ExpType.PrimaryLeftValue){
                    ListIterator<BaseScope> iter = Environment.listIterator(Environment.size());

                    boolean IsExisted = false;
                    while(iter.hasPrevious()){
                        BaseScope Scope = iter.previous();

                        if(Scope.FindVar(LeftSon.Id)){
                            IsExisted = true;
                            Type = Scope.GetType(LeftSon.Id);
                            break;
                        }
                    }
                    if(!IsExisted){
                        ErrorInfo errorInfo = new ErrorInfo("Left Id Not Found", "");
                        throw new RuntimeException();
                    }
                }
                else{Type = LeftSon.ThisType;}
                ClassScope CS= GlobalTable.GetClassScope(GlobalTable.InvType(Type));

                if(CS.FindVar(RightSon.Id)){
                    root.Id = CS.GetClassName() + "." + RightSon.Id;
                    root.IsThisLeft = true;
                    root.ThisType = CS.GetType(RightSon.Id);
                }
                else if(CS.FindFunc(RightSon.Id)){
                    root.Id = CS.GetClassName() + "." + RightSon.Id;
                    root.IsThisLeft = true;
                    root.ThisType = -2;
                }
                else{
                    ErrorInfo errorInfo = new ErrorInfo("Right Id Not Found", "");
                    throw new RuntimeException();
                }
                break;
            }
            case ParamList:{
                for(ExpNode Son : root.SonVec){
                    InnerSemanticCheck(Son);
                }
                break;
            }
            case FuncExpr: {
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if (!Objects.equals(LeftSon.Id, "")) {
                    int Pos = LeftSon.Id.indexOf(".");
                    String FuncName = LeftSon.Id.substring(Pos + 1);

                    Vector<Integer> TypeVec = null;
                    Vector<ExpNode> RetTypeVec = RightSon.SonVec;
                    GlobalScope GS = Environment.get(EnvironmentType.size()-EnvironmentType.search("global")).GetGlobalDerivation();

                    if (Pos != -1) {
                        String ClassName = LeftSon.Id.substring(0, Pos);
                        ClassScope CS = GS.GetClassScope(ClassName);
                        TypeVec = CS.GetFunc(FuncName);
                    }
                    else {
                        if(LeftSon.OpType==ExpType.LambdaExpr){
                            TypeVec = Environment.peek().GetLambda();
                        }else {
                            int Index = EnvironmentType.search("class");
                            if (Index != -1) {
                                ClassScope CS = Environment.get(EnvironmentType.size() - Index).GetClassDerivation();
                                if (CS.FindFunc(FuncName)) {
                                    TypeVec = CS.GetFunc(FuncName);
                                } else {
                                    TypeVec = GS.GetFunc(FuncName);
                                }
                            } else {
                                TypeVec = GS.GetFunc(FuncName);
                            }
                        }
                    }
                    if (RetTypeVec.size() != TypeVec.size()-1) {
                        ErrorInfo errorInfo = new ErrorInfo("The Number Of Param Is Not Match", "");
                        throw new RuntimeException();
                    }
                    for (int i = 0; i < TypeVec.size()-1; ++i) {
                        if (!Objects.equals(RetTypeVec.get(i).ThisType, TypeVec.get(i+1))&&(!((RetTypeVec.get(i).ThisType==-3)&&TypeVec.get(i+1)>=4))) {
                            ErrorInfo errorInfo = new ErrorInfo("The Type Of Param Is Not Match", "");
                            throw new RuntimeException();
                        }
                    }
                    root.ThisType = TypeVec.get(0);
                    root.IsThisLeft = false;
                }
                else{
                    ErrorInfo errorInfo = new ErrorInfo("Strange Error In FuncExpr", "");
                    throw new RuntimeException();
                }
                break;
            }
            case IndexExpr:{
                InnerSemanticCheck(root.SonVec.get(0));

                ExpNode LeftSon = root.SonVec.get(0);
                for(int i =1;i<root.SonVec.size(); ++ i){
                    InnerSemanticCheck(root.SonVec.get(i));
                    if(root.SonVec.get(i).ThisType != 2){
                        ErrorInfo errorInfo = new ErrorInfo("IndexExpr Only To Be Int", "");
                        throw new RuntimeException();
                    }
                }
                if(!LeftSon.IsNull){
                    String TypeName = GlobalTable.InvType(LeftSon.ThisType);
                    int First = TypeName.indexOf("[");
                    int Last = TypeName.lastIndexOf("[");
                    int Dimension = (Last-First)/2+1;
                    if(First == -1) {
                        ErrorInfo errorInfo = new ErrorInfo("This Is Not Array", "");
                        throw new RuntimeException();
                    }
                    else{
                        int RetDimension = Dimension - root.SonVec.size()+1;
                        String RetTypeName = TypeName.substring(0,First)+("[]").repeat(RetDimension);
                        GlobalTable.NewArray(RetTypeName);
                        root.ThisType = GlobalTable.FindType(RetTypeName);
                    }
                    root.IsThisLeft = true;
                } else {
                    ErrorInfo errorInfo = new ErrorInfo("Null Array", "");
                    throw new RuntimeException();
                }
                break;
            }
            case Not:{
                InnerSemanticCheck(root.SonVec.get(0));
                ExpNode Son = root.SonVec.get(0);
                if (Son.ThisType == 2 ) {
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else if(Son.ThisType==3){
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else {
                    ErrorInfo errorInfo = new ErrorInfo("Not Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case And:
            case Or:{
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if (LeftSon.ThisType == 2 && RightSon.ThisType == 2) {
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else if(LeftSon.ThisType==3 && RightSon.ThisType == 3){
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else {
                    ErrorInfo errorInfo = new ErrorInfo("And/Or Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case Equal:
            {
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if (LeftSon.ThisType == 2 && RightSon.ThisType == 2) {
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else if(LeftSon.ThisType==3 && RightSon.ThisType == 3){
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else if (LeftSon.ThisType == 4 && RightSon.ThisType == 4) {
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else if((LeftSon.ThisType>=4||LeftSon.ThisType==-3) && RightSon.ThisType == -3){
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else {
                    ErrorInfo errorInfo = new ErrorInfo("Equal/Comparison Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case Comparison: {
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if (LeftSon.ThisType == 2 && RightSon.ThisType == 2) {
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else if(LeftSon.ThisType==4 && RightSon.ThisType == 4){
                    root.ThisType = 3;
                    root.IsThisLeft = false;
                }
                else {
                    ErrorInfo errorInfo = new ErrorInfo("Equal/Comparison Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case SelfLeft: {
                InnerSemanticCheck(root.SonVec.get(0));
                ExpNode Son = root.SonVec.get(0);
                if (Son.ThisType == 2 && Son.IsThisLeft) {
                    root.ThisType = 2;
                    root.IsThisLeft = true;
                } else {
                    ErrorInfo errorInfo = new ErrorInfo("SelfLeft Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case SelfRight:{
                InnerSemanticCheck(root.SonVec.get(0));
                ExpNode Son = root.SonVec.get(0);
                if(Son.ThisType==2&&Son.IsThisLeft){
                    root.ThisType = 2;
                    root.IsThisLeft = false;
                }
                else {
                    ErrorInfo errorInfo = new ErrorInfo("SelfRight Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case SignChange:
            case BitNot: {
                InnerSemanticCheck(root.SonVec.get(0));
                ExpNode Son = root.SonVec.get(0);
                if(Son.ThisType == 2){
                    root.ThisType = 2;
                    root.IsThisLeft = false;
                }
                else {
                    ErrorInfo errorInfo = new ErrorInfo("SignChange Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case BitAnd:
            case BitXor:
            case BitOr:
            case Minus:
            case Shift:
            case Multiply: {
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if (LeftSon.ThisType == 2 && RightSon.ThisType == 2) {
                    root.ThisType = 2;
                    root.IsThisLeft = false;
                } else {
                    ErrorInfo errorInfo = new ErrorInfo("BitNot Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case Plus: {
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if (LeftSon.ThisType == 2 && RightSon.ThisType == 2) {
                    root.ThisType = 2;
                    root.IsThisLeft = false;
                } else if (LeftSon.ThisType == 4 && RightSon.ThisType == 4) {
                    root.ThisType = 4;
                    root.IsThisLeft = false;
                } else {
                    ErrorInfo errorInfo = new ErrorInfo("Plus Type Error", "");
                    throw new RuntimeException();
                }
                break;
            }
            case Assign:{
                InnerSemanticCheck(root.SonVec.get(0));
                InnerSemanticCheck(root.SonVec.get(1));
                ExpNode LeftSon = root.SonVec.get(0);
                ExpNode RightSon = root.SonVec.get(1);
                if(LeftSon.Id.contains("this")){
                    ErrorInfo errorInfo = new ErrorInfo("This Is Not Assignable", "");
                    throw new RuntimeException();
                }
                if(LeftSon.IsThisLeft){
                    if(Objects.equals(LeftSon.ThisType, RightSon.ThisType)){
                        root.ThisType = LeftSon.ThisType;
                        root.IsThisLeft = true;
                    }
                    else if(root.SonVec.get(1).ThisType==-3 && LeftSon.ThisType>=4){
                        root.ThisType = LeftSon.ThisType;
                        root.IsThisLeft = true;
                    }
                    else{
                        ErrorInfo errorInfo = new ErrorInfo("Assign Type Error", "");
                        throw new RuntimeException();
                    }
                }
                else{
                    ErrorInfo errorInfo = new ErrorInfo("Assign Only To Be LValue", "");
                    throw new RuntimeException();
                }
                break;
            }
        }
    }
    private void LeftRotate(ExpNode root){
        ExpNode RightSon = root.SonVec.get(1);
        int Index = root.Father.SonVec.indexOf(root);
        root.Father.SonVec.set(Index,RightSon);
        RightSon.Father = root.Father;

        root.SonVec.set(1,RightSon.SonVec.get(0));
        RightSon.SonVec.get(0).Father = root;

        root.Father = RightSon;
        RightSon.SonVec.set(0,root);


    }
    private void RightRotate(ExpNode root){
        ExpNode LeftSon = root.SonVec.get(0);
        int Index = root.Father.SonVec.indexOf(root);
        root.Father.SonVec.set(Index,LeftSon);
        LeftSon.Father = root.Father;
        if(LeftSon.SonVec.size()==1){
            root.SonVec.set(0,LeftSon.SonVec.get(0));
            LeftSon.SonVec.get(0).Father = root;
            root.Father = LeftSon;
            LeftSon.SonVec.set(0,root);
        }
        else {
            root.SonVec.set(0, LeftSon.SonVec.get(1));
            LeftSon.SonVec.get(1).Father = root;
            root.Father = LeftSon;
            LeftSon.SonVec.set(1,root);
        }

    }
    private void PriorityModify(ExpNode root){
      /*  if(root.OpType.IsEqual(0)&&root.OpType != ExpType.PrimaryExpression){
            //什么都不做
            for(ExpNode Son : root.SonVec) PriorityModify(Son);
        }
        else*/ if(root.OpType.IsEqual(2)){
            if(root.SonVec.get(0).OpType.Comp(root.OpType)&&!(root.OpType.IsEqual(root.SonVec.get(0).OpType))){
                ExpNode Son = root.SonVec.get(0);
                int Index = root.Father.SonVec.indexOf(root);
                root.Father.SonVec.set(Index,Son);
                Son.Father = root.Father;

                root.SonVec.set(0,Son.SonVec.get(0));
                Son.SonVec.get(0).Father = root;

                root.Father = Son;
                Son.SonVec.set(0,root);
                PriorityModify(root.Father.Father);
            }
        }
        else if(root.OpType.IsEqual(13)){
            ExpNode RightSon = root.SonVec.get(1);
            if(RightSon.OpType.Comp(root.OpType)&&!RightSon.OpType.IsEqual(root.OpType)){
                //左旋先
                LeftRotate(root);
                PriorityModify(root.Father.Father);
            }
            ExpNode LeftSon = root.SonVec.get(0);
            if(LeftSon.OpType.Comp(root.OpType)){
                //再右旋
                RightRotate(root);
                PriorityModify(root.Father.Father);
            }
        }
        else if(root.OpType.Comp(3)||root.OpType.IsEqual(1)){

            ExpNode LeftSon = root.SonVec.get(0);
            if(LeftSon.OpType.Comp(root.OpType)&&!LeftSon.OpType.IsEqual(root.OpType)){
                //右旋先
                RightRotate(root);
                PriorityModify(root.Father.Father);
            }

            ExpNode RightSon = root.SonVec.get(1);

            if(RightSon.OpType.Comp(root.OpType)) {
                //再左旋
                if (root.OpType == ExpType.IndexExpr) {
                }
                else {
                    LeftRotate(root);
                    PriorityModify(root.Father.Father);
                }
            }

        }
        for(ExpNode Son : root.SonVec) PriorityModify(Son);
    }
    public Boolean ExpTreeIsNull(){
        return Root.IsNull;
    }
    public Boolean ExpTreeIsLeft(){
        return Root.IsThisLeft;
    }
    public Integer ExpTreeType(){
        return Root.ThisType;
    }
    public void UpTree(){
        This = This.Father;
    }
    public void InsertSon(ExpType opType, Integer PrimType, Boolean IsLeft, Boolean IsNull, String Id){

        ExpNode NewSon = new ExpNode();
        NewSon.OpType = opType;
        NewSon.ThisType = PrimType;
        NewSon.IsThisLeft = IsLeft;
        NewSon.IsNull = IsNull;
        NewSon.Id = Id;
        NewSon.Father = This;

        This.SonVec.add(NewSon);
    }
    public void DownTree(){
        int Index = This.VisitedSonNum;
        ++This.VisitedSonNum;
        This = This.SonVec.get(Index);
    }
    public void ShowTree(ExpNode root){
        System.out.println(root.OpType);
        for(ExpNode Son : root.SonVec) ShowTree(Son);
    }
    public boolean SemanticCheck(){
        PriorityModify(Root);
        InnerSemanticCheck(Root);
        return true;
    }
}

class ExpNode{
    ExpType OpType;
    ExpNode Father;
    Vector<ExpNode> SonVec;
    Integer ThisType;
    Boolean IsThisLeft;
    Boolean IsNull;
    Integer VisitedSonNum;
    String Id="";
    ExpNode(){
        SonVec = new Vector<>();
        VisitedSonNum  = 0;
    }
}

