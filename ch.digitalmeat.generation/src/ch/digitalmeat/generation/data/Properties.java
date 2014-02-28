package ch.digitalmeat.generation.data;

import java.util.HashMap;

public class Properties {
   private HashMap<String, Object> map;

   public Properties() {
      map = new HashMap<String, Object>();
   }

   public void put(String key, Object object) {
      if (object == null) {
         map.remove(key);
      } else {
         map.put(key, object);
      }
   }

   public Object getObject(String key) {
      return map.get(key);
   }

   public <T> T get(String key) {
      Object object = map.get(key);
      return object == null ? null : (T) object;
   }

   public <T> T get(String key, T defaultValue) {
      Object object = map.get(key);
      return object == null ? defaultValue : (T) object;

   }
}
