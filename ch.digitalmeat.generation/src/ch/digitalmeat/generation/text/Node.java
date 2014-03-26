package ch.digitalmeat.generation.text;

import java.util.Random;

public abstract class Node {

   public abstract void setRandom(Random random);

   public abstract void generate(StringBuilder builder);

   public String generate() {
      StringBuilder builder = new StringBuilder();
      generate(builder);
      return builder.toString();
   }

}
