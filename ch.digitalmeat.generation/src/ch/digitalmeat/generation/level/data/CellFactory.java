package ch.digitalmeat.generation.level.data;

public interface CellFactory {
   public Cell spawn();

   public void init(Cell cell);

   public class DefaultCellFactory implements CellFactory {

      @Override
      public Cell spawn() {
         return new Cell(this);
      }

      @Override
      public void init(Cell cell) {

      }
   }
}
