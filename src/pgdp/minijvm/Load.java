package pgdp.minijvm;

public class Load extends Instruction {
    int i;

    public Load(int i) {
        this.i = i;
    }

    @Override
    public void execute(Simulator simulator) {
        simulator.getStack().push(simulator.getStack().getValueAtIndex(i));
    }
}
