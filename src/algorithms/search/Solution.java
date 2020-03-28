package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    private ArrayList<AState> SolutionPath;

    public Solution() {
        SolutionPath = new ArrayList<AState>();
    }

    public ArrayList<AState> getSolutionPath(){
        return SolutionPath;
    }

    public void reversePath(){
        Collections.reverse(SolutionPath);
    }


    public void print() {
        for(int i=0;i<SolutionPath.size();i++){
            System.out.println(String.format(" %s",SolutionPath.get(i).getName()));
        }
    }
}
