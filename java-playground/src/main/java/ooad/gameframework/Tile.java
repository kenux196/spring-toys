package ooad.gameframework;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private final int x;
    private final int y;
    private final List<Unit> units = new ArrayList<>();

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnits() {
        units.clear();
    }

    public List<Unit> getUnits() {
        return units;
    }
}
