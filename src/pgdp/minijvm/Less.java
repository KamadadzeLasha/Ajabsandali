package pgdp.minijvm;

public class Less extends Instruction {
    @Override
    public void execute(Simulator simulator) {
        int a = simulator.getStack().pop();
        int b = simulator.getStack().pop();
        if (b < a ){
            simulator.getStack().push(1);
        }
        else {
            simulator.getStack().push(0);
        }
    }
}
