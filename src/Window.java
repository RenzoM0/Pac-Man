public class Window {

    private int rowCount, columnCount, tileSize;

    // Map size = tileSize x row/column
    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;

    public Window(int rows, int columns, int tiles) {
        this.rowCount = rows;
        this.columnCount = columns;
        this.tileSize = tiles;

        WINDOW_WIDTH = this.tileSize * this.columnCount;
        WINDOW_HEIGHT = this.tileSize * this.rowCount;
    }

    public int getWINDOW_WIDTH(){
        return WINDOW_WIDTH;
    }

    public int getWINDOW_HEIGHT(){
        return WINDOW_HEIGHT;
    }

    public int getRowCount(){
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getTileSize() {
        return tileSize;
    }
}
