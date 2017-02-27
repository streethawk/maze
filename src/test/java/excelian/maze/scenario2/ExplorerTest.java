package excelian.maze.scenario2;

import excelian.maze.Explorer;
import excelian.maze.exception.InvalidMazeException;
import excelian.maze.Maze;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ExplorerTest {
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void cantExploreWithNullMaze() throws Exception{
        expectedException.expect(InvalidMazeException.class);
        expectedException.expectMessage(Explorer.INVALID_MAZE);
        
        Explorer explorer = new Explorer();
        explorer.exploreMaze(null);
    }

    @Test
    public void explore2DMaze() throws Exception{
        Explorer explorer = new Explorer();
        List<String> positionValueDirectioList = explorer.exploreMaze(new Maze("TwoDMazeWith3Lines.txt"));
        List<String> expectedPositions  = newArrayList("[1, 6]", "[2, 6]", "[2, 5]", "[2, 4]", "[2, 3]", "[2, 2]", "[2, 1]", "[2, 0]");
        
        assertThat(positionValueDirectioList, is(expectedPositions));
    }

    @Test
    public void exploreExampleMaze() throws Exception{
        Explorer explorer = new Explorer();
        List<String>  positionValueDirectionList = explorer.exploreMaze(new Maze("ExampleMaze.txt"));
        List<String> expectedPositions  = newArrayList("[3, 3]", "[3, 4]", "[3, 5]", "[3, 6]", "[3, 7]", "[3, 8]", "[3, 9]", "[3, 10]", "[3, 11]", 
        "[4, 11]", "[5, 11]", "[6, 11]", "[7, 11]", "[8, 11]", "[7, 11]", "[6, 11]", "[6, 10]", "[6, 9]", "[6, 8]", "[6, 7]", 
        "[6, 6]", "[7, 6]", "[8, 6]", "[9, 6]", "[9, 5]", "[9, 4]", "[9, 3]", "[10, 3]", "[11, 3]", "[12, 3]", "[12, 4]", "[12, 5]",
        "[12, 6]", "[12, 7]", "[12, 8]", "[12, 9]", "[12, 10]", "[12, 11]", "[13, 11]", "[13, 12]", "[13, 13]", "[12, 13]", "[11, 13]", 
        "[10, 13]", "[9, 13]", "[8, 13]", "[7, 13]", "[6, 13]", "[5, 13]", "[4, 13]", "[3, 13]", "[2, 13]", "[1, 13]", "[1, 12]", "[1, 11]", 
        "[1, 10]", "[1, 9]", "[1, 8]", "[1, 7]", "[1, 6]", "[1, 5]", "[1, 4]", "[1, 3]", "[1, 2]", "[1, 1]", "[2, 1]", "[3, 1]", "[4, 1]", "[5, 1]", 
        "[6, 1]", "[7, 1]", "[8, 1]", "[9, 1]", "[10, 1]", "[11, 1]", "[12, 1]", "[13, 1]", "[14, 1]");

        assertThat(positionValueDirectionList, is(expectedPositions));
    }
}
