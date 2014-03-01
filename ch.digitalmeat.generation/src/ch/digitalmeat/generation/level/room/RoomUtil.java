package ch.digitalmeat.generation.level.room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import ch.digitalmeat.generation.data.IntVector2;
import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.Direction8;

public class RoomUtil {
   private List<Cell> openCells = new ArrayList<Cell>();
   private List<Cell> closedCells = new ArrayList<Cell>();

   public List<Cell> getReachableCells(Cell cell) {
      return getReachableCells(cell, new ArrayList<Cell>());
   }

   public List<Cell> getReachableCells(Cell cell, List<Cell> list) {
      if (cell == null) {
         return null;
      }
      openCells.clear();
      closedCells.clear();

      openCells.addAll(list);

      EnumSet<Direction8> directions = Direction8.getCardinalDirecations();
      openCells.add(cell);
      while (openCells.size() > 0) {
         Cell open = openCells.remove(0);
         closedCells.add(open);
         for (Direction8 direction : directions) {
            if (canPass(open, direction)) {
               Cell neighbour = open.neighbours[direction.value];
               if (canBeHandled(neighbour) && canPass(neighbour, direction.getOpposite())) {
                  openCells.add(neighbour);
               }
            }
         }
      }
      list.addAll(closedCells);
      return list;
   }

   private boolean canBeHandled(Cell neighbour) {
      return neighbour != null && !openCells.contains(neighbour) && !closedCells.contains(neighbour);
   }

   private boolean canPass(Cell open, Direction8 direction) {
      return open.get(direction.name, RoomCellFactory.EMPTY) != RoomCellFactory.WALL;
   }

   public List<Cell> findNeighbouringTiles(List<Cell> input) {
      List<Cell> output = new ArrayList<Cell>();
      EnumSet<Direction8> directions = Direction8.getCardinalDirecations();
      for (Cell cell : input) {
         boolean candidate = false;
         for (Direction8 direction : directions) {
            if (hasPossibleBreakthrough(cell, direction, true)) {
               candidate = true;
            }
         }
         if (candidate) {
            output.add(cell);
         }
      }
      return output;
   }

   public boolean hasPossibleBreakthrough(Cell cell, Direction8 direction, boolean requireUnconnected) {
      Cell neighbour = cell.neighbours[direction.value];
      if (neighbour == null) {
         return false;
      }
      if (requireUnconnected && neighbour.get(RoomCellFactory.CONNECTED, false)) {
         return false;
      }
      return !(canPass(cell, direction) && canPass(neighbour, direction.getOpposite()));
   }

   public void randomBreakThrough(Random random, Cell cell, boolean requireUnconnected) {
      List<Direction8> list = new ArrayList<Direction8>();
      list.addAll(Direction8.getCardinalDirecations());
      Collections.shuffle(list, random);
      while (list.size() > 0) {
         Direction8 dir = list.remove(0);
         if (hasPossibleBreakthrough(cell, dir, requireUnconnected)) {
            breakThrough(cell, dir);
            return;
         }
      }
   }

   public void breakThrough(Cell cell, Direction8 dir) {
      cell.put(dir.name, RoomCellFactory.DOOR);
      Cell neighbour = cell.neighbours[dir.value];
      neighbour.put(dir.getOpposite().name, RoomCellFactory.DOOR);
   }

   public class RoomCoords {
      public IntVector2 position;
      public Cell cell;

      public RoomCoords(int x, int y, Cell cell) {
         this(new IntVector2(x, y), cell);
      }

      public RoomCoords(IntVector2 position, Cell cell) {
         this.position = position;
         this.cell = cell;
      }
   }
}
