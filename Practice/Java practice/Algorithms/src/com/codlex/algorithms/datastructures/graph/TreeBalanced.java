package com.codlex.algorithms.datastructures.graph;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class TreeBalanced {
	
	private int leafMaxDepth = 0;
	private int leafMinDepth = Integer.MAX_VALUE;
	
	public boolean isBalanced() {
		return this.leafMaxDepth - this.leafMinDepth <= 1;
	}
	
	
	public TreeBalanced(TreeNode root) {
		dfs(0, root);
	}
	
	private void dfs(int depth, TreeNode node) {
		if (node.isLeaf()) {
			leafMaxDepth = Math.max(leafMaxDepth, depth);
			leafMinDepth = Math.min(leafMinDepth, depth);
		}
		
		for (TreeNode child : node.children) {
			dfs(depth + 1, child);
		}
	}

	public static class TreeNode {
		public List<TreeNode> children = new ArrayList<TreeNode>();
		public boolean isLeaf() {
			return this.children.isEmpty();
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode();
		TreeNode secondLevel = new TreeNode();
		TreeNode thirdLevel = new TreeNode();
		TreeNode firstLevelLeaf = new TreeNode();
		
//		root.children.add(secondLevel);
//		root.children.add(firstLevelLeaf);
//		
//		secondLevel.children.add(thirdLevel);
//		thirdLevel.children.add(new TreeNode());
//		
//		firstLevelLeaf.children.add(new TreeNode());
		
		System.out.println(new TreeBalanced(root).isBalanced());
		
	
	}
}
