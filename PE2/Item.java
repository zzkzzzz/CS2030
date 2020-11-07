import java.util.List;
import java.util.ArrayList;

public abstract class Item{
    
    private final int status;
    private final List<String> statusList;

    Item(int status, List<String> statusList) {
        this.status=status;
        this.statusList=new ArrayList<>(statusList);
    }

    public int getStatus(){
        return this.status;
    }

    public List<String> getStatusList(){
        return this.statusList;
    }

    abstract public Item change();  

    public String toString(){
        return this.statusList.get(this.status);
    }

}
