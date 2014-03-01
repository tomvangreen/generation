package ch.digitalmeat.generation.level.room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.digitalmeat.generation.data.IntVector2;
import ch.digitalmeat.generation.data.List2D;
import ch.digitalmeat.generation.level.Processor;
import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.Direction8;
import ch.digitalmeat.generation.level.data.Grid;

public class TemplateFiller extends Processor {
   private List<RoomConfig> templates = new ArrayList<RoomConfig>();
   private List<RoomConfig> temp = new ArrayList<RoomConfig>();
   private List<IntVector2> candidates = new ArrayList<IntVector2>();
   private List<IntVector2> candidatesTemp = new ArrayList<IntVector2>();
   private List<IntVector2> coordinates = new ArrayList<IntVector2>();
   private int placedRooms = 0;

   public TemplateFiller addTemplate(RoomConfig room) {
      templates.add(room);
      return this;
   }

   @Override
   protected boolean processImplementation(Grid level) {
      candidates.clear();
      List2D<Cell> cells = level.cells();
      int width = cells.width();
      int height = cells.height();
      for (int y = 0; y < height; y++) {
         for (int x = 0; x < width; x++) {
            Cell cell = cells.get(x, y);
            if (!cell.get(RoomCellFactory.USED, false)) {
               candidates.add(new IntVector2(x, y));
            }

         }
      }
      while (placementIteration(cells)) {
      }
      return true;
   }

   protected boolean placementIteration(List2D<Cell> cells) {
      temp.clear();
      temp.addAll(templates);
      Collections.shuffle(temp, r);

      while (temp.size() > 0) {
         RoomConfig config = temp.remove(0);
         candidatesTemp.clear();
         candidatesTemp.addAll(candidates);
         Collections.shuffle(candidatesTemp, r);
         while (candidatesTemp.size() > 0) {
            IntVector2 candidate = candidatesTemp.remove(0);
            if (canBePlaced(cells, config, candidate)) {
               System.out.println("Placing room");
               place(cells, config, candidate);
               placedRooms++;
               return true;
            }
         }

      }

      return false;
   }

   protected void place(List2D<Cell> cells, RoomConfig cfg, IntVector2 pos) {
      int w = cfg.width;
      int h = cfg.height;
      for (int y = 0; y < h; y++) {
         for (int x = 0; x < w; x++) {
            Cell cell = cells.get(pos.x + x, pos.y + y);
            cell.put(RoomCellFactory.USED, true);
            if (x == 0) {
               cell.put(Direction8.West.name, RoomCellFactory.WALL);
            }
            if (x == w - 1) {
               cell.put(Direction8.East.name, RoomCellFactory.WALL);
            }
            if (y == 0) {
               cell.put(Direction8.South.name, RoomCellFactory.WALL);
            }
            if (y == h - 1) {
               cell.put(Direction8.North.name, RoomCellFactory.WALL);
            }
         }
      }
   }

   /**
    * Checks if a room can be placed at some given coordinates.
    * 
    * @param cells
    * @param config
    * @param place
    * @return
    */
   protected boolean canBePlaced(List2D<Cell> cells, RoomConfig config, IntVector2 place) {
      List<IntVector2> coords = getRoomCoordinates(config, place);
      for (IntVector2 coord : coords) {
         Cell cell = cells.get(coord.x, coord.y);
         // If cell is outside of the grid, it cannot be placed
         if (cell == null) {
            return false;
         }
         if (cell.get(RoomCellFactory.USED, false)) {
            return false;
         }
      }
      return true;
   }

   protected List<IntVector2> getRoomCoordinates(RoomConfig config, IntVector2 initialPosition) {
      coordinates.clear();

      for (int y = 0; y < config.height; y++) {
         for (int x = 0; x < config.width; x++) {
            coordinates.add(new IntVector2(initialPosition.x + x, initialPosition.y + y));
         }
      }

      return coordinates;
   }
}