package BSTBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 */
public class BST<E extends Comparable<E>> implements BSTInterface<E> {

    

    
    //----------- end of nested Node class -----------
    protected Node<E> root = null;     // root of the tree

    /* Constructs an empty binary search tree. */
    public BST() {
        root = null;
    }

    /*
    * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
    }

    /*
    * Verifies if the tree is empty
    * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /*
    * Inserts an element in the tree.
     */
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {

        if (node == null) {
            return new Node(element, null, null);
        }
        if (node.getElement().compareTo(element) > 0) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (node.getElement().compareTo(element) < 0) {
            node.setRight(insert(element, node.getRight()));
        }
        return node;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary
     * Search Tree.
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
        } else {
            node.setRight(remove(element, node.getRight()));
        }

        return node;
    }

    /*
    * Returns the number of nodes in the tree.
    * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return (size(node.left) + 1 + size(node.right));
        }
    }

    /*
    * Returns the height of the tree
    * @return height 
     */
    public int height() {
        return height(root);
    }

    /*
    * Returns the height of the subtree rooted at Node node.
    * @param node A valid Node within the tree
    * @return height 
     */
    protected int height(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            int lDepth = height(node.left);
            int rDepth = height(node.right);

            /* use the larger one */
            if (lDepth > rDepth) {
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }

    }

    /**
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    public E smallestElement() {
        return smallestElement(root);
    }

    protected E smallestElement(Node<E> node) {

        /* loop down to find the leftmost leaf */
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return (node.getElement());
    }
    protected Node<E> find(E element){
        return find(element, root);
    }
    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     *
     * This method despite not being essential is very useful. It is written
     * here in order to be used by this class and its subclasses avoiding
     * recoding. So its access level is protected
     */
    protected Node<E> find(E element, Node<E> node) {
        // Base Cases: root is null or key is present at root 
        if (node == null || node.getElement() == element) {
            return node;
        }

        // val is greater than root's key 
        if (node.getElement().compareTo(element) > 0) {
            return find(element, node.getLeft());
        }

        // val is less than root's key 
        return find(element, node.getRight());
    }
    
    

    /*
   * Returns an iterable collection of elements of the tree, reported in in-order.
   * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an in-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;

    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an pre-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    public Iterable<E> posOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            posOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;

    }

    /**
     * Adds positions of the subtree rooted at Node node to the given snapshot
     * using an post-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void posOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        posOrderSubtree(node.getLeft(), snapshot);
        posOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
    }

    /*
    * Returns a map with a list of nodes by each tree level.
    * @return a map with a list of nodes by each tree level
     */
    public Map<Integer, List<E>> nodesByLevel() {
        int level = 0;
        Map<Integer, List<E>> result = new HashMap<>();
        processBstByLevel(root, result, level);
       
        return result;
    }

    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//#########################################################################
    /**
     * Returns a string representation of the tree. Draw the tree horizontally
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null) {
            return;
        }
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                sb.append("|\t");
            }
            sb.append("|-------" + root.getElement() + "\n");
        } else {
            sb.append(root.getElement() + "\n");
        }
        toStringRec(root.getLeft(), level + 1, sb);
    }

} //----------- end of BST class -----------

