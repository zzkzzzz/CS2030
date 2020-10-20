import java.util.ArrayList;
import java.util.List;

public class qaTest {

    static void displayResults(List<Boolean> rList) {
        int marks = 0;
        for (boolean a : rList) {
            if (a)
                marks++;
        }
        System.out.println(String.format("You got %d questions correct.",
                                         marks));
    }
    
    public static void main(String[] args) {
        List<Boolean> results = new ArrayList<>();        
        List<QA> questions = new ArrayList<>();
        
        questions.add(new MCQ("What is 1+1?", 'A'));
        questions.add(new TFQ("The sky is blue (T/F)", 'T'));
        questions.add(new MCQ("Which animal is an elephant?", 'C'));
        questions.add(new TFQ("A square is a circle (T/F)", 'F'));

        for (QA q : questions) {
            Sandbox.make(q)
                .map(QA::displayQuestion)
                .map(QA::getAnswer)
                .consume(results::add);
        }
        displayResults(results);
    }
}
                      
