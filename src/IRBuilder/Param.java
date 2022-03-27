package IRBuilder;

public class Param {
    String Label;
    String Type;
    String Name;
    Param(String type,String name,String label){
        Type = type;
        Name = name;
        Label = label;
    }

    public String getName() {
        return Name;
    }
}
