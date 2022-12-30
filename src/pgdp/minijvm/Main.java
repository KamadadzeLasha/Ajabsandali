package pgdp.minijvm;

public class Main {
    public static void main(String[] args) {
        Instruction[] code = new Instruction[]{new Halt()};
        Simulator simulator = new Simulator(2, code);
        simulator.executeInstructions();
        Instruction[] code1 = new Instruction[]{new Const(3), new Halt()};
        Simulator simulator1 = new Simulator(2, code1);
        simulator1.executeInstructions();
        Instruction[] code2 = new Instruction[] {new Const(3), new Const(2), new Sub(), new Halt()};
        Simulator simulator2 = new Simulator(3, code2);
        simulator2.executeInstructions();
        Instruction[] code3 = new Instruction[]{
                //The program calculates the sum of the whole numbers from 1 to 5
                new Alloc(2),
                new Const(0),
                new Store(0),
                new Const(5),
                new Store(1),
                new Const(0),
                new Load(1),
                new Less(),
                new FJump(18),
                new Load(0),
                new Load(1),
                new Add(),
                new Store(0),
                new Load(1),
                new Const(1),
                new Sub(),
                new Store(1),
                new Jump(5),
                new Halt()};
        Simulator simulator3 = new Simulator(5, code3);
        simulator3.executeInstructions();
        System.out.println(simulator3.getStack());
    }
}
