package ch.digitalmeat.generation.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NodeList extends Node {
   private List<Node> nodes = new ArrayList<Node>();

   public NodeList add(Node node) {
      nodes.add(node);
      return this;
   }

   @Override
   public void generate(StringBuilder builder) {
      for (Node node : nodes) {
         node.generate(builder);
      }
   }

   @Override
   public void setRandom(Random random) {
      for (Node node : nodes) {
         node.setRandom(random);
      }

   }

}
