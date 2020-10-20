//*************** TFQ ****************
import java.util.*;

class TFQ extends QA {
    public TFQ(String question, char ans) {
        super(question, ans);
    }

    @Override
    boolean getAnswer() {
        char answer = this.getInput();        
        if (answer != 'T' && answer != 'F') {
            throw new InvalidTFQException("Invalid TFQ answer");
        }
        return this.correctAnswer == answer;
    }
}
