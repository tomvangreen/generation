package ch.digitalmeat.generation.data;

import java.util.ArrayList;
import java.util.List;

public class List2D<T> {
   private List<T> list;
   private int width;
   private int height;
   private int length;
   private boolean initialized;

   public List2D() {
      this(new ArrayList<T>());
   }

   public List2D(List<T> baseList) {
      this.list = baseList;
   }

   public void initialize(int width, int height) {
      this.width = width;
      this.height = height;

      this.length = width * height;
      initList();
      initialized = true;
   }

   private void initList() {
      list.clear();
      for (int index = 0; index < length; index++) {
         list.add(null);
      }
   }

   public void clear() {
      if (initialized) {
         list.clear();
         initList();
      }
   }

   public int width() {
      return width;
   }

   public int height() {
      return height;
   }

   public int length() {
      return length;
   }

   public int getX(int position) {
      return position % width;
   }

   public int getY(int position) {
      return position / width;
   }

   public int getPosition(int x, int y) {
      return x + y * width;
   }

   public boolean isPositionValid(int x, int y) {
      return isPositionValid(getPosition(x, y));
   }

   public boolean isPositionValid(int position) {
      return initialized && position >= 0 && position < length;
   }

   public T get(int x, int y) {
      return get(getPosition(x, y));
   }

   public T get(int position) {
      return isPositionValid(position) ? list.get(position) : null;
   }

   public boolean set(int x, int y, T value) {
      return put(getPosition(x, y), value);
   }

   public boolean put(int position, T value) {
      if (isPositionValid(position)) {
         list.set(position, value);
         return true;
      }
      return false;
   }

   public void deinit() {
      list.clear();
      width = 0;
      height = 0;
      length = 0;
      initialized = false;
   }
}
