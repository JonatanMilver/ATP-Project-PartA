package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {
    private BestFirstSearch bestFS = new BestFirstSearch();
    private AMazeGenerator myMaze = new MyMazeGenerator();
    private AMazeGenerator simpleMaze = new SimpleMazeGenerator();
    private AMazeGenerator empty = new EmptyMazeGenerator();
    private Maze maze = myMaze.generate(5,10);
    private Maze smallestMaze = myMaze.generate(1,1);
//    private Maze zeroMaze = myMaze.generate(0,0);


    private ISearchable domain1 = new SearchableMaze(maze);
    private ISearchable domain2 = new SearchableMaze(smallestMaze);
//    private ISearchable domain3 = new SearchableMaze(zeroMaze);

    @Test
    void buildMyMaze_Test() {
//        assertNull(myMaze.generate(0,0) , "option1");
        assertNull(myMaze.generate(0,1) , "option2");
        assertNull(myMaze.generate(1,0) , "option3");
//        assertNull(myMaze.generate(-5,30) , "option4");

        for (int i = 1 ; i < 50 ; i++){
            for (int j = 1 ; j < 50 ; j++){
                assertNotNull(myMaze.generate(i,j), "output is null in maze size: (" + i +"," + j + ")");
            }
        }
    }

    @Test
    void solve() throws Exception {
//        assertNull(bestFS.solve(domain3));
        assertEquals(domain2.getStartState(),domain2.getGoalState());
        assertEquals(smallestMaze.getStartPosition(),smallestMaze.getGoalPosition());
    }


    @Test
    void getName() throws Exception{
        assertEquals(bestFS.getName(), "Best First Search");
    }





}