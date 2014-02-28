package ch.digitalmeat.generation.level.data;

import ch.digitalmeat.generation.data.List2D;

public class Level {
   private boolean initialized;
   private CellFactory factory;
   private List2D<Cell> cells;

   public Level() {
      this(new CellFactory.DefaultCellFactory());
   }

   public Level(CellFactory factory) {
      this.factory = factory;
      this.cells = new List2D<Cell>();
   }

   public void init(int width, int height) {
      if (initialized) {
         deinit();
      }
      cells.initialize(width, height);
      int length = cells.length();
      for (int index = 0; index < length; index++) {
         cells.put(index, factory.spawn());
      }
      Direction8[] directions = Direction8.values();
      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {
            Cell cell = cells.get(x, y);
            for (Direction8 direction : directions) {
               Cell n = cells.get(x + direction.xOffset, y + direction.yOffset);
               cell.neighbours[direction.value] = n;
               if (n != null) {
                  n.neighbours[direction.getOpposite().value] = cell;
               }
            }
         }
      }
      initialized = true;
   }

   public void deinit() {
      if (!initialized) {
         return;
      }
      cells.deinit();
      initialized = false;
   }

}
