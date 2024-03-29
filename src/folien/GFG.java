package folien;

import java.util.*;

public class GFG {

    // Maximum number of nodes is 100000 and nodes are
    // numbered from 1 to 100000
    static final int MAXN = 100001;

    static Vector<Integer>[] tree = new Vector[MAXN];
    static int[][] path = new int[3][MAXN]; // storing root to node path
    static boolean flag;

    // storing the path from root to node
    static void dfs(int cur, int prev, int pathNumber, int ptr, int node) {
        for (int i = 0; i < tree[cur].size(); i++) {
            if (tree[cur].get(i) != prev && !flag) {
                // pushing current node into the path
                path[pathNumber][ptr] = tree[cur].get(i);
                if (tree[cur].get(i) == node) {
                    // node found
                    flag = true;

                    // terminating the path
                    path[pathNumber][ptr + 1] = -1;
                    return;
                }
                dfs(tree[cur].get(i), cur, pathNumber, ptr + 1, node);
            }
        }
    }

    // This Function compares the path from root to 'a' & root
    // to 'b' and returns LCA of a and b. Time Complexity : O(n)
    static int LCA(int a, int b) {
        // trivial case
        if (a == b) return a;

        // setting root to be first element in path
        path[1][0] = path[2][0] = 1;

        // calculating path from root to a
        flag = false;
        dfs(1, 0, 1, 1, a);

        // calculating path from root to b
        flag = false;
        dfs(1, 0, 2, 1, b);

        // runs till path 1 & path 2 matches
        int i = 0;
        while (i < MAXN && path[1][i] == path[2][i]) i++;

        // returns the last matching node in the paths
        return path[1][i - 1];
    }

    static void addEdge(int a, int b) {
        tree[a].add(b);
        tree[b].add(a);
    }

    // Driver code
    public static void main(String[] args) {
        for (int i = 0; i < MAXN; i++)
            tree[i] = new Vector<>();

        // Number of nodes
        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(2, 5);
        addEdge(2, 6);
        addEdge(3, 7);
        addEdge(3, 8);

        System.out.print("LCA(3, 7) = " + LCA(1, 7) + "\n");
        System.out.print("LCA(4, 6) = " + LCA(4, 6) + "\n");
    }
}
