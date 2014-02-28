package ch.digitalmeat.generation.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.digitalmeat.generation.level.data.Level;

public abstract class Processor {
   private Random r;
   private List<Processor> children;

   public Processor() {
      children = new ArrayList<Processor>();
   }

   public void setRandom(Random random) {
      this.r = random;
   }

   public void process(Level level) {
      if (r == null) {
         r = new Random();
      }
      processImplementation(level);
      for (Processor processor : children) {
         processor.process(level);
      }
   }

   public abstract void processImplementation(Level level);

   public class Container extends Processor {

      @Override
      public void processImplementation(Level level) {

      }

   }
}
