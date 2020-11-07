import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Candle extends Item {

    public Candle() {
        super(0, new ArrayList<String>(Arrays.asList("Candle flickers.", "Candle is getting shorter.",
                "Candle is about to burn out.", "Candle has burned out.")));
    }

    public Candle(int status) {
        super(status, new ArrayList<String>(Arrays.asList("Candle flickers.", "Candle is getting shorter.",
                "Candle is about to burn out.", "Candle has burned out.")));
    }

    public Candle(int status, List<String> list) {

        super(status, list);
    }

    public Candle change() {
        if (this.getStatus() == this.getStatusList().size() - 1)
            return this;
        return new Candle(this.getStatus() + 1);
    }
}
