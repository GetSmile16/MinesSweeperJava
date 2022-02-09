package minesweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedCells;

    void start() {
        flagMap = new Matrix(Cell.CLOSED);
        countOfClosedCells = Ranges.getSize().x * Ranges.getSize().y;

    }

    int countOfClosedCells() {
        return countOfClosedCells;
    }

    Cell getter(Coordinates coord) {
        return flagMap.getter(coord);
    }

    void openCell(Coordinates coord) {
        flagMap.setter(coord, Cell.OPENED);
        countOfClosedCells--;
    }

    void flagCell(Coordinates coord) {
        flagMap.setter(coord, Cell.FLAGED);
    }

    void bombCell(Coordinates coord) {
        flagMap.setter(coord, Cell.BOMBED);
    }

    void closedBombCell(Coordinates coord) {
        if (flagMap.getter(coord) == Cell.CLOSED)
            flagMap.setter(coord, Cell.BOMB);
    }

    void noBombCellToFlagCell(Coordinates coord) {
        if (flagMap.getter(coord) == Cell.FLAGED)
            flagMap.setter(coord, Cell.NOBOMB);
    }

    int countOfFlagCellsAround(Coordinates coord) {
        int countBomb = 0;
        for (Coordinates aroundCoord : Ranges.getCoordsAround(coord)) {
            if (flagMap.getter(aroundCoord) == Cell.FLAGED)
                countBomb++;
        }
        return countBomb;
    }
}
