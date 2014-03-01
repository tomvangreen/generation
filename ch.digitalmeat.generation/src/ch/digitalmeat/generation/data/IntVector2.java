package ch.digitalmeat.generation.data;

public class IntVector2 implements Comparable<IntVector2> {
   public int x;
   public int y;

   public IntVector2() {
      this(0, 0);
   }

   public IntVector2(int x, int y) {
      set(x, y);
   }

   public void set(int x, int y) {
      this.x = x;
      this.y = y;
   }

   @Override
   public int compareTo(IntVector2 other) {
      if (other == null) {
         return 1;
      }
      int difference = y - other.y;
      return difference == 0 ? x - other.x : difference;
   }

}
