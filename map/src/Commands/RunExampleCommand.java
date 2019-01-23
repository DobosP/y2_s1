package Commands;

import Controller.Cntrl;

public class RunExampleCommand extends Command {
    private Cntrl cntrl;

    public RunExampleCommand(final String key, final String desc, final Cntrl cntrl) {
        super(key, desc);
        this.cntrl = cntrl;
    }

    @Override
    public void execute() {
        cntrl.allStep();
    }
}