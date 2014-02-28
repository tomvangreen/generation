package ch.digitalmeat.generation.level.data;

public interface CellFactory {
   public Cell spawn();

   public class DefaultCellFactory implements CellFactory {

      @Override
      public Cell spawn() {
         return new Cell();
      }

   }
}
