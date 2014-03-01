package ch.digitalmeat.generation.level.room;

import java.util.TreeSet;

import ch.digitalmeat.generation.data.IntVector2;

public class RoomConfig {
   public String hash;
   public int width;
   public int height;

   public TreeSet<IntVector2> doorways = new TreeSet<IntVector2>();

   public RoomConfig size(int width, int height) {
      this.width = width;
      this.height = height;
      return this;
   }

   public RoomConfig door(int x, int y) {
      doorways.add(new IntVector2(x, y));
      return this;
   }

   public void updateHash() {
      StringBuilder builder = new StringBuilder();
      builder.append(String.format("%d:%d|", width, height));
      for (IntVector2 door : doorways) {
         builder.append(String.format(String.format("%d;%d|", door.x, door.y)));
      }
      hash = builder.toString();
   }

   public boolean hasDoorway(int x, int y) {
      for (IntVector2 p : doorways) {
         if (p.x == x && p.y == y) {
            return true;
         }
      }
      return false;
   }
}
