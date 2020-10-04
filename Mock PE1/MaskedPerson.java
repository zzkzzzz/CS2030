import java.util.ArrayList;
import java.util.List;

public class MaskedPerson extends Person{
	MaskedPerson(String name){
		super(name);
	}	

	MaskedPerson(String name,List<Virus> virusList){
		super(name,virusList);
	}

	@Override
	public Person infectWith(List<Virus> listOfViruses, double random) {
		List<Virus> newViriusList = this.getVirusList();
		if(random<=SimulationParameters.MASK_EFFECTIVENESS)
			return new MaskedPerson(this.getName(),newViriusList);
		for (int i = 0; i < listOfViruses.size(); i++) {
			if (!this.test(listOfViruses.get(i).getName()))
				newViriusList.add(listOfViruses.get(i));
		}
		return new MaskedPerson(this.getName(), newViriusList);
	}

	@Override
	public List<Virus> transmit(double random) {

		List<Virus> newViriusList = new ArrayList<Virus>();
		List<Virus> existingViriusList = this.getVirusList();
		if(random<=SimulationParameters.MASK_EFFECTIVENESS)
			return newViriusList;

		for (int i = 0; i <existingViriusList.size(); i++) {
			newViriusList.add(existingViriusList.get(i).spread(random));
		}

		return newViriusList;
	}
}



