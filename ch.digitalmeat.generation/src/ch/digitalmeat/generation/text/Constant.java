package ch.digitalmeat.generation.text;

import java.util.Random;

public class Constant extends Node {
   public String constant;

   public Constant() {
      this("");
   }

   public Constant(String constant) {
      this.constant = constant;
   }

   @Override
   public void setRandom(Random random) {

   }

   @Override
   public void generate(StringBuilder builder) {
      if (constant != null) {
         builder.append(constant);
      }
   }

}
