package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    private ArrayList<AState> SolutionPath;
    private double solutionCost;

    public Solution() {
        SolutionPath = new ArrayList<AState>();
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
        for(int i=0;i<SolutionPath.size();i++){
            System.out.print(String.format(" %s",SolutionPath.get(i).getName()));
        }
        System.out.println();
    }
}
