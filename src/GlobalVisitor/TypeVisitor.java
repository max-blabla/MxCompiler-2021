<<<<<<< HEAD
package GlobalVisitor;

import ErrorInfo.ErrorInfo;
import MxParser.MxBaseVisitor;
import MxParser.MxVisitor;
import MxParser.MxParser;
import MxParser.MxBaseListener;
import com.sun.source.tree.Scope;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;


//可以检测出的错误:函数返回、参数、变量声明类型不存在，全局变量重名，类变量重名
//在此同时可以补充内建函数
//接下来进行Sema Check : 调用未知签名，表达式类型错误，以及重定义变量，以及没有main函数
//接下来需要和内存分配结合
public class TypeVisitor extends MxBaseListener {
    //Stack<String> ErrorLine = new Stack<>();
    MxParser parser;
    TypeGather TypeSet = new TypeGather();
    int ErrorNum = 0;


    public TypeGather GetTypeMap(){
        return TypeSet;
    }
    public TypeVisitor(MxParser parser){
        this.parser = parser;
    }
    public void ShowType(){
        TypeSet.ShowType();
    }

    @Override
    public void enterClassDef(MxParser.ClassDefContext ctx){
        String ClassName = ctx.Identifier().getText();
        if(!TypeSet.Insert(ClassName)){ ErrorInfo errorInfo = new ErrorInfo("Duplicated Class",ctx.getText());
        throw new RuntimeException();
        }
    }

    //同时进行全局的登记，都要检查重名。
}
=======
package GlobalVisitor;

import ErrorInfo.ErrorInfo;
import MxParser.MxBaseVisitor;
import MxParser.MxVisitor;
import MxParser.MxParser;
import MxParser.MxBaseListener;
import com.sun.source.tree.Scope;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;


//可以检测出的错误:函数返回、参数、变量声明类型不存在，全局变量重名，类变量重名
//在此同时可以补充内建函数
//接下来进行Sema Check : 调用未知签名，表达式类型错误，以及重定义变量，以及没有main函数
//接下来需要和内存分配结合
public class TypeVisitor extends MxBaseListener {
    //Stack<String> ErrorLine = new Stack<>();
    MxParser parser;
    TypeGather TypeSet = new TypeGather();
    int ErrorNum = 0;


    public TypeGather GetTypeMap(){
        return TypeSet;
    }
    public TypeVisitor(MxParser parser){
        this.parser = parser;
    }
    public void ShowType(){
        TypeSet.ShowType();
    }

    @Override
    public void enterClassDef(MxParser.ClassDefContext ctx){
        String ClassName = ctx.Identifier().getText();
        if(!TypeSet.Insert(ClassName)){ ErrorInfo errorInfo = new ErrorInfo("Duplicated Class",ctx.getText());
        throw new RuntimeException();
        }
    }

    //同时进行全局的登记，都要检查重名。
}
>>>>>>> 4c773d54cd780b6299f59d705f4124f10145b5a4
