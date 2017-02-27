package excelian.maze;

public class Explore {

    public static void main(String[] args) throws Exception{
        Explorer explorer = new Explorer();
        Maze maze = new Maze("ExampleMaze.txt");
        System.out.println(explorer.exploreMaze(maze));
    }
}
