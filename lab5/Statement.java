
// Statement.java
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class Statement {
	// static members and methods
	private static int maxNumOfInput;
	private static Roster roster = new Roster("AY2020");

	public static Statement parse(String input, int lineNo) {
		return new Statement(input, lineNo);
	}

	/**
	 * set the max number of records.
	 * 
	 * @param input user input
	 * 
	 */
	public static void setMaxRecord(String input) {
		// Parse the first command if its the first input
		try {
			Scanner sc = new Scanner(input);
			int max = sc.nextInt();
			maxNumOfInput = max;
		} catch (InputMismatchException e) {
			System.out.println("invalid number");
		}

	}

	private String command;
	private String[] arguments;
	private String message;

	/**
	 * Statement Constructor.
	 * 
	 * @param input user input
	 * @param line  line number
	 * 
	 */
	public Statement(String input, int line) {

		Scanner sc = new Scanner(input);

		// Parse arguments
		// Input format 1: <arg1> <arg2> <arg3> <arg4>
		// records have 4 arguments
		// Input format 2: <arg1> <arg2> <arg3>
		// query have 3 arguments
		if (line <= maxNumOfInput) {
			this.arguments = new String[4];
			this.command = "record";
		} else {
			this.arguments = new String[3];
			this.command = "query";
		}

		// read arguments
		try {
			for (int i = 0; i < arguments.length; i++) {
				this.arguments[i] = sc.next();
			}
			if (sc.hasNext()) {
				this.command = "err";
				this.message = "Too many arguments!";
			}

		} catch (InputMismatchException e) {
			this.command = "err";
			this.message = "Bad arguments!";

		} catch (NoSuchElementException e) {
			this.command = "err";
			this.message = "Too few arguments!";
		}
	}

	/**
	 * add the record to the roster
	 * 
	 * @param record formatted record
	 * 
	 */
	private void addRecord(String[] record) {
		Assessment assessment = new Assessment(record[2], record[3]);
		Module module = new Module(record[1]).put(assessment);
		Student student = new Student(record[0]).put(module);

		// if student is present and the module also present
		// update the map
		// if student is present but the module not present
		// put the module
		// else the student is not present
		// put the student with the module
		roster.get(record[0]).ifPresentOrElse(
				(stu) -> stu.get(record[1]).ifPresentOrElse((mod) -> mod.put(assessment), () -> stu.put(module)),
				() -> roster.put(student));

	}

	/**
	 * get the grade.
	 * 
	 * @param query formatted user input
	 * @return the query result
	 * 
	 */
	private String checkQuery(String[] query) {
		return roster.getGrade(query[0], query[1], query[2]);
	}

	/**
	 * evaluate the user input.
	 * 
	 * @return the query result
	 * 
	 */
	public String evaluate() {
		if (!this.command.equals("err")) {
			// if its record no return message
			if (this.command.equals("record")) {
				this.addRecord(this.arguments);
				this.message = "";
			}
			// if its record,return the result message
			if (this.command.equals("query")) {
				this.message = this.checkQuery(this.arguments);
			}
		}
		return this.message;
	}

}
