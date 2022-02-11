package IRBuilder;

import ASTNode.*;
import com.sun.jdi.Value;
import org.antlr.v4.runtime.misc.Pair;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class IROutput {
    List<IRModule> ModuleList;
    public IROutput(List<IRModule> modules){
        ModuleList = modules;
    }
    public void FileRun(String Filename){
        try {
            FileWriter LlvmWriter = new FileWriter(Filename);
          //  FileWriter Writer = new FileWriter("./src/outtest.txt");
          //  Writer.write('?');
        //    Writer.close();
            for(IRModule Module : ModuleList) Module.Output(LlvmWriter);
            LlvmWriter.close();
        }catch(Exception E){
            System.out.println(".");
        }
    }


}

