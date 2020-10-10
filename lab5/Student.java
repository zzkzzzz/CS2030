class Student extends KeyableMap<Module> implements Keyable {
    /**
     * Student Constructor.
     * 
     * @param key student id
     */
    Student(String key) {
        super(key);
    }

    @Override
    public Student put(Module item) {
        this.getMap().put(item.getKey(), item);
        return this;
    }
}
