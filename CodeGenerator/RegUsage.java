package CodeGenerator;

public class RegUsage {
    UsageType Type;
    String RegName;
    String UserName;
    Integer Bytes;
    Integer Serial;
    RegUsage(String regName,Integer serial){
        Type = UsageType.Idle;
        Serial = serial;
        RegName = regName;
    }
}
