package pgdp.minijvm;

public class  FJump extends Instruction {
    int i;

    public FJump(int i) {
        this.i = i;
    }

    @Override
    public void execute(Simulator simulator) {
        if(simulator.getStack().pop() == 0){
            simulator.setProgramCounter(i);
        }
    }
}