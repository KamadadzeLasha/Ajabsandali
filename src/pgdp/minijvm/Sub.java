package pgdp.minijvm;

public class Sub extends Instruction {
    @Override
    public void execute(Simulator simulator) {
        simulator.getStack().push(-simulator.getStack().pop() + simulator.getStack().pop());
    }
}