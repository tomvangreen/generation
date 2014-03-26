package ch.digitalmeat.generation.text;

import java.util.Random;

public class Shuffler extends TextProcessor {
   private StringBuilder builder = new StringBuilder();

   public Shuffler() {
      this(null);
   }

   public Shuffler(Node child) {
      this.child = child;
   }

   @Override
   protected String process(String input, Random random) {
      for (int index = 0; index < input.length(); index++) {
         String character = input.substring(index, index + 1);
         if (random.nextBoolean()) {
            builder.append(character.toLowerCase());
         } else {
            builder.append(character.toUpperCase());
         }
      }
      String result = builder.toString();
      builder.setLength(0);
      return result;
   }
}
