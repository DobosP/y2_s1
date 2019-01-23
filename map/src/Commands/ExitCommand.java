package Commands;

public class ExitCommand extends Command {

    public ExitCommand(final String key, final String desc) {
        super(key, desc);
    }

    @Override
    public void execute() {
        System.exit(0);
    }

}