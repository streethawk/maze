package excelian.maze.scenario1;

import excelian.maze.Maze;
import excelian.maze.MazeTestUtils;
import excelian.maze.orientation.Position;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ProvidedExampleMazeTest {

    private static Maze maze;

    @BeforeClass
    public static void setup() throws Exception{
        maze = MazeTestUtils.createMazeOfAType("ExampleMaze.txt");
    }

    @Test
    public void testExampleMazeHas15Lines() throws Exception{
        assertEquals(15, maze.getMazeStructure().size());
    }

    @Test
    public void testExampleMazeHas149Walls(){
        assertEquals(149, maze.getOccurrence(Maze.WALL));
    }

    @Test
    public void testExampleMazeHas74Spaces(){
        assertEquals(74, maze.getOccurrence(Maze.SPACE));
    }

    @Test
    public void getValueAtCoordinateForStart(){
        assertEquals(String.valueOf(Position.START.getPositionCharacter()), maze.getValueAtCoordinate(3,3));
    }

    @Test
    public void getValueAtCoordinateForFinish(){
        assertEquals(String.valueOf(Position.FINISH.getPositionCharacter()), maze.getValueAtCoordinate(14,1));
    }

    @Test
    public void getValueAtCoordinateForBeginning(){
        assertEquals("X", maze.getValueAtCoordinate(0,0));
    }

    @Test
    public void getValueAtCoordinateForEnd(){
        assertEquals("X", maze.getValueAtCoordinate(14,14));
    }

    @Test
    public void getValueAtCoordinateOutOfBoundXValue(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(15,13));
    }

    @Test
    public void getValueAtCoordinateOutOfBoundYValue(){
        assertEquals(Maze.INVALID, maze.getValueAtCoordinate(9,15));
    }

}
