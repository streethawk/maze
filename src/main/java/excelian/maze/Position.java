package excelian.maze;

public enum Position {
    START{
        @Override
        public char getPositionCharacter(){ return 'S'; }
    },
    FINISH{
        @Override
        public char getPositionCharacter(){ return 'F'; }
    };

    public abstract char getPositionCharacter();
}
