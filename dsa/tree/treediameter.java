class TreeDiameter {
    int diameter = 0;

    int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    int height(TreeNode node) {
        if (node == null) return 0;
        int left = height(node.left);
        int right = height(node.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
}
