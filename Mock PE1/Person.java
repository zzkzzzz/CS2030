import java.util.ArrayList;
import java.util.List;

public class Person {
	private final String name;
	private final List<Virus> virusList;
	private final boolean SHN_status;

	Person(String name) {
		this.name = name;
		this.virusList = new ArrayList<Virus>();
		this.SHN_status = false;
	}

	Person(String name, List<Virus> virusList) {
		this.name = name;
		this.virusList = virusList;
		this.SHN_status = false;
	}

	public String getName() {
		return name;
	}

	public List<Virus> getVirusList() {
		List<Virus> newList = new ArrayList<Virus>(this.virusList);
		return newList;
	}

	public List<Virus> transmit(double random) {
		List<Virus> newViriusList = new ArrayList<Virus>();
		List<Virus> existingViriusList = this.getVirusList();
		for (int i = 0; i < virusList.size(); i++) {
			newViriusList.add(existingViriusList.get(i).spread(random));
		}

		return newViriusList;
	}

	public Person infectWith(List<Virus> listOfViruses, double random) {
		List<Virus> newViriusList = this.getVirusList();
		for (int i = 0; i < listOfViruses.size(); i++) {
			if (!this.test(listOfViruses.get(i).getName()))
				newViriusList.add(listOfViruses.get(i));
		}
		return new Person(name, newViriusList);
	}

	public boolean test(String virusName) {
		for (int i = 0; i < virusList.size(); i++) {
			if (virusList.get(i).getName().equals(virusName))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean onSHN(double time) {
		return this.SHN_status;
	}
}
