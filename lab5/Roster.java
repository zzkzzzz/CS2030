import java.util.Optional;

public class Roster extends KeyableMap<Student> implements Keyable {
    /**
     * Roster Constructor.
     * 
     * @param key roster id
     */
    Roster(String key) {
        super(key);
    }

    /**
     * get the grade according to given info.
     * 
     * @param student   student id
     * @param module    module id
     * @param assesment assesment id
     * @return the grade, if the grade not exist, return err message
     */
    public String getGrade(String student, String module, String assessment) {
        String errMessage = String.format("No such record: %s %s %s", student, module, assessment);
        Optional<Module> mod = this.get(student).flatMap(x -> x.get(module));
        Optional<Assessment> result = mod.flatMap(x -> x.get(assessment));

        return result.map(Assessment::getGrade).orElse(errMessage);
    }

    @Override
    public Roster put(Student item) {
        this.getMap().put(item.getKey(), item);
        return this;
    }

}
