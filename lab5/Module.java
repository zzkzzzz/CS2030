class Module extends KeyableMap<Assessment> implements Keyable {
    /**
     * Module Constructor
     * 
     * @param key module id
     */
    Module(String key) {
        super(key);
    }

    @Override
    public Module put(Assessment item) {
        this.getMap().put(item.getKey(), item);
        return this;
    }
}
