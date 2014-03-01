package ch.digitalmeat.generation.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedList<T> {
   private int totalWeight;
   private List<T> items;
   private List<Integer> weights;

   public WeightedList() {
      items = new ArrayList<T>();
      weights = new ArrayList<Integer>();
   }

   public void add(T item) {
      add(item, 1);
   }

   public void add(T item, int weight) {
      if (weight < 1) {
         weight = 1;
      }
      items.add(item);
      weights.add(weight);
      totalWeight += weight;
   }

   public T get(Random r) {
      if (items.size() == 0) {
         return null;
      }
      int value = r.nextInt(totalWeight);
      for (int x = 0; x < items.size(); x++) {
         if (value <= 0) {
            return items.get(x);
         } else {
            value -= weights.get(x);
         }
      }
      return null;
   }
}
