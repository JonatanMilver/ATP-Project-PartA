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
    private Maze zeroMaze = myMaze.generate(0,0);


    private ISearchable domain1 = new SearchableMaze(maze);
    private ISearchable domain2 = new SearchableMaze(smallestMaze);
    private ISearchable domain3 = new SearchableMaze(zeroMaze);






    @Test
    void solve() throws Exception {
        assertNull(bestFS.solve(domain3));
        assertEquals(domain2.getStartState(),domain2.getGoalState());
        assertEquals(smallestMaze.getStartPosition(),smallestMaze.getGoalPosition());
    }


    @Test
    void getName() throws Exception{
        assertEquals(bestFS.getName(), "Best First Search");
    }

    @Test
    void getNumberOfNodesEvaluated() {

    }




}