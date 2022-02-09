package minesweeper;

public enum Cell {
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB, //бомба
    OPENED, //открытая ячейка
    CLOSED, //закрытая ячейка
    FLAGED, //помеченная флагом ячейка
    BOMBED, //взорванная бомба
    NOBOMB; //нету бомбы

    public Object image;

    Cell getNextNumberCell() {
        return Cell.values()[this.ordinal() + 1];
    }

    int getNumber() {
        return this.ordinal();
    }
}
