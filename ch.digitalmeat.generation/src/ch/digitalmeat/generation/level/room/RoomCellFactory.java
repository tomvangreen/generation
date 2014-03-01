package ch.digitalmeat.generation.level.room;

import static ch.digitalmeat.generation.level.data.Direction8.East;
import static ch.digitalmeat.generation.level.data.Direction8.North;
import static ch.digitalmeat.generation.level.data.Direction8.South;
import static ch.digitalmeat.generation.level.data.Direction8.West;
import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.CellFactory;

public class RoomCellFactory implements CellFactory {

   public static final String USED = "used";
   public static final String CONNECTED = "connected";
   public final static int EMPTY = 0;
   public final static int WALL = 1;
   public final static int DOOR = 2;

   @Override
   public Cell spawn() {
      Cell cell = new Cell();

      cell.put(USED, false);
      cell.put(CONNECTED, false);

      cell.put(North.name, EMPTY);
      cell.put(East.name, EMPTY);
      cell.put(South.name, EMPTY);
      cell.put(West.name, EMPTY);

      return cell;
   }
}
