package ch.digitalmeat.generation.level.room;

import static ch.digitalmeat.generation.level.data.Direction8.East;
import static ch.digitalmeat.generation.level.data.Direction8.North;
import static ch.digitalmeat.generation.level.data.Direction8.South;
import static ch.digitalmeat.generation.level.data.Direction8.West;
import static ch.digitalmeat.generation.level.room.RoomCellFactory.DOOR;
import static ch.digitalmeat.generation.level.room.RoomCellFactory.EMPTY;
import static ch.digitalmeat.generation.level.room.RoomCellFactory.WALL;
import ch.digitalmeat.generation.data.List2D;
import ch.digitalmeat.generation.level.Processor;
import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.Grid;

public class Randomizer extends Processor {

   @Override
   protected boolean processImplementation(Grid level) {
      List2D<Cell> cells = level.cells();
      int length = cells.length();
      for (int index = 0; index < length; index++) {
         Cell cell = cells.get(index);
         randomize(cell);
      }
      return true;
   }

   private void randomize(Cell cell) {
      if (r.nextInt(4) == 0) {
         cell.put(RoomCellFactory.USED, false);
      } else {
         cell.put(RoomCellFactory.USED, true);

         cell.put(North.name, randomWall());
         cell.put(East.name, randomWall());
         cell.put(South.name, randomWall());
         cell.put(West.name, randomWall());
         // cell.put(North.name, DOOR);
         // cell.put(East.name, DOOR);
         // cell.put(South.name, DOOR);
         // cell.put(West.name, DOOR);
      }
   }

   private int randomWall() {
      switch (r.nextInt(3)) {
      default:
      case 0:
         return EMPTY;
      case 1:
         return WALL;
      case 2:
         return DOOR;
      }
   }

}
