package excelian.maze.orientation;

import excelian.maze.Maze;

/**
 * Enum representing Movement Options for the Explorer
 * Given the position in x,y terms, Maze and the Direction of Travel, this provides coordinates when
 * User moves forward
 * User turns right and moves forward
 * User turns left and moves forward
 * 
 * The explorer will only ever use the Move Forward option to move forward
 * 
 * Left option is to determine if it is possible to move left in the current direction from the provided coordinates
 * 
 * Right option is to determine if it is possible to move right in the current direction from the provided coordinates
 */
public enum MovementOptions {
    
    FORWARD{
        @Override
        public int[] getDirectionCoordinates(int x, int y, Maze maze, Direction direction){
            int[] coordinates = new int[]{-1,-1};
            switch (direction){
                case NORTH: coordinates[0] = x-1; coordinates[1]=y; break;
                case EAST:  coordinates[0] = x; coordinates[1]=y+1; break;  
                case WEST:  coordinates[0] = x; coordinates[1]=y-1; break;
                case SOUTH: coordinates[0] = x+1; coordinates[1]=y; break;
            }
            
            return coordinates; 
        }

        @Override
        public boolean moveAllowed(int x, int y, Maze maze, Direction direction) {
            int[] coordinates = getDirectionCoordinates(x,y,maze,direction);
            return MovementOptions.isValid(maze.getValueAtCoordinate(coordinates[0],coordinates[1]));
        }
        
    },
    LEFT{
        @Override
        public int[] getDirectionCoordinates(int x, int y, Maze maze, Direction direction){

            int[] coordinates = new int[]{-1,-1};
            switch (direction){
                case NORTH: coordinates[0] = x; coordinates[1]=y-1; break;
                case EAST:  coordinates[0] = x-1; coordinates[1]=y; break;
                case WEST:  coordinates[0] = x+1; coordinates[1]=y; break;
                case SOUTH: coordinates[0] = x; coordinates[1]=y+1; break;
            }

            return coordinates;
        }

        @Override
        public boolean moveAllowed(int x, int y, Maze maze, Direction direction) {
            int[] coordinates = getDirectionCoordinates(x,y,maze,direction);
            return MovementOptions.isValid(maze.getValueAtCoordinate(coordinates[0],coordinates[1]));

        }

    },
    RIGHT{
        @Override
        public int[] getDirectionCoordinates(int x, int y, Maze maze, Direction direction){

            int[] coordinates = new int[]{-1,-1};
            switch (direction){
                case NORTH: coordinates[0] = x; coordinates[1]=y+1; break;
                case EAST:  coordinates[0] = x+1; coordinates[1]=y; break;
                case WEST:  coordinates[0] = x-1; coordinates[1]=y; break;
                case SOUTH: coordinates[0] = x; coordinates[1]=y-1; break;
            }

            return coordinates;
        }

        @Override
        public boolean moveAllowed(int x, int y, Maze maze, Direction direction) {
            int[] coordinates = getDirectionCoordinates(x,y,maze,direction);
            return MovementOptions.isValid(maze.getValueAtCoordinate(coordinates[0],coordinates[1]));
        }

    };
    
    public abstract int[] getDirectionCoordinates(int x, int y, Maze maze, Direction direction);
    
    public abstract boolean moveAllowed(int x, int y, Maze maze, Direction direction);
    
    private static boolean isValid(String move){
        if (Maze.INVALID.equals(move) || Character.toString(Maze.WALL).equals(move)){
            return false;
        }
        return true;
    }
}
