package ch.digitalmeat.generation.level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ch.digitalmeat.generation.data.IntVector2;
import ch.digitalmeat.generation.level.data.Cell;
import ch.digitalmeat.generation.level.data.Grid;

public abstract class AwtPlotter extends Plotter {
   private BufferedImage image;
   private Graphics g;
   private Graphics2D g2;
   private int width;
   private int height;
   protected IntVector2 bounds = new IntVector2();

   public abstract int tileWidth();

   public abstract int tileHeight();

   protected abstract void plot(Cell cell, int x, int y, Graphics g, Graphics2D g2);

   public BufferedImage getImage() {
      return image;
   }

   @Override
   protected void init(Grid level) {
      width = tileWidth();
      height = tileHeight();
      bounds.x = level.width() * width;
      bounds.y = level.height() * height;
      image = new BufferedImage(bounds.x, bounds.y, BufferedImage.TYPE_4BYTE_ABGR_PRE);
      g = image.getGraphics();
      g2 = (Graphics2D) g;
   }

   @Override
   protected void plot(Cell cell, int x, int y) {
      plot(cell, x * width, y * width, g, g2);
   }

}
