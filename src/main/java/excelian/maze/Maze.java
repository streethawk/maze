package excelian.maze;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Maze {

    public static final char WALL = 'X';
    public static final char SPACE = ' ';
    public static final String INVALID = "invalid";
    public static final String EMPTY_MAZE = "Empty Maze Structure Found";
    public static final String NO_START_FOUND = "Start Position Not Found";
    public static final String NO_FINISH_FOUND = "Finish Position Not Found";
    public static final String MULTIPLE_START_FOUND = "More than 1 Start Position Found";
    public static final String MULTIPLE_FINISH_FOUND = "More than 1 Finish Position Found";
    public static final String NO_START_FINISH_FOUND = "Neither Start Nor Finish Position Found";
    public static final String MULTIPLE_START_FINISH_FOUND = "Multiple Start And Multiple Finish Position Found";
    
    
    /**
     * FileName containing Maze representation
     */
    private final String mazeFile;


    /**
     * Data Structure representation of maze. This is a symmetric list of list. Where every sub-list is same length.
     * A new List is needed for each Maze instance
     */
    private List<List<String>> mazeStructure;

    public Maze(String fileName) throws IOException{
        this.mazeFile = fileName;
        this.mazeStructure = createMapStructureFromFile(); 
        validateMaze();
    }

    public List<List<String>> getMazeStructure() {
        return mazeStructure;
    }
    
    /**
     * Read the File, indicated by the FileName, reads all the lines and then creates a List<String> containing characters from each line
     * @throws IOException
     * if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
     */
    private List<List<String>> createMapStructureFromFile() throws IOException{
        List<List<String>> structure = newArrayList();
        String filePath = ClassLoader.getSystemResource(mazeFile).getPath();
        List<String> linesList = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        for(String line: linesList){
            final List<String> currentLineList = newArrayList();
            addEachCharacterOfStringAsListItem(line, currentLineList);
            structure.add(currentLineList);
        }
        
        return structure;
    }

    public int getOccurrence(char type){
        int count = 0;
        for(List<String> line: mazeStructure){
            count += Collections.frequency(line, String.valueOf(type));
        }
        return count;
    }

    public String getValueAtCoordinate(int x, int y) {

        //when the provided coordinates are negative
        if(x<0 || y<0)
            return INVALID;

        //when the provided coordinates are out of bound w.r.t the maze 
        if(isXIndexOutOfbound(x) || isYIndexOutOfbound(y))
            return INVALID;

        return mazeStructure.get(x).get(y);
    }

    private void addEachCharacterOfStringAsListItem(String line, List<String> lineList){
        for (int i = 0; i < line.length(); i++) {
            String s = line.substring(i,i+1);
            lineList.add(s);
        }
    }

    private boolean isXIndexOutOfbound(int x){
        /**
         given that the mazeStructure is List of Lists, upper bound of x coordinate can be derived from the total
         * number of lists in the main list, which represent total rows
         */
        return mazeStructure.size()-1<x;
    }

    private boolean isYIndexOutOfbound(int y){
        /**
         * given that the mazeStructure is symmetric, that is, each sublist has same number of elements, length of any
         * sublist will provide the upper bound of the y coordinate
         */
        return mazeStructure.get(0).size()-1<y;
    }
    
    private void validateMaze() throws InvalidMazeException{
        if(mazeStructure.isEmpty()){
            throw new InvalidMazeException(EMPTY_MAZE);
        }
        
        int startOccurrences = getOccurrence(Position.START.getPositionCharacter());
        int finishOccurrences = getOccurrence(Position.FINISH.getPositionCharacter());
        
        if(startOccurrences >1 && finishOccurrences>1){
            throw new InvalidMazeException(MULTIPLE_START_FINISH_FOUND);
        }
        
        if(startOccurrences > 1){
            throw new InvalidMazeException(MULTIPLE_START_FOUND);
        }
        if(finishOccurrences > 1){
            throw new InvalidMazeException(MULTIPLE_FINISH_FOUND);
        }

        if(startOccurrences == 0 && finishOccurrences == 0){
            throw new InvalidMazeException(NO_START_FINISH_FOUND);
        }

        if(startOccurrences == 0){
            throw new InvalidMazeException(NO_START_FOUND);
        }

        if(finishOccurrences == 0){
            throw new InvalidMazeException(NO_FINISH_FOUND);
        }

        
    }

}
