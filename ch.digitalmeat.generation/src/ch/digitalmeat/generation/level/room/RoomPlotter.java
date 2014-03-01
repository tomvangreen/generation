package ch.digitalmeat.generation.level.room;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ch.digitalmeat.generation.level.AwtPlotter;
import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.Direction8;

public class RoomPlotter extends AwtPlotter {

   public int tileWidth = 64;
   public int tileHeight = 64;

   public int wallWidth = 8;
   public int doorwayWidth = 32;

   @Override
   public int tileWidth() {
      return tileWidth;
   }

   @Override
   public int tileHeight() {
      return tileHeight;
   }

   @Override
   protected void plot(Cell cell, int x, int y, Graphics g, Graphics2D g2) {
      if (cell == null) {
         plotUnusedCell(x, y, g2);
      } else {
         boolean used = cell.<Boolean> get(RoomCellFactory.USED, false);
         if (used) {
            plotUsed(cell, x, y, g2);
         } else {
            plotUnusedCell(x, y, g2);
         }
      }
      plotGrid(x, y, g2);
   }

   private void plotUsed(Cell cell, int x, int y, Graphics2D g2) {
      if (cell.get(RoomCellFactory.CONNECTED, false)) {
         g2.setColor(Color.white);
      } else {
         g2.setColor(Color.gray);
      }
      g2.fillRect(x, y, tileWidth, tileHeight);
      g2.setColor(Color.black);
      handleDirection(cell, x, y, g2, Direction8.North);
      handleDirection(cell, x, y, g2, Direction8.East);
      handleDirection(cell, x, y, g2, Direction8.South);
      handleDirection(cell, x, y, g2, Direction8.West);

   }

   private void handleDirection(Cell cell, int x, int y, Graphics2D g2, Direction8 direction) {
      int action = cell.get(direction.name, RoomCellFactory.EMPTY);
      if (action == RoomCellFactory.DOOR) {
         plotDoor(x, y, direction, g2);
      } else if (action == RoomCellFactory.WALL) {
         plotWall(x, y, direction, g2);
      }
   }

   private void plotGrid(int x, int y, Graphics2D g2) {
      g2.setColor(Color.red);
      g2.drawRect(x, y, x + tileWidth, y + tileHeight);
   }

   private void plotUnusedCell(int x, int y, Graphics2D g2) {
      g2.setColor(Color.black);
      g2.fillRect(x, y, tileWidth, tileHeight);
   }

   private void plotWall(int x, int y, Direction8 dir, Graphics2D g2) {
      // System.out.println(String.format("Wall: %s", dir.name));
      g2.setColor(Color.black);
      int x1 = x;
      int y1 = y;
      int w = tileWidth;
      int h = tileHeight;
      if (dir == Direction8.North || dir == Direction8.South) {
         h = wallWidth;
         if (dir == Direction8.North) {
            y1 += tileHeight - wallWidth;
         }
      } else {
         w = wallWidth;
         if (dir == Direction8.East) {
            x1 += tileWidth - wallWidth;
         }
      }
      g2.fillRect(x1, y1, w, h);
   }

   private void plotDoor(int x, int y, Direction8 dir, Graphics2D g2) {
      // System.out.println(String.format("Door: %s", dir.name));
      g2.setColor(Color.black);
      int x1 = x;
      int y1 = y;
      int x2 = x;
      int y2 = y;
      int w = tileWidth;
      int h = tileHeight;
      if (dir == Direction8.North || dir == Direction8.South) {
         w = (w - doorwayWidth) / 2;
         h = wallWidth;
         x2 += w + doorwayWidth;
         if (dir == Direction8.North) {
            y1 += tileHeight - wallWidth;
            y2 = y1;

         }
      } else {
         h = (h - doorwayWidth) / 2;
         w = wallWidth;
         y2 += h + doorwayWidth;
         if (dir == Direction8.East) {
            x1 += tileWidth - wallWidth;
            x2 = x1;
         }
      }
      g2.fillRect(x1, normalizeY(y1, h), w, h);
      g2.fillRect(x2, normalizeY(y2, h), w, h);
   }

   private int normalizeY(int y, int h) {
      return y;
      // return y;
   }
}
