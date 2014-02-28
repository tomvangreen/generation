package ch.digitalmeat.generation.level.data;

import ch.digitalmeat.generation.data.List2D;

public class CellGrid {
   private boolean updateInstantly;
   private boolean dirty;

   public List2D<Cell> cells;

   public CellGrid() {
      cells = new List2D<Cell>();
   }

   public void connectCell(int x, int y) {
      Cell cell = cells.get(x, y);
      if (cell == null) {
         return;
      }
      for (Direction8 dir : Direction8.values()) {
         Cell neighbour = cells.get(x + dir.xOffset, y + dir.yOffset);
         if (neighbour != null) {
            cell.neighbours[dir.value] = neighbour;
            neighbour.neighbours[dir.getOpposite().value] = cell;
         }
      }
   }

   public void clean() {
      if (!dirty) {
         return;
      }
      for (int y = 0; y < cells.height(); y++) {
         for (int x = 0; x < cells.width(); x++) {
            connectCell(x, y);
         }
      }
   }
}
