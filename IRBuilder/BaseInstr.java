package IRBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class BaseInstr {
    InstrType Type;
    BaseInstr(InstrType instrType){
        Type = instrType;
    }
    abstract public void Output(FileWriter Writer) throws IOException;
}
