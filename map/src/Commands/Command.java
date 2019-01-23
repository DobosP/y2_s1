package Commands;

public abstract class Command {
    private String key, description;

    public Command(final String key, final String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute();

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

}
