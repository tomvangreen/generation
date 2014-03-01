package ch.digitalmeat.generation.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.digitalmeat.generation.level.data.Grid;

public abstract class Processor {
   protected Random r;
   private List<Processor> children;

   public Processor() {
      children = new ArrayList<Processor>();
   }

   public void setRandom(Random random) {
      this.r = random;
   }

   public boolean process(Grid level) {
      if (r == null) {
         r = new Random();
      }
      boolean result = processImplementation(level);
      if (result) {
         for (Processor processor : children) {
            processor.setRandom(r);
            result = processor.process(level);
            if (!result) {
               return false;
            }
         }
      }
      return result;
   }

   protected abstract boolean processImplementation(Grid level);

   public class Container extends Processor {

      @Override
      protected boolean processImplementation(Grid level) {
         return true;
      }

   }
}
