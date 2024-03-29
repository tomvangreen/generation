package ch.digitalmeat.generation.test.level.room;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import ch.digitalmeat.generation.level.Generator;
import ch.digitalmeat.generation.level.data.Grid;
import ch.digitalmeat.generation.level.room.Randomizer;
import ch.digitalmeat.generation.level.room.RoomCellFactory;
import ch.digitalmeat.generation.level.room.RoomConfig;
import ch.digitalmeat.generation.level.room.RoomPlotter;
import ch.digitalmeat.generation.level.room.TemplateFiller;

public class RoomTest {
   RoomPlotter plotter = new RoomPlotter();

   private void exportGrid(Grid grid, String file) {
      plotter.plot(grid);
      try {
         BufferedImage image = plotter.getImage();
         // Flip the image vertically
         AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
         tx.translate(0, -image.getHeight(null));
         AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
         image = op.filter(image, null);
         ImageIO.write(image, "png", new File(file));
      } catch (Exception ex) {
         ex.printStackTrace();
         throw new RuntimeException("Failed to wite image.", ex);
      }
   }

   @Test
   public void randomRooms() {
      Generator generator = new Generator(new RoomCellFactory(), new Randomizer());
      exportGrid(generator.generate(8, 8), "random-rooms.png");
   }

   @Test
   public void templateRooms4x4() {

      TemplateFiller filler = new TemplateFiller();
      filler.addTemplate((new RoomConfig()).size(1, 1));
      filler.addTemplate((new RoomConfig()).size(1, 2));
      filler.addTemplate((new RoomConfig()).size(2, 2));
      filler.addTemplate((new RoomConfig()).size(2, 1));
      filler.addTemplate((new RoomConfig()).size(4, 1));
      filler.addTemplate((new RoomConfig()).size(1, 4));

      Generator generator = new Generator(new RoomCellFactory(), filler);
      exportGrid(generator.generate(4, 4), "template-rooms-4x4.png");
   }

   @Test
   public void templateRooms16x16() {

      TemplateFiller filler = new TemplateFiller();
      filler.addTemplate((new RoomConfig()).size(1, 2));
      filler.addTemplate((new RoomConfig()).size(2, 2));
      filler.addTemplate((new RoomConfig()).size(2, 1));
      filler.addTemplate((new RoomConfig()).size(4, 2));
      filler.addTemplate((new RoomConfig()).size(2, 4));
      filler.addTemplate((new RoomConfig()).size(4, 4));
      filler.addTemplate((new RoomConfig()).size(4, 6));
      filler.addTemplate((new RoomConfig()).size(4, 8));
      filler.addTemplate((new RoomConfig()).size(8, 4));

      Generator generator = new Generator(new RoomCellFactory(), filler);
      exportGrid(generator.generate(16, 16), "template-rooms-16x16.png");
   }

   @Test
   public void templateRooms64x48() {

      TemplateFiller filler = new TemplateFiller();

      filler.addTemplate((new RoomConfig()).size(4, 2));
      filler.addTemplate((new RoomConfig()).size(2, 4));
      filler.addTemplate((new RoomConfig()).size(4, 4));
      filler.addTemplate((new RoomConfig()).size(4, 6));
      filler.addTemplate((new RoomConfig()).size(4, 8));
      filler.addTemplate((new RoomConfig()).size(8, 4));
      filler.addTemplate((new RoomConfig()).size(10, 4));
      filler.addTemplate((new RoomConfig()).size(10, 6));

      Generator generator = new Generator(new RoomCellFactory(), filler);
      exportGrid(generator.generate(96, 48), "template-rooms-64x48.png");
   }
}
