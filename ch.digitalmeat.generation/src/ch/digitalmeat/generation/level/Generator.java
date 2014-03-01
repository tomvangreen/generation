package ch.digitalmeat.generation.level;

import java.util.Random;

import ch.digitalmeat.generation.level.data.CellFactory;
import ch.digitalmeat.generation.level.data.Grid;

public class Generator {
   final public CellFactory factory;
   final public Processor processor;

   public Generator(CellFactory factory, Processor processor) {
      this.factory = factory;
      this.processor = processor;
   }

   public Grid generate(int width, int height) {
      return generate(width, height, null);
   }

   public Grid generate(int width, int height, String seed) {

      Grid level = new Grid(factory);
      level.init(width, height);
      if (processor != null) {
         Random random = seed == null ? new Random() : new Random(seed.hashCode());
         processor.setRandom(random);
         processor.process(level);
      }
      return level;
   }
}
