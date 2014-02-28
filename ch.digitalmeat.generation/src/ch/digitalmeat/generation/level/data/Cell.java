package ch.digitalmeat.generation.level.data;

public class Cell {
   public Cell[] neighbours;

   public Cell() {
      neighbours = new Cell[Direction8.values().length];
   }

}
