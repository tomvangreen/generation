package ch.digitalmeat.generation.data;

public class IntVector2 {
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

}
