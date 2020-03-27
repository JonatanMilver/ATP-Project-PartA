package algorithms.search;

public interface ISearchingAlgorithm {
    public Solution solve(ISearchable domain);
    public int getNumberOfNodesEvaluated();
    public String getName();
}
