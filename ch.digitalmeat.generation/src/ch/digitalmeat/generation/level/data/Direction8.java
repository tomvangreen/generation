package ch.digitalmeat.generation.level.data;

import java.util.EnumSet;

public enum Direction8 {
// @formatter:off
   North("N", "North", 0, 0, 1)
   , NorthEast("NE", "Northeast", 1, 1, 1)
   , East("E", "East", 2, 1, 0)
   , SouthEast("SE", "Southeast", 3, 1, -1)
   , South("S", "South", 4, 0, -1)
   , SouthWest("SW", "Southwest", 5, -1, -1)
   , West("W", "West", 6, -1, 0)
   , NorthWest("NW", "Northwest", 7, -1, 1);
// @formatter:on  

   public final int value;
   public final int xOffset;
   public final int yOffset;
   public final String name;
   public final String shortName;

   Direction8(String shortName, String name, int value, int xOffset, int yOffset) {
      this.value = value;
      this.xOffset = xOffset;
      this.yOffset = yOffset;
      this.shortName = shortName;
      this.name = name;
   }

   public Direction8 getOpposite() {
      int length = values().length;
      return values()[(value + 4) % length];
   }

   public Direction8 cw(int steps) {
      return values()[normalize(value + steps)];
   }

   public Direction8 ccw(int steps) {
      return values()[normalize(value - steps)];
   }

   private int normalize(int value) {
      int length = values().length;
      while (value < 0) {
         value += length;
      }
      while (value >= length) {
         value -= length;
      }
      return value;
   }

   public static EnumSet<Direction8> getCardinalDirecations() {
      return EnumSet.of(Direction8.North, Direction8.East, Direction8.South, Direction8.West);
   }
}
