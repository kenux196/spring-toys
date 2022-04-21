package ooad.gameframework.board;

import ooad.gameframework.Tile;
import ooad.gameframework.Unit;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int width;
    private int height;

    private List<Tile> tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        initialize(width, height);
    }

    private void initialize(int width, int height) {
        tiles = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(i, j);
                tiles.add(tile);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        return tiles.stream()
                .filter(tile -> tile.getX() == x)
                .filter(tile -> tile.getY() == y)
                .findAny().orElse(null);
    }

    public void addUnit(Unit unit, int x, int y) {
        Tile tile = getTile(x, y);
        tile.addUnit(unit);
    }

    public void removeUnit(int x, int y) {
        Tile tile = getTile(x, y);
        tile.removeUnits();
    }

    public List<Unit> getUnits(int x, int y) {
        return getTile(x, y).getUnits();
    }
}
