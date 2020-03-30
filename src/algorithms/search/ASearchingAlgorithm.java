package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    private String algo_name;
    private int visitedNode;

    public ASearchingAlgorithm() {
        this.visitedNode = 0;
    }


    public void setAlgo_name(String algo_name) {
        this.algo_name = algo_name;
    }

    public String getName() {
        return algo_name;
    }

    public int getNumberOfNodesEvaluated(){
        return visitedNode;
    }

    public void increaseVisited(){
        this.visitedNode++;
    }
}
