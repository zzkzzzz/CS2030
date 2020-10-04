import java.util.ArrayList;
import java.util.List;

public class Location{
	private final String name;
	private final List<Person> occupantsList;
	Location(String name){
		this.name = name;
		this.occupantsList = new ArrayList<Person>();
	}

	Location(String name,List<Person> occupants){
		this.name = name;
		this.occupantsList = occupants;
	}

	public String getName(){
		return this.name;
	}

	public List<Person> getOccupants(){
		return new ArrayList<Person>(this.occupantsList);
	}

	public Location accept(Person person){
		List<Person> occupantsList = this.getOccupants();	
		occupantsList.add(person);
		return new Location(name,occupantsList);
	}

	public Location remove(String personName){
		List<Person> personsList = this.getOccupants();
		for(int i = 0; i < personsList.size(); i++){
			if(personsList.get(i).getName()==personName){
				personsList.remove(i);
			}
		}
		return new Location(name,personsList);
	}


}
