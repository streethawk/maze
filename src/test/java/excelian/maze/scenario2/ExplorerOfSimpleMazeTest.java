package excelian.maze.scenario2;

import excelian.maze.*;
import excelian.maze.orientation.Direction;
import excelian.maze.orientation.MovementOptions;
import excelian.maze.orientation.Position;
import excelian.maze.orientation.TurnOptions;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExplorerOfSimpleMazeTest {
    
    private static Explorer explorer;
    private static Maze linearMaze;
    
    @BeforeClass
    public static void setup() throws Exception{
        explorer = new Explorer();
        linearMaze = new Maze("LinearSimpleMaze.txt");
    }
    
    @Test
    public void exploreSimpleMazeTest() throws Exception{
        int[] start = explorer.locatePosition(linearMaze, String.valueOf(Position.START.getPositionCharacter()));
        assertEquals(0, start[0]);
        assertEquals(7, start[1]);
    }

    @Test
    public void allAllowedMovesFromStartPosition() throws Exception{
        int[] startPosition = explorer.locatePosition(linearMaze, String.valueOf(Position.START.getPositionCharacter()));
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(startPosition, linearMaze);
        List<MovementOptions> expectedAllowedMoves = newArrayList(MovementOptions.FORWARD);
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }

    @Test
    public void allAllowedMovesFromEndOfMaze() throws Exception{
        int[] lastPosition = new int[]{0,18}; // its a 18 length linear array
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(lastPosition, linearMaze);
        List<MovementOptions> expectedAllowedMoves = newArrayList();
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }

    @Test
    public void allAllowedMovesFromOriginOfMaze() throws Exception{
        int[] origin = new int[]{0,0}; // its a 18 length linear array
        List<MovementOptions> allAllowedMoves = explorer.allAllowedMoves(origin, linearMaze);
        List<MovementOptions> expectedAllowedMoves = newArrayList(MovementOptions.FORWARD);
        assertEquals(expectedAllowedMoves, allAllowedMoves);
    }
    
    @Test
    public void testStartDirectionOfTravelIsEast() {
        //check that the explorer is heading EAST in the beginning
        assertEquals(Direction.EAST, explorer.getCurrentDirectionOfTravel());
    }

    @Test
    public void testExplorerCanTurnAtWill() {

        //turn him left
        explorer.turn(TurnOptions.LEFT);

        //check that the explorer is now heading NORTH
        assertEquals(Direction.NORTH, explorer.getCurrentDirectionOfTravel());

        //turn him left again
        explorer.turn(TurnOptions.LEFT);

        //check that the explorer is now heading SOUTH
        assertEquals(Direction.WEST, explorer.getCurrentDirectionOfTravel());

        //turn him right now
        explorer.turn(TurnOptions.RIGHT);

        //check that the explorer is now heading back to NORTH
        assertEquals(Direction.NORTH, explorer.getCurrentDirectionOfTravel());

        //turn him right again
        explorer.turn(TurnOptions.RIGHT);

        //check that the explorer is now heading EAST, he is back now covering a 180 degrees area
        assertEquals(Direction.EAST, explorer.getCurrentDirectionOfTravel());
        
    }
    
    @Test
    public void exploreLinearMaze() throws Exception{
        LinkedHashMap<String,String> expectedMovements = newLinkedHashMap();
        String[] expected1 = {"0","8"};
        String[] expected2 = {"0","9"};
        expectedMovements.put(Arrays.toString(expected1),"S");
        expectedMovements.put(Arrays.toString(expected2),"F");
        
    }

}
