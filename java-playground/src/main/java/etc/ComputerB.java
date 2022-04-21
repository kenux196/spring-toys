package etc;

public class ComputerB {
    private MainBoard mainBoard;
    private CPU cpu;
    private Memory memory;

    // aggregation
    public ComputerB(MainBoard mainBoard, CPU cpu, Memory memory) {
        this.mainBoard = mainBoard;
        this.cpu = cpu;
        this.memory = memory;
    }
}