package test;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.*;

import java.util.ArrayList;

/**
 * Created by Aviadjo on 3/22/2017.
 */
public class RunSearchOnMaze {
    public static void main(String[] args) {
        IMazeGenerator mg = new EmptyMazeGenerator();
        Maze maze = mg.generate(3, 3);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
//        Solution solution = new BreadthFirstSearch().solve(searchableMaze);
        Solution solution2 = new BestFirstSearch().solve(searchableMaze);
//        solveProblem(searchableMaze, new BreadthFirstSearch());
//        solveProblem(searchableMaze, new DepthFirstSearch());
//        solveProblem(searchableMaze, new BestFirstSearch());
    }

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
        }
    }
}