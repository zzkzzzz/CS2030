//*************** MCQ ****************
import java.util.*;

class MCQ extends QA {
    public MCQ(String question, char ans) {
        super(question, ans);
    }

    @Override
    boolean getAnswer() {
        char answer = this.getInput();
        if (answer < 'A' || answer > 'E') {
            throw new InvalidMCQException("Invalid MCQ answer");
        }
        return this.correctAnswer == answer;
    }
}
