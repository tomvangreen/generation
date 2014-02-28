package ch.digitalmeat.generation.test.level.data;

import static ch.digitalmeat.generation.level.data.Direction8.East;
import static ch.digitalmeat.generation.level.data.Direction8.North;
import static ch.digitalmeat.generation.level.data.Direction8.NorthEast;
import static ch.digitalmeat.generation.level.data.Direction8.NorthWest;
import static ch.digitalmeat.generation.level.data.Direction8.South;
import static ch.digitalmeat.generation.level.data.Direction8.SouthEast;
import static ch.digitalmeat.generation.level.data.Direction8.SouthWest;
import static ch.digitalmeat.generation.level.data.Direction8.West;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Direction8Test {

   @Test
   public void testOpposites() {
      assertEquals("North <-> South", North.getOpposite(), South);
      assertEquals("NorthEast <-> SouthWest", NorthEast.getOpposite(), SouthWest);
      assertEquals("East <-> West", East.getOpposite(), West);
      assertEquals("SouthEast <-> NorthWest", SouthEast.getOpposite(), NorthWest);
      assertEquals("South <-> North", South.getOpposite(), North);
      assertEquals("SouthWest <-> NorthEast", SouthWest.getOpposite(), NorthEast);
      assertEquals("West <-> East", West.getOpposite(), East);
      assertEquals("NorthWest <-> SouthEast", NorthWest.getOpposite(), SouthEast);
   }

   @Test
   public void testCw() {
      assertEquals("CCW 1 North => NorthEast", North.cw(1), NorthEast);
      assertEquals("CCW 1 North => NorthEast", North.cw(2), East);
      assertEquals("CCW 1 North => NorthEast", North.cw(7), NorthWest);
      assertEquals("CCW 1 North => NorthEast", North.cw(8), North);
      assertEquals("CCW 1 North => NorthEast", North.cw(-3), SouthWest);

   }

   @Test
   public void testCcw() {
      assertEquals("CCW 1 North => NorthEast", North.ccw(1), NorthWest);
      assertEquals("CCW 1 North => NorthEast", North.ccw(2), West);
      assertEquals("CCW 1 North => NorthEast", North.ccw(7), NorthEast);
      assertEquals("CCW 1 North => NorthEast", North.ccw(8), North);
      assertEquals("CCW 1 North => NorthEast", North.ccw(-3), SouthEast);

   }
}
