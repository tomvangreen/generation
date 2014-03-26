package ch.digitalmeat.generation.text;

import java.util.Random;

public abstract class TextProcessor extends Node {
   private Random random;
   public Node child;

   protected abstract String process(String input, Random random);

   @Override
   public void setRandom(Random random) {
      this.random = random;
      if (child != null) {
         child.setRandom(random);
      }
   }

   @Override
   public void generate(StringBuilder builder) {
      String result = child.generate();
      builder.append(process(result, random));
   }

}
