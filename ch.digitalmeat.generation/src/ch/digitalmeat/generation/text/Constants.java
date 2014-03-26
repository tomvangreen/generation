package ch.digitalmeat.generation.text;

import java.util.Random;

import ch.digitalmeat.generation.data.WeightedList;

public class Constants extends Node {

   private Random random;
   private WeightedList<String> constants = new WeightedList<String>();

   public Constants add(String constant) {
      return add(constant, 1);
   }

   public Constants add(String constant, int weight) {
      constants.add(constant, weight);
      return this;
   }

   @Override
   public void generate(StringBuilder builder) {
      if (random == null) {
         random = new Random();
      }
      builder.append(constants.get(random));
   }

   @Override
   public void setRandom(Random random) {
      this.random = random;
   }

}
