package ch.digitalmeat.generation.level.data;

import ch.digitalmeat.generation.data.Properties;

public class Cell {
   public Cell[] neighbours;
   public Properties properties;

   public Cell() {
      neighbours = new Cell[Direction8.values().length];
      properties = new Properties();
   }

   public Cell put(String key, Object value) {
      properties.put(key, value);
      return this;
   }

   public <T> T get(String key) {
      return properties.<T> get(key);
   }

   public <T> T get(String key, T defaultValue) {
      return properties.<T> get(key, defaultValue);
   }
}
