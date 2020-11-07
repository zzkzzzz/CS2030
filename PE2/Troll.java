import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Troll  extends Item{

    public Troll(){
        super(0,new ArrayList<String>(Arrays.asList("Troll lurks in the shadows.","Troll is getting hungry.","Troll is VERY hungry.","Troll is SUPER HUNGRY and is about to ATTACK!","Troll attacks!")));
    }

    public Troll(int status){
        super(status,new ArrayList<String>(Arrays.asList("Troll lurks in the shadows.", "Troll is getting hungry.","Troll is VERY hungry.","Troll is SUPER HUNGRY and is about to ATTACK!","Troll attacks!")));
    }

    public Troll(int status,List<String> list){

        super(status,list);
    }

    public Troll  change(){
        if(this.getStatus()==this.getStatusList().size()-1)
            return this;
        return new Troll(this.getStatus()+1);
    }
}
