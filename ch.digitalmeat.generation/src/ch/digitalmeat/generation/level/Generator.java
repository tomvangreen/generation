package ch.digitalmeat.generation.level;

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
      Grid level = new Grid(factory);
      level.init(width, height);
      if (processor != null) {
         processor.process(level);
      }
      return level;
   }
}
