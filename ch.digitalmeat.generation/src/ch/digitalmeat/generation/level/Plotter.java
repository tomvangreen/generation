package ch.digitalmeat.generation.level;

import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.Grid;

public abstract class Plotter {
   public void plot(Grid level) {
      init(level);

      for (int y = 0; y < level.height(); y++) {
         for (int x = 0; x < level.width(); x++) {
            Cell cell = level.get(x, y);
            plot(cell, x, y);
         }
      }
   }

   protected abstract void init(Grid level);

   protected abstract void plot(Cell cell, int x, int y);
}
