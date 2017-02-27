package excelian.maze.scenario1;

import excelian.maze.Maze;
import excelian.maze.MazeTestUtils;
import excelian.maze.Position;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 A simple linear maze representation, can also be thought as array 0 to 17. Captured in LinearSimpleMaze.txt
 
 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
     X X X X X S F X X X      X  X  
 */
public class LinearMazeTest {
    
    private static Maze maze;
    
    @BeforeClass
    public static void setup() throws Exception{
        maze = MazeTestUtils.createMazeOfAType("LinearSimpleMaze.txt");     
    }

    @Test
    public void testLinearMazeHasJustOneListOfStrings() throws Exception{
        assertEquals(1, maze.getMazeStructure().size());
    }

    @Test
    public void testLinearMazeHas10Walls(){
        assertEquals(10, maze.getOccurrence(Maze.WALL));
    }

    //testing leading and trailing whitespaces as well
    @Test
    public void testLinearMazeHas4Spaces(){
        assertEquals(6, maze.getOccurrence(Maze.SPACE));
    }

    @Test
    public void positiveInvalidXCoordinateForLinearMaze(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(2,0)); // as its 1 D array, x>0 is invalid
    }

    @Test
    public void negativeInvalidXCoordinateForLinearMaze(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(-1,0));
    }

    @Test
    public void positiveInvalidYCoordinateForLinearMaze(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(0,22));
    }

    @Test
    public void negativeInvalidYCoordinateForLinearMaze(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(0,-7));
    }

    @Test
    public void getValueAtCoordinateForStart(){
        assertEquals(String.valueOf(Position.START.getPositionCharacter()), maze.getValueAtCoordinate(0,7));
    }

    @Test
    public void getValueAtCoordinateForFinish(){
        assertEquals(String.valueOf(Position.FINISH.getPositionCharacter()), maze.getValueAtCoordinate(0,8));
    }

    @Test
    public void getValueAtLastIndexForLinearMaze(){
        assertEquals(" ", maze.getValueAtCoordinate(0,17));
    }

    @Test
    public void getValueAtFirstIndexForLinearMaze(){
        assertEquals(" ", maze.getValueAtCoordinate(0,0));
    }

    @Test
    public void getWallValueForLinearMaze(){
        assertEquals("X", maze.getValueAtCoordinate(0,3));
    }

}
