import java.util.List;

public class DormsWithShn extends Dorms {
    private static final String TESTS_POSITIVE = "%s tests positive for %s " + "at time %.3f";
    private static final String TESTS_NEGATIVE = "%s test negative for %s " + "at time %.3f";
    private static final String SHN = "%s has been served a SHN that ends at %.3f";

    public DormsWithShn(boolean verbose) {
        super(verbose);
    }

    /**
     * Handles the sick person.
     * 
     * @param person The latest state of the sick Person
     * @param time   The time this is handled
     */
    @Override
    void handleSickPerson(Person person, double time) {
        if (!person.test(SimulationParameters.TARGET_VIRUS)) {
            log(String.format(TESTS_NEGATIVE, person, SimulationParameters.TARGET_VIRUS, time));

        } else {
            List<? extends Contact> contacts = this.queryContacts(person.getName(), time);
            log(String.format(TESTS_POSITIVE, person, SimulationParameters.TARGET_VIRUS, time));
            log(String.format(SHN, person, time + 28));
            for (Contact contact : contacts) {
                List<Person> involved = contact.getPeople();
                double timeOfContact = contact.timeOfContact();
                String firstName = involved.get(0).toString();
                String secondName = involved.get(1).toString();
                if (firstName.equals(person.getName())) {
                    log(String.format(SHN, involved.get(1), timeOfContact + 14));
                } else if (secondName.equals(person.toString())) {
                    log(String.format(SHN, involved.get(0), timeOfContact + 14));
                }
            }
        }
    }

}
