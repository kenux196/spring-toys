package etc;

public class ComputerA {
    private MainBoard mainBoard;
    private CPU cpu;
    private Memory memory;

    // composition
    private ComputerA() {
        this.mainBoard = new MainBoard();
        this.cpu = new CPU();
        this.memory = new Memory();
    }
}