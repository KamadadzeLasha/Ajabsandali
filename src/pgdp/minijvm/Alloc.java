package pgdp.minijvm;

public class Alloc extends Instruction {
    int i;

    public Alloc(int i) {
        this.i = i;
    }
    @Override
    public void execute(Simulator simulator) {
        simulator.getStack().alloc(i);
    }
}
