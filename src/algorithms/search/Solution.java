package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A solution for a Searchable object
 */
public class Solution implements Serializable {

    private ArrayList<AState> SolutionPath;
    private double solutionCost;

    public Solution() {
        SolutionPath = new ArrayList<>();
        solutionCost = 0;
    }


    public ArrayList<AState> getSolutionPath(){
        return SolutionPath;
    }

    public void reversePath(){
        Collections.reverse(SolutionPath);
    }

    public double getSolutionCost() {
        return solutionCost;
    }

    public void setSolutionCost(double solutionCost) {
        this.solutionCost = solutionCost;
    }

    public void print() {
        for (AState state : SolutionPath) {
            System.out.print(String.format(" %s", state.getName()));
        }
        System.out.println();
    }
}
