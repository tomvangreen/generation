package ch.digitalmeat.generation.level.data;

public enum Direction8 {
   North(0, 0, 1), NorthEast(1, 1, 1), East(2, 1, 0), SouthEast(3, 1, -1), South(4, 0, -1), SouthWest(5, -1, -1), West(6, -1, 0), NorthWest(7, -1, 1);

   public final int value;
   public final int xOffset;
   public final int yOffset;

   Direction8(int value, int xOffset, int yOffset) {
      this.value = value;
      this.xOffset = xOffset;
      this.yOffset = yOffset;
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
}
