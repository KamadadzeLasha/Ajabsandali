package pgdp.minijvm;

public class Const extends Instruction{
    int  i;

    public Const(int i) {
        this.i = i;
    }

    @Override
    public void execute(Simulator simulator) {
        simulator.getStack().push(i);
    }
}
