package algorithms.search;

/**
 * An Interface for a searching algorithm
 */
public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain);
    int getNumberOfNodesEvaluated();
    String getName();
}
