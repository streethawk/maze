package excelian.maze;

import excelian.maze.exception.InvalidMazeException;
import excelian.maze.orientation.Direction;
import excelian.maze.orientation.MovementOptions;
import excelian.maze.orientation.Position;
import excelian.maze.orientation.TurnOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

/**
 * Explorer implementation. Where explorer can Move forward, turn left and/or right and maintains a Map of his movements
 */
public class Explorer {

    public static final String INVALID_MAZE = "Invalid/Null Maze Supplied";
    private static final Direction START_DIRECTION = Direction.EAST; //declaring the default traversal direction as Start direction
    private Direction currentDirectionOfTravel; //Explorer always maintains/knows the direction he/she is traveling in
    private LinkedHashMap<String,String> positionValueDirectionMap; //A temporary map K=[0,0] V=" " Explorer always maintains a map
    private List<String> positionValueList; // K=[0,0] V=" " Explorer always maintains a map and holds it as a record of his journey
    private int[] currentPosition;

    public Explorer(){
        this.currentDirectionOfTravel = START_DIRECTION;
    }

    public Direction getCurrentDirectionOfTravel() {
        return currentDirectionOfTravel;
    }

    //this should not be public as no-one external should be able to set the direction of travel. the direction
    //can only be changed when explorer turns, so an indirect control
    private void setCurrentDirectionOfTravel(Direction currentDirectionOfTravel) {
        this.currentDirectionOfTravel = currentDirectionOfTravel;
    }

    /**
     * Explore the maze by evaluating next move recursively
     * @param maze Maze to explore
     * @return list of [x,y] coordinates showing explorers journey in the maze
     * @throws IOException
     * @throws InvalidMazeException
     */
    public List<String> exploreMaze(Maze maze)  throws IOException, InvalidMazeException{
        positionValueDirectionMap = newLinkedHashMap();
        positionValueList = newArrayList();

        if(null==maze){
            throw new InvalidMazeException(INVALID_MAZE);
        }

        int[] start = locatePosition(maze, String.valueOf(Position.START.getPositionCharacter()));
        int[] finish = locatePosition(maze, String.valueOf(Position.FINISH.getPositionCharacter()));

        positionValueDirectionMap.put(Arrays.toString(start),String.valueOf(Position.START.getPositionCharacter()));
        positionValueList.add(Arrays.toString(start));

        explorePosition(start, maze, finish);
        return positionValueList;
    }

    /**
     * Evaluates the current position and takes the next step accordingly
     * @param start [x,y] coordinates of the start position depicted by 'S'
     * @param maze Maze representation
     * @param finish [x,y] coordinates of the finish position depicted by 'F'
     */
    private void explorePosition(int[] start, Maze maze, int[] finish){

        if(!positionValueDirectionMap.containsKey(Arrays.toString(finish))){

            List<MovementOptions> allAllowedMoves = allAllowedMoves(start, maze);
            
            //hit the dead-end? turn around and trace your steps backwards
            if(allAllowedMoves.isEmpty()){
                turnaround();
                allAllowedMoves = allAllowedMoves(start, maze);
            }
            if(allAllowedMoves.size()==1){
                int[] movePosition = move(allAllowedMoves.get(0), start, maze);
                explorePosition(movePosition, maze, finish);
            }
            else {
                for(MovementOptions possibleMove: allAllowedMoves){
                    int[] possibleNextMove = possibleMove.getDirectionCoordinates(start[0],start[1],maze, getCurrentDirectionOfTravel());
                    if(!positionValueDirectionMap.containsKey(Arrays.toString(possibleNextMove))){
                        int[] movePosition = move(possibleMove, start, maze);
                        explorePosition(movePosition, maze, finish);
                    }

                }
            }
        }
    }

    /**
     * Single move taken by explorer, making a note of the move at the same time
     * @param possibleMove one of the possible moves which can be taken by explorer
     * @param start [x,y] coordinates of the start position depicted by 'S'
     * @param maze Maze representation
     * @return coordinate of the next possible move
     */
    private int[] move(MovementOptions possibleMove, int[] start, Maze maze) {
        turnIfNeeded(possibleMove); //turn the explorer as indicates by the available Movement Options

        int[] movePosition = MovementOptions.FORWARD.getDirectionCoordinates(start[0],start[1],maze,getCurrentDirectionOfTravel());
        String value = maze.getValueAtCoordinate(movePosition[0],movePosition[1]);
        positionValueList.add(Arrays.toString(movePosition));

        if(!positionValueDirectionMap.containsValue(String.valueOf(Position.FINISH.getPositionCharacter()))){

            //assign the start position as the current position
            currentPosition = movePosition;

            //make an entry of the start position in the map
            positionValueDirectionMap.put(Arrays.toString(currentPosition), value);
        }

        return currentPosition;
    }

    /**
     * Turns the explorer in a direction specified ny Turnoptions value (left or right)
     * @param turnOptions
     */
    public void turn(TurnOptions turnOptions) {
        if(turnOptions.equals(TurnOptions.LEFT)){
            setCurrentDirectionOfTravel(getCurrentDirectionOfTravel().turnLeft());
        }else if(turnOptions.equals(TurnOptions.RIGHT)){
            setCurrentDirectionOfTravel(getCurrentDirectionOfTravel().turnRight());
        }

    }

    /**
     * Locates the [x,y] coordinate of specified string value in the maze
     * @param maze
     * @param positionCharacter
     * @return
     * @throws IOException
     */
    public int[] locatePosition(Maze maze, String positionCharacter) throws IOException{
        int[] start = {0,0};
        if(null!=maze){
            List<List<String>> mazeStructure = maze.getMazeStructure();
            for (int i = 0; i < mazeStructure.size(); i++) {
                List<String> row = mazeStructure.get(i);
                if(row.contains(positionCharacter)){
                    start[0] = i;
                    start[1] = row.indexOf(positionCharacter);
                }
            }
        }
        else {
            start[0] = -1;
            start[1] = -1;
        }
        return start;
    }

    /**
     * List of all possible moves from current position travelling in a direction
     * @param currentPosition
     * @param maze
     * @return List of all possible moves from current position travelling in a direction
     */
    public List<MovementOptions> allAllowedMoves(int[] currentPosition, Maze maze) {
        List<MovementOptions> allAllowedMoves = newArrayList();

        for(MovementOptions movementOptions : MovementOptions.values()){
            if(movementOptions.moveAllowed(currentPosition[0],currentPosition[1],maze, getCurrentDirectionOfTravel())){
                allAllowedMoves.add(movementOptions);
            }
        }

        return allAllowedMoves;
    }

    private void turnIfNeeded(MovementOptions movementOptions){
        if(movementOptions.name().equals(MovementOptions.LEFT.name())){
            turn(TurnOptions.LEFT);
        }
        if(movementOptions.name().equals(MovementOptions.RIGHT.name())){
            turn(TurnOptions.RIGHT);
        }
    }

    private void turnaround(){
        turn(TurnOptions.LEFT);
        turn(TurnOptions.LEFT);
    }


}
