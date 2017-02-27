package excelian.maze;

public enum Direction {
    
    NORTH{
        @Override
        public Direction turnLeft() {
            return WEST;
        }

        @Override
        public Direction turnRight() {
            return EAST;
        }
    },
    EAST{
        @Override
        public Direction turnLeft() {
            return NORTH;
        }

        @Override
        public Direction turnRight() {
            return SOUTH;
        }
        
    },
    WEST{
        @Override
        public Direction turnLeft() {
            return SOUTH;
        }

        @Override
        public Direction turnRight() {
            return NORTH;
        }
        
    },
    SOUTH{
        @Override
        public Direction turnLeft() {
            return EAST;
        }

        @Override
        public Direction turnRight() {
            return WEST;
        }
        
    };
    
    public abstract Direction turnLeft();

    public abstract Direction turnRight();
}
