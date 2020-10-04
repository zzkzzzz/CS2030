import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Contact {
	private final Person first;
	private final Person second;
	private final double time;

	Contact(Person first, Person second, double time) {
		this.first = first;
		this.second = second;
		this.time = time;
	}

	public List<Person> transmit(double random) {
		List<Person> persons = new ArrayList<Person>();
		Person newFirst = first.infectWith(second.transmit(random), 0);
		Person newSecond = second.infectWith(first.transmit(random), 0);
		persons.add(newFirst);
		persons.add(newSecond);

		return persons;
	}

	public List<Person> getPeople() {
		return new ArrayList<Person>(Arrays.asList(first, second));
	}

	public double timeOfContact() {
		return time;
	}
}
