package minesweeper;

public class Game {
    private final Bomb bomb;
    private final Flag flag;
    private GameState state;

    public Game (int cols, int rows, int bombs) {
        Ranges.setSize(new Coordinates(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAY;
    }

    public Cell getCell (Coordinates coord) {
        if (flag.getter(coord) == Cell.OPENED)
            return bomb.getter(coord);
        return flag.getter(coord);
    }

    public GameState getState() {
        return state;
    }

    public void pressedLeftButton(Coordinates coord) {
        if (gameOver())
            return;
        recursiveOpenCell(coord);
        checkWin();
    }

    private void checkWin() {
        if(state == GameState.PLAY)
            if (flag.countOfClosedCells() == bomb.getNumberBomb())
                state = GameState.WIN;
    }

    private void recursiveOpenCell(Coordinates coord) {
        switch (flag.getter(coord)) {
            case OPENED:
                //openCellsAroundNumber(coord);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.getter(coord)) {
                    case ZERO:
                        openCellsAround(coord);
                        return;
                    case BOMB:
                        openBombs(coord);
                        return;
                    default:
                        flag.openCell(coord);
                        return;
                }
        }
    }

    private void openCellsAroundNumber(Coordinates coord) {
        if (bomb.getter(coord) != Cell.BOMB)
            if (flag.countOfFlagCellsAround(coord) == bomb.getter(coord).getNumber());
                for (Coordinates aroundCoord : Ranges.getCoordsAround(coord)) {
                    if (flag.getter(aroundCoord) == Cell.CLOSED)
                        recursiveOpenCell(aroundCoord);
                }
    }

    private void openBombs(Coordinates bombCoord) {
        state = GameState.LOSE;
        flag.bombCell(bombCoord);
        for (Coordinates coord : Ranges.getAllCoords()) {
            if (bomb.getter(coord) == Cell.BOMB)
                flag.closedBombCell(coord);
            else
                flag.noBombCellToFlagCell(coord);
        }
    }

    private void openCellsAround(Coordinates coord) {
        flag.openCell(coord);
        for (Coordinates aroundCoord : Ranges.getCoordsAround(coord)) {
            recursiveOpenCell(aroundCoord);
        }
    }

    public void pressedRightButton(Coordinates coord) {
        if (gameOver())
            return;
        flag.flagCell(coord);
    }

    private boolean gameOver() {
        if (state == GameState.PLAY)
            return false;
        start();
        return true;
    }
}
