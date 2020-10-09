import java.util.Optional;

public class Roster extends KeyableMap<Student> implements Keyable {
    Roster(String key) {
        super(key);
    }

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
