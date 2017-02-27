package excelian.maze.scenario1;

import excelian.maze.Maze;
import excelian.maze.MazeTestUtils;
import excelian.maze.orientation.Position;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A simple 2D Maze with 3 rows as depicted below. Can also be thought as array[3][13]. Captured in TwoDMazeWith3Lines.txt
 * 
     0 1 2 3 4 5 6 7 8 9 10 11 12 13
   0 X X X X X X X X X X X  X  X  X 
   1 X X X X X X S       X          
   2 F              
 */
public class SimpleTwoDMazeTest {

    private static Maze maze;

    @BeforeClass
    public static void setup() throws Exception{
        maze = MazeTestUtils.createMazeOfAType("TwoDMazeWith3Lines.txt");
    }

    @Test
    public void test2DMazeHas3Lines() throws Exception{
        assertEquals(3, maze.getMazeStructure().size());
    }

    @Test
    public void test2DMazeHas24Walls(){
        assertEquals(24, maze.getOccurrence(Maze.WALL));
    }

    @Test
    public void test2DMazeHas19Spaces(){
        assertEquals(19, maze.getOccurrence(Maze.SPACE));
    }

    @Test
    public void getValueAtCoordinateForStart(){
        assertEquals(String.valueOf(Position.START.getPositionCharacter()), maze.getValueAtCoordinate(1,6));
    }

    @Test
    public void getValueAtCoordinateForFinish(){
        assertEquals(String.valueOf(Position.FINISH.getPositionCharacter()), maze.getValueAtCoordinate(2,0));
    }

    @Test
    public void getValueAtCoordinateForBeginning(){
        assertEquals("X", maze.getValueAtCoordinate(0,0));
    }

    @Test
    public void getValueAtCoordinateForEnd(){
        assertEquals(" ", maze.getValueAtCoordinate(2,13));
    }

    @Test
    public void getValueAtCoordinateOutOfBoundXValue(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(9,13));
    }

    @Test
    public void getValueAtCoordinateOutOfBoundYValue(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(9,14));
    }

}
