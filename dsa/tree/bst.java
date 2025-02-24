class BinarySearchTree {
    // Node structure
    class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private TreeNode root;

    // Insert a node into the BST
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else if (val > root.val) root.right = insertRec(root.right, val);
        return root;
    }

    // Search for a value in the BST
    public boolean search(int val) {
        return searchRec(root, val);
    }

    private boolean searchRec(TreeNode root, int val) {
        if (root == null) return false;
        if (root.val == val) return true;
        return val < root.val ? searchRec(root.left, val) : searchRec(root.right, val);
    }

    // Delete a node from BST
    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private TreeNode deleteRec(TreeNode root, int val) {
        if (root == null) return null;

        if (val < root.val) root.left = deleteRec(root.left, val);
        else if (val > root.val) root.right = deleteRec(root.right, val);
        else {
            // Case 1: No child or one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 2: Two children - find inorder successor
            TreeNode successor = minValueNode(root.right);
            root.val = successor.val;
            root.right = deleteRec(root.right, successor.val);
        }
        return root;
    }

    private TreeNode minValueNode(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Inorder Traversal (sorted order)
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode root) {
        if (root == null) return;
        inorderRec(root.left);
        System.out.print(root.val + " ");
        inorderRec(root.right);
    }

    // Main method to test the BST
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        System.out.println("Inorder Traversal:");
        bst.inorder(); // Expected output: 2 3 4 5 6 7 8

        System.out.println("Search 4: " + bst.search(4)); // Expected: true
        System.out.println("Search 9: " + bst.search(9)); // Expected: false

        bst.delete(3);
        System.out.println("Inorder after deleting 3:");
        bst.inorder(); // Expected output: 2 4 5 6 7 8
    }
}
