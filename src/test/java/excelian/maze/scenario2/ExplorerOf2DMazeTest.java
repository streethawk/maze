package excelian.maze.scenario2;

import excelian.maze.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class ExplorerOf2DMazeTest {

    private Explorer explorer;
    private static Maze twoDMaze;

    @BeforeClass
    public static void setup() throws Exception{
        twoDMaze = new Maze("TwoDMazeWith3Lines.txt");
    }
    
    @Before
    public void init(){
        explorer = new Explorer();
    }

    @Test
    public void locateStartPositionTest() throws Exception{
        int[] start = explorer.locatePosition(twoDMaze, String.valueOf(Position.START.getPositionCharacter()));
        assertEquals(1, start[0]);
        assertEquals(6, start[1]);
    }

    @Test
    public void allAllowedMovesFromStartPositionInDefaultDirection() throws Exception{
        int[] startPosition = explorer.locatePosition(twoDMaze, String.valueOf(Position.START.getPositionCharacter()));
        
        //get the possible moves in EAST
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(startPosition, twoDMaze);
        
        //can go forward or right
        List<MovementOptions> expectedAllowedMoves = newArrayList(MovementOptions.RIGHT);
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }

    @Test
    public void allAllowedMovesFromStartPositionAfterTurningLeft() throws Exception{
        int[] startPosition = explorer.locatePosition(twoDMaze, String.valueOf(Position.START.getPositionCharacter()));
        
        //turn the explorer left
        explorer.turn(TurnOptions.LEFT);
        
        //now he is facing NORTH
        assertEquals(Direction.NORTH, explorer.getCurrentDirectionOfTravel());
        
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(startPosition, twoDMaze);
        
        //only allowed to go right
        List<MovementOptions> expectedAllowedMoves = newArrayList();
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }

    @Test
    public void allAllowedMovesFromEndOfMaze() throws Exception{
        int[] lastPosition = new int[]{2,15}; // its a 3x16 length 2D array
        
        //checking moves when travelling in EAST or default direction
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(lastPosition, twoDMaze);
        List<MovementOptions> expectedAllowedMoves = newArrayList();
        assertEquals(expectedAllowedMoves, allAllowedMoves);
        
        //turn the explorer right, now he is in going SOUTH
        explorer.turn(TurnOptions.RIGHT);
        assertEquals(Direction.SOUTH, explorer.getCurrentDirectionOfTravel());
        
        allAllowedMoves = explorer.allAllowedMoves(lastPosition, twoDMaze);
        expectedAllowedMoves = newArrayList(MovementOptions.RIGHT);
        assertEquals(expectedAllowedMoves, allAllowedMoves);

        //turn the explorer left, so he is facing in EAST again
        explorer.turn(TurnOptions.LEFT);
        assertEquals(Direction.EAST, explorer.getCurrentDirectionOfTravel());

        //turn the explorer left now, so he is facing in NORTH now
        explorer.turn(TurnOptions.LEFT);
        assertEquals(Direction.NORTH, explorer.getCurrentDirectionOfTravel());
        allAllowedMoves = explorer.allAllowedMoves(lastPosition, twoDMaze);
        expectedAllowedMoves = newArrayList(MovementOptions.LEFT);
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }

    @Test
    public void allAllowedMovesFromOriginOfMaze() throws Exception{
        int[] origin = new int[]{0,0}; // its a 3x16 length 2D array
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(origin, twoDMaze);
        List<MovementOptions> expectedAllowedMoves = newArrayList();
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }
    
}
