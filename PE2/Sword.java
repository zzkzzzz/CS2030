import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class Sword extends Item{

    public Sword(){
        super(0,new ArrayList<String>(Arrays.asList("Sword is shimmering.")));
    }

    public Sword(int status){

        super(status,new ArrayList<String>(Arrays.asList("Sword is shimmering.")));
    }


    public Sword(int status,List<String> list){

        super(status,list);
    }

    public Sword change(){
        if(this.getStatus()==this.getStatusList().size()-1)
            return this;
        return new Sword(this.getStatus()+1);
    }
}
