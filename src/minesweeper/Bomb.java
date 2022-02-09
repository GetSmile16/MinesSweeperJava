package minesweeper;

class Bomb {
    private Matrix bombMap;

    private int numberBomb;

    Bomb (int numberBomb) {
        this.numberBomb = numberBomb;
        fixBombNumber();
    }

    void start() {
        bombMap = new Matrix(Cell.ZERO);
        for (int i = 0; i < numberBomb; i++) {
            placeBomb();
        }
    }

    Cell getter (Coordinates coord) {
        return bombMap.getter(coord);
    }

    public int getNumberBomb() {
        return numberBomb;
    }

    private void placeBomb() {
        while (true) {
            Coordinates coord = Ranges.getRandomCoord();
            if (Cell.BOMB == bombMap.getter(coord))
                continue;
            bombMap.setter(coord, Cell.BOMB);
            incrementNumbersAroundBomb(coord);
            break;
        }
    }

    private void fixBombNumber() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (numberBomb > maxBombs) {
            numberBomb = maxBombs;
        }
    }
    private void incrementNumbersAroundBomb(Coordinates coord) {
        for (Coordinates aroundCoord : Ranges.getCoordsAround(coord)) {
            if (Cell.BOMB != bombMap.getter(aroundCoord))
                bombMap.setter(aroundCoord, bombMap.getter(aroundCoord).getNextNumberCell());
        }
    }
}
