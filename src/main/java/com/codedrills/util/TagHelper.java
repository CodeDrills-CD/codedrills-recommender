package com.codedrills.util;

import com.codedrills.model.Site;
import com.codedrills.model.Tag;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codedrills.model.Tag.*;
import static com.codedrills.model.Tag.Math;
import static java.util.Arrays.asList;

public class TagHelper {
  private static Multimap<Tag, List<String>> tagMap;
  private static Map<String, Tag> invMap;

  static {
    tagMap = HashMultimap.create();
    // cf
    tagMap.put(Strings, asList("cf:strings", "cf:string suffix structures"));
    tagMap.put(GraphsTrees, asList("cf:trees", "cf:graphs", "cf:dfs and similar", "cf:shortest paths"));
    tagMap.put(Flows, asList("cf:flows", "cf:graph matchings"));
    tagMap.put(Games, asList("cf:games"));
    tagMap.put(Probabilities, asList("cf:probabilities"));
    tagMap.put(Combinatorics, asList("cf:combinatorics"));
    tagMap.put(Geometry, asList("cf:geometry"));
    tagMap.put(Math, asList("cf:math"));
    tagMap.put(NumberTheory, asList("cf:number theory"));
    tagMap.put(Matrices, asList("cf:matrices"));
    tagMap.put(Implementation, asList("cf:ternary search"));
    tagMap.put(DynamicProgramming, asList("cf:dp"));
    tagMap.put(Implementation, asList("cf:two pointers"));
    tagMap.put(Greedy, asList("cf:greedy"));
    tagMap.put(DivideConquer, asList("cf:divide and conquer"));
    tagMap.put(DataStructures, asList("cf:dsu", "cf:data structures"));
    tagMap.put(Bitmasks, asList("cf:bitmasks"));
    tagMap.put(BinarySearch, asList("cf:binary search"));
    tagMap.put(Implementation, asList("cf:meet in the middle"));
    tagMap.put(Implementation, asList("cf:implementation"));
    tagMap.put(Implementation, asList("cf:constructive algorithms"));
    tagMap.put(BruteForce, asList("cf:brute force"));
    tagMap.put(Adhoc, asList("cf:sortings"));
    tagMap.put(Hashing, asList("cf:hashing"));
    tagMap.put(FFT, asList("cf:fft"));

    // cc
    tagMap.put(Strings, asList("cc:z-algorithm", "cc:zfunction", "cc:kmp", "cc:aho-corasick", "cc:manacher", "cc:string", "cc:trie", "cc:tries", "cc:grammar-parsing", "cc:string-parsing", "cc:string-process", "cc:strings", "cc:suffix-array", "cc:suffix-auto", "cc:suffix-trees", "cc:suffix_automata", "cc:regex"));
    tagMap.put(GraphsTrees, asList("cc:prim", "cc:unrooted-trees", "cc:colouring", "cc:treeroot", "cc:trees", "cc:rooted-trees", "cc:lca", "cc:spanningtree", "cc:kirchhoff", "cc:shortest-path", "cc:hamiltonian", "cc:traversal", "cc:kruskal", "cc:tree", "cc:tree-based", "cc:floyd-warshall", "cc:graph", "cc:graph-sqrt", "cc:graph-theory", "cc:graph-traversal", "cc:graphs", "cc:euler-tour", "cc:eulerian", "cc:dual-graph", "cc:dominator", "cc:edge-cover", "cc:directed", "cc:dfs", "cc:digraph", "cc:dijkstra", "cc:dfs-order", "cc:bipartiteness", "cc:central-nodes", "cc:connected", "cc:centroid-decomp", "cc:bfs", "cc:bellman-ford", "cc:basic-graph", "cc:bridges"));
    tagMap.put(Flows, asList("cc:flow", "cc:dilworth", "cc:bipartite", "cc:max-flow", "cc:maxflow", "cc:min-cost-flow", "cc:mincut-maxflow", "cc:minimum-cut", "cc:maximum-flow", "cc:matching"));
    tagMap.put(Games, asList("cc:zero-sum-game", "cc:sprague-grundy", "cc:nim", "cc:impartial-game", "cc:hackenbush", "cc:game", "cc:game-theory", "cc:games"));
    tagMap.put(Probabilities, asList("cc:probability", "cc:expectation", "cc:expected-value"));
    tagMap.put(Combinatorics, asList("cc:pigeonhole", "cc:lucas-theorem", "cc:inclusn-exclusn", "cc:derangement", "cc:permutation", "cc:permutations", "cc:combinations", "cc:combinatorial", "cc:combinatorics", "cc:burnside", "cc:binomial"));
    tagMap.put(Geometry, asList("cc:voronoi", "cc:geometry", "cc:polygons", "cc:convex-hull", "cc:convex-polygon", "cc:trigonometry", "cc:line-clipping", "cc:line-sweep", "cc:angles", "cc:sweepline"));
    tagMap.put(Math, asList("cc:numeral-systems", "cc:lagrange", "cc:polynomial", "cc:divisors", "cc:recurrence", "cc:recurrences", "cc:fibonacci", "cc:log", "cc:linear-comb", "cc:math", "cc:mathematics", "cc:maths", "cc:integrals", "cc:division", "cc:interpolation", "cc:gcd", "cc:binary", "cc:catalan", "cc:binary-numbers", "cc:counting", "cc:big-integer", "cc:biginteger", "cc:cross-product", "cc:basic-math", "cc:basic-maths", "cc:basic_math", "cc:arithmetic", "cc:algebra", "cc:advanced-math"));
    tagMap.put(NumberTheory, asList("cc:sieve", "cc:prime", "cc:rabin-karp", "cc:prime-factor", "cc:seive", "cc:primegenerator", "cc:primenumbers", "cc:primitive-root", "cc:pollard-rho", "cc:number-theory", "cc:euclids-algo", "cc:lcm", "cc:chinese-rem", "cc:miller-rabin", "cc:crt", "cc:bezout-identity", "cc:mobius_function", "cc:modular", "cc:modular-arith", "cc:modular-inv", "cc:modulo", "cc:modulo-ops"));
    tagMap.put(Matrices, asList("cc:matrices", "cc:matrix", "cc:matrix-expo", "cc:linear-algebra", "cc:gauss-elim"));
    tagMap.put(Implementation, asList("cc:optimization", "cc:construction", "cc:precalculation", "cc:precomputation", "cc:precomputing", "cc:prefix-minimum", "cc:prefix-sum", "cc:prefix-sums", "cc:preprocessing", "cc:constructions", "cc:interactive", "cc:constructive", "cc:computation", "cc:intervals", "cc:basics", "cc:basic-prog", "cc:basic", "cc:implemenatation", "cc:implementation", "cc:basic-implement", "cc:array", "cc:array-string", "cc:arrays", "cc:backtracking"));
    tagMap.put(DynamicProgramming, asList("cc:tree-dp", "cc:memoization", "cc:knapsack", "cc:lis", "cc:lcp", "cc:lcs", "cc:kadane", "cc:edit-distance", "cc:dp", "cc:dp+bitmask", "cc:dynamic-prog", "cc:dp+lcs", "cc:dp+probability", "cc:digit-dp"));
    tagMap.put(Implementation, asList("cc:two-pointers"));
    tagMap.put(Greedy, asList("cc:greedy", "cc:greedy-algo"));
    tagMap.put(DivideConquer, asList("cc:divide-and-conq"));
    tagMap.put(DataStructures, asList("cc:union-find", "cc:sqrt-decomp", "cc:stack", "cc:sparse-tables", "cc:splay-tree", "cc:splay-trees", "cc:segment-tree", "cc:segment-trees", "cc:segtree", "cc:treap", "cc:treaps", "cc:rmq", "cc:range-queries", "cc:range-sum", "cc:range-tree", "cc:range-trees", "cc:persistence", "cc:link-cut-tree", "cc:list", "cc:maps", "cc:lazypropagation", "cc:kd-tree", "cc:fenwick", "cc:fenwick-tree", "cc:heap", "cc:heaps", "cc:heavy-decomp", "cc:heavy-light", "cc:hld", "cc:dsu", "cc:data-structure", "cc:disjoint-set", "cc:decomposition", "cc:deque", "cc:binarytrees", "cc:binaryheap", "cc:binary-tree", "cc:2d-fenwick", "cc:balanced", "cc:balanced-tree"));
    tagMap.put(Bitmasks, asList("cc:bit", "cc:bitmasking", "cc:bitmasks", "cc:bits", "cc:bitset", "cc:bitwise"));
    tagMap.put(BinarySearch, asList("cc:binarysearch", "cc:binary-search"));
    tagMap.put(Implementation, asList("cc:meet-in-middle"));
    tagMap.put(BruteForce, asList("cc:brute", "cc:brute-force", "cc:bruteforce"));
    tagMap.put(Adhoc, asList("cc:calendar-calc", "cc:sort", "cc:sorting", "cc:arbitrary", "cc:palindrome", "cc:palindromes", "cc:parentheses", "cc:parity", "cc:mergesort", "cc:hamming-dist", "cc:logic", "cc:loop", "cc:looping", "cc:loops", "cc:finding-pattern", "cc:exponentiation", "cc:fast-expo", "cc:fastmodexp", "cc:conditions", "cc:counting_sort", "cc:2d", "cc:ad", "cc:adhoc", "cc:ad-hoc"));
    tagMap.put(Hashing, asList("cc:rolling-hash", "cc:hash-map", "cc:hashing", "cc:hashmaps", "cc:string-hashing"));
    tagMap.put(FFT, asList("cc:fourier", "cc:fft", "cc:karatsuba"));

    //sp
    tagMap.put(Strings, asList("sp:text-processing", "sp:parsing", "sp:validation", "sp:string-matchinig", "sp:regular-expressions", "sp:kmp-algorithm"));
    tagMap.put(GraphsTrees, asList("sp:graph", "sp:graph-theory", "sp:shortest-path", "sp:hamiltonian-circuit", "sp:euler-circuit", "sp:domination", "sp:graph-coloring", "sp:ramsey-numbers", "sp:scc", "sp:longest-path", "sp:vertex-cover", "sp:mst", "sp:independent-set"));
    tagMap.put(GraphsTrees, asList("sp:dfs", "sp:bfs", "sp:flood-fill", "sp:kruskal-s-algorithm", "sp:prim-s-algorithm", "sp:dijkstra-s-algorithm"));
    tagMap.put(Flows, asList("sp:flow", "sp:max-flow", "sp:min-cut", "sp:max-cut", "sp:matching"));
    tagMap.put(Games, asList("sp:game-theory", "sp:game", "sp:board-game", "sp:carrom", "sp:chess"));
    tagMap.put(Probabilities, asList("sp:probability-theory"));
    tagMap.put(Combinatorics, asList("sp:combinatorics", "sp:partition", "sp:stirling"));
    tagMap.put(Geometry, asList("sp:geometry", "sp:convex-hull", "sp:plane-geometry", "closest-pair", "sp:topology", "sp:metric-space", "sp:knot-theory"));
    tagMap.put(Math, asList("sp:math", "sp:simple-math", "sp:big-numbers", "sp:factorial", "sp:positional-systems", "sp:algebra"));
    tagMap.put(Math, asList("sp:calculus", "sp:differential-equation", "real-function", "zero-of-function", "linear-algebra", "linear-system"));
    tagMap.put(Math, asList("sp:linear-programming", "sp:newton-raphson"));
    tagMap.put(NumberTheory, asList("sp:number-theory", "sp:gcd", "sp:totient", "sp:prime-numbers", "sp:factorisation", "sp:lcm"));
    tagMap.put(NumberTheory, asList("sp:sieve-of-eratosthenes"));
    tagMap.put(Matrices, asList("sp:matrix"));
    tagMap.put(Implementation, asList("sp:implementation"));
    tagMap.put(DynamicProgramming, asList("sp:knapsack", "sp:lcs", "sp:edit-distance", "sp:dynamic-programming"));
    tagMap.put(Greedy, asList("sp:greedy"));
    tagMap.put(DivideConquer, asList("sp:divide-and-conquer"));
    tagMap.put(DataStructures, asList("sp:rmq", "sp:data-structure", "sp:tree", "sp:binary-tree", "sp:avl-tree", "sp:red-black-tree", "sp:segment-tree", "sp:splay-tree", "sp:trie"));
    tagMap.put(DataStructures, asList("sp:heap", "sp:binomial-heap", "sp:fibonacci-heap", "sp:disjoint-set"));
    tagMap.put(Bitmasks, asList("sp:bitmasks"));
    tagMap.put(BinarySearch, asList("sp:binary-search"));
    tagMap.put(Implementation, asList("sp:prefix-sum"));
    tagMap.put(Implementation, asList("sp:sorting"));
    tagMap.put(Implementation, asList("sp:array", "sp:associative-array", "sp:2d-array", "sp:stack", "sp:queue", "sp:priority-queue"));
    tagMap.put(Implementation, asList("sp:list", "sp:singly-linked-list", "sp:doubly-linked-list"));
    tagMap.put(Implementation, asList("sp:recursion", "sp:branch-and-bound", "sp:backtracking", "sp:sliding-window"));
    tagMap.put(BruteForce, asList("sp:brute-force"));
    tagMap.put(Adhoc, asList("sp:collatz", "sp:logic", "sp:quick-sort", "sp:subset-sum"));
    tagMap.put(Adhoc, asList("sp:set-theory", "sp:set-cover", "sp:partial-order", "sp:linear-order", "sp:group-theory", "sp:ring-theory"));
    tagMap.put(Adhoc, asList("sp:extreme-principle"));
    tagMap.put(Hashing, asList("sp:hash-table"));

    invMap = new HashMap<>();
    tagMap.entries()
      .stream()
      .forEach(e -> e.getValue().stream()
        .forEach(k -> invMap.put(k, e.getKey())));
  }

  public static Tag mapTag(Site site, String tag) {
    String siteTag = String.format("%s:%s", site.getShortName(), tag);
    return invMap.getOrDefault(siteTag, UNTAGGED);
  }

  public static List<String> tagsOfSite(Site site) {
    String sname = site.getShortName();
    return invMap.keySet()
      .stream()
      .filter(t -> t.startsWith(sname + ":"))
      .map(t -> t.substring(sname.length() + 1))
      .collect(Collectors.toList());
  }
}
