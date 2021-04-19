package binary_tree;

public class BinaryTree {
    private Node root;

    public void addNode(String data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
        } else {
            Node currentNode = root;
            while (currentNode != null) {
                int comparisonResult = currentNode.getData().compareTo(data);
                if (comparisonResult > 0) {
                    if (currentNode.getLeft() == null) currentNode.setLeft(node);
                    currentNode = currentNode.getLeft();
                } else if (comparisonResult < 0) {
                    if (currentNode.getRight() == null) currentNode.setRight(node);
                    currentNode = currentNode.getRight();
                } else currentNode = null;
            }
        }
    }

    public boolean isContains(String data) {
        Node currentNode = root;
        while (currentNode != null) {
            int comparisonResult = currentNode.getData().compareTo(data);
            if (comparisonResult > 0) {
                currentNode = currentNode.getLeft();
            } else if (comparisonResult < 0) {
                currentNode = currentNode.getRight();
            } else return true;
        }
        return false;
    }

    public Node getRoot() {
        return root;
    }
}
