package minesweeper;

class Matrix {
     private Cell[][] matrix;

     Matrix(Cell defCell) {
         matrix = new Cell[Ranges.getSize().x][Ranges.getSize().y];
         for (Coordinates coord : Ranges.getAllCoords()) {
             matrix[coord.x][coord.y] = defCell;
         }
     }

     Cell getter (Coordinates coord) {
         if (Ranges.inRange(coord))
            return matrix[coord.x][coord.y];
         return null;
     }

     void setter (Coordinates coord, Cell cell) {
         if (Ranges.inRange(coord))
            matrix[coord.x][coord.y] = cell;
     }
}
