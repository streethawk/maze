package excelian.maze.scenario1;

import excelian.maze.*;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * A 2D maze representation, can also be thought as array[15][15]. Captured in ExampleMaze.txt
 * 
 XXXXXXXXXXXXXXX
 X             X
 X XXXXXXXXXXX X
 X XS        X X
 X XXXXXXXXX X X
 X XXXXXXXXX X X
 X XXXX      X X
 X XXXX XXXX X X
 X XXXX XXXX X X
 X X    XXXXXX X
 X X XXXXXXXXX X
 X X XXXXXXXXX X
 X X         X X
 X XXXXXXXXX   X
 XFXXXXXXXXXXXXX
 
 */
public class MazeStructureTest {
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    

    @Test
    public void invalidMazeExceptionWhenEmptyMaze() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.EMPTY_MAZE);
        
        Maze maze = new Maze("EmptyMaze.txt");
    }
    
    @Test
    public void invalidMazeExceptionWhenNoStartPositionFound() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.NO_START_FOUND);

        Maze maze = new Maze("MazeWithNoStartPosition.txt");

    }

    @Test
    public void invalidMazeExceptionWhenMultipleStartPositionFound() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.MULTIPLE_START_FOUND);

        Maze maze = new Maze("MazeWithMultipleStartPositions.txt");
    }

    @Test
    public void invalidMazeExceptionWhenNoFinishPositionFound() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.NO_FINISH_FOUND);

        Maze maze = new Maze("MazeWithNoFinishPosition.txt");
    }

    @Test
    public void invalidMazeExceptionWhenMultipleFinishPositionFound() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.MULTIPLE_FINISH_FOUND);

        Maze maze = new Maze("MazeWithMultipleFinishPositions.txt");
    }

    @Test
    public void invalidMazeExceptionWhenNoStartAndNoFinishPositionFound() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.NO_START_FINISH_FOUND);

        Maze maze = new Maze("MazeWithNoStartAndNoFinish.txt");

    }

    @Test
    public void invalidMazeExceptionWhenMultipleStartAndFinishPositionFound() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Maze.MULTIPLE_START_FINISH_FOUND);

        Maze maze = new Maze("MazeWithMultipleStartAndFinish.txt");

    }
    
}
