package test;

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
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(100, 100);
        SearchableMaze searchableMaze = new SearchableMaze(maze);

//        maze.print();



//        Solution sol = solveProblem(searchableMaze, new BreadthFirstSearch());
//        maze.printWithColor(sol);
//        Solution sol1 = solveProblem(searchableMaze, new DepthFirstSearch());
//        maze.printWithColor(sol1);
        Solution sol2 = solveProblem(searchableMaze, new BestFirstSearch());
        maze.printWithColor(sol2);

        System.out.println(maze.getStartPosition());
        System.out.println(maze.getGoalPosition());


    }

    private static Solution solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
        //Printing Solution Path
//        System.out.println("Solution Size: " + solution.getSolutionPath().size());
//        System.out.print("Solution path: ");
//        solution.print();
//        ArrayList<AState> solutionPath = solution.getSolutionPath();
//        for (int i = 0; i < solutionPath.size(); i++) {
//            System.out.println(String.format("%s. %s",i,solutionPath.get(i)));
//        }
        return solution;
    }
}