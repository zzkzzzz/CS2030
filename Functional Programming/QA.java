//*************** QA ****************
import java.util.*;

abstract class QA {

    String question;
    char correctAnswer;

    public QA(String question, char ans) {
        this.question = question;
        this.correctAnswer = ans;
    }
    
    abstract boolean getAnswer();

    public QA displayQuestion() {
        System.out.println(this.question);
        return this;
    }
        
    public char getInput() {
        Scanner sc = new Scanner(System.in);            
        System.out.printf("=> ");
        char result = sc.next().charAt(0);
        System.out.println("");
        return result;
    }
}

