package excelian.maze;

public class MazeTestUtils {

    public static Maze createMazeOfAType(String fileName) throws Exception{
        return new Maze(fileName);
    }
}
