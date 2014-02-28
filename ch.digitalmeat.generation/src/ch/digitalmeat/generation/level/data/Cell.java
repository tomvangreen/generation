package ch.digitalmeat.generation.level.data;

import ch.digitalmeat.generation.data.Properties;

public class Cell {
   public Cell[] neighbours;
   public Properties properties;

   public Cell() {
      neighbours = new Cell[Direction8.values().length];
      properties = new Properties();
   }
}
