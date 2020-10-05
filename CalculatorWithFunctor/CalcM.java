//CalcM.java

import java.util.NoSuchElementException;
import calcm.*;

public class CalcM {
    public static void main(String[] args) {
	Statement.initialize();
	try {
	    while (true) {
                Sandbox.make(Input.readInput())
                    .map(Statement::new)
                    .map(Statement::parseCommand, "Bad command!")
                    .map(Statement::parseArguments, "Bad arguments!")
                    .map(Statement::evaluate)
                    .consume(Result::display);
	    }
	} catch (NoSuchElementException e) {
	    //break out of while loop; end program
	} 
    }
}
