package pgdp.minijvm;

public class Halt extends Instruction {
    @Override
    public void execute(Simulator simulator) {
        simulator.setHalted(true);
    }
}
