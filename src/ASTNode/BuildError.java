package ASTNode;

import java.util.Objects;

public class BuildError{
    BuildError(String Node,String Func,String Info) {
        if(Objects.equals(Info, ""))  System.out.println("[Error: "+Node+"]"+Func);
        else System.out.println("[Error: "+Node+"]"+Func+":"+Info);
    }
}