/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BSTBase;

import ProjectClasses.Country;
import ProjectClasses.Pair;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hugo
 */
public class TwoDTree<E extends Comparable<E>> implements BSTInterface {

    //----------- end of nested Node class -----------
    protected Node<E> root = null;     // root of the tree

    /* Constructs an empty binary search tree.  */
    public TwoDTree() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E element) {
        root = insert(element, root);
    }

    

   

   

    class Comparator2DTLong implements Comparator<E> {

        @Override
        public int compare(E c1, E c2) {
            Pair p1 = (Pair) c1;
            Pair p2 = (Pair) c2;

            if (p1.getCountry().getLongitude() - p2.getCountry().getLongitude() < 0) {
                return -1;
            } else {
                return 1;
            }

        }
    }

    class Comparator2DTLat implements Comparator<E> {

        @Override
        public int compare(E c1, E c2) {
            Pair p1 = (Pair) c1;
            Pair p2 = (Pair) c2;

            if (p1.getCountry().getLatitude() - p2.getCountry().getLatitude() < 0) {
                return -1;
            } else {
                return 1;
            }

        }
    }

    private Node<E> insert(E element, Node<E> node) {
        int depth = 0;
        return insert(element, node, depth);
    }

    private Node<E> insert(E element, Node<E> node, int depth) {
        Comparator2DTLat c2dtLat = new Comparator2DTLat();
        Comparator2DTLong c2dtLong = new Comparator2DTLong();
        if (node == null) {
            return new Node<E>(element, null, null);
        }
        if (depth % 2 == 0) {
            if (c2dtLat.compare(node.getElement(), element) > 0) {
                depth++;
                node.setLeft(insert(element, node.getLeft(), depth));
            } else if (c2dtLat.compare(node.getElement(), element) < 0) {
                depth++;
                node.setRight(insert(element, node.getRight(), depth));
            }

        } else {
            if (c2dtLong.compare(node.getElement(), element) < 0) {
                depth++;
                node.setRight(insert(element, node.getRight(), depth));
            } else if (c2dtLong.compare(node.getElement(), element) > 0) {
                depth++;
                node.setLeft(insert(element, node.getLeft(), depth));
            }
        }

        return node;
    }

    public void remove(E element) {
        root = remove(element, root);
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    @Override
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;

    }

    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    @Override
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

    protected Node<E> findCountry(double lat, double log) {
        int depth = 0;
        return findCountry(lat, log, depth, root);
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
    protected Node<E> findCountry(double lat, double log, int depth, Node<E> node) {
        if (node == null) {
            return null;
        }
        Pair par = (Pair) node.getElement();

        if ((par.getCountry().getLongitude() == log && par.getCountry().getLatitude() == lat)) {
            return node;
        }
        if (depth % 2 == 0) {
            if (par.getCountry().getLatitude() - lat > 0) {
                depth++;
                return findCountry(lat, log, depth, node.getLeft());
            } else if (par.getCountry().getLatitude() - lat < 0) {
                depth++;
                return findCountry(lat, log, depth, node.getRight());
            }

        } else {
            if (par.getCountry().getLongitude() - log > 0) {
                depth++;
                return findCountry(lat, log, depth, node.getLeft());
            } else if (par.getCountry().getLongitude() - log < 0) {
                depth++;
                return findCountry(lat, log, depth, node.getRight());
            }
        }
        return node;
    }

    protected Country findNearest(double lat, double log) {
        int depth = 0;
        Pair pairDest = new Pair(new Country(lat, log));
        Node<E> nodeDest = new Node(pairDest, null, null);
        LinkedList<Pair> passedCountries = new LinkedList<>();
        passedCountries = findNearest(depth, root, nodeDest, passedCountries);
        double minDistAux = calcMinDist(passedCountries, pairDest);
        if(minDistAux!=-1){
            LinkedList<Pair> countriesByArea = getCountriesByArea(lat+minDistAux, lat-minDistAux, log+minDistAux, log-minDistAux);
            return calcClosestCountry(countriesByArea, pairDest);
        }
        return null;
    }
    
    private double calcMinDist(LinkedList<Pair> passedCountries, Pair pairDest ) {
        double minDist = Double.MAX_VALUE;
        for(Pair c : passedCountries){
            double dist = distance(c.getCountry().getLatitude(), c.getCountry().getLongitude(), pairDest.getCountry().getLatitude(), pairDest.getCountry().getLongitude());
            if(dist < minDist){
                minDist = dist;
            }
        }
        return minDist;
        
    }
    private Country calcClosestCountry(LinkedList<Pair> passedCountries, Pair pairDest) {
        Country closestCountry = null;
        double minDist = Double.MAX_VALUE;
        for(Pair c : passedCountries){
            double dist = distance(c.getCountry().getLatitude(), c.getCountry().getLongitude(), pairDest.getCountry().getLatitude(), pairDest.getCountry().getLongitude());
            if(dist < minDist){
                minDist = dist;
                closestCountry = c.getCountry();
            }
        }
        return closestCountry;
        
    }
    
    private LinkedList<Pair> findNearest(int depth, Node<E> node, Node<E> nodeDest, LinkedList<Pair> passedCountries) {
        Comparator2DTLat c2dtLat = new Comparator2DTLat();
        Comparator2DTLong c2dtLong = new Comparator2DTLong();
        if (node == null) {
            return passedCountries;
        }
        Pair newPair = (Pair) node.getElement();
        passedCountries.add(newPair);
        
        if (depth % 2 == 0) {
            if (c2dtLat.compare(node.getElement(), nodeDest.getElement()) > 0) {
                depth++;
                findNearest(depth, node.getLeft(), nodeDest, passedCountries);
            } else if (c2dtLat.compare(node.getElement(), nodeDest.getElement()) < 0) {
                depth++;
                findNearest(depth, node.getRight(), nodeDest, passedCountries);
            }

        } else {
            if (c2dtLong.compare(node.getElement(), nodeDest.getElement()) < 0) {
                depth++;
                findNearest(depth, node.getRight(), nodeDest, passedCountries);
            } else if (c2dtLong.compare(node.getElement(), nodeDest.getElement()) > 0) {
                depth++;
                findNearest(depth, node.getLeft(), nodeDest, passedCountries);
            }
        }
        
        return passedCountries;
    }

    
     
    public static double distance(double lat1, double lon1, double lat2, double lon2) {    
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            final double R = 6371e3;
            double x = lat2 - lat1;
            double y = lon2 - lon1;
            double theta1 = Math.toRadians(lat1);
            double theta2 = Math.toRadians(lat2);
            double deltaTheta = Math.toRadians(x);
            double deltaLambda = Math.toRadians(y);
            double a = Math.sin(deltaTheta / 2) * Math.sin(deltaTheta / 2) + Math.cos(theta1) * Math.cos(theta2) * Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = R * c * Math.pow(10, -3);
            return d;
        }
    }
    
     public LinkedList<Pair> getCountriesByArea(double maxLatitude, double minLatitude, double maxLongitude, double minLongitude) {
        LinkedList<Pair> countriesByArea = new LinkedList<>();
        int depth = 0;
        getCountriesByArea(countriesByArea, root, maxLatitude, minLatitude, maxLongitude, minLongitude, depth);
        return countriesByArea;
    }

    protected LinkedList<Pair> getCountriesByArea(LinkedList<Pair> countriesByArea, Node<E> node, double maxLatitude, double minLatitude, double maxLongitude, double minLongitude, int depth) {
        if (node == null) {
            return countriesByArea;
        }
        Pair pair = (Pair) node.getElement();
        
        if (depth % 2 == 0) {
            if (pair.getCountry().getLatitude()> maxLatitude) {
                getCountriesByArea(countriesByArea, node.getLeft(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
            } else {
                if (pair.getCountry().getLatitude() < minLatitude) {
                    getCountriesByArea(countriesByArea, node.getRight(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
                } else {
                    if (pair.getCountry().getLongitude() >= minLongitude && pair.getCountry().getLongitude() <= maxLongitude) {
                        countriesByArea.add(pair);
                    }
                    getCountriesByArea(countriesByArea, node.getRight(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
                    getCountriesByArea(countriesByArea, node.getLeft(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
                }
            }
} else {
            if (pair.getCountry().getLongitude() > maxLongitude) {
                getCountriesByArea(countriesByArea, node.getLeft(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
            } else {
                if (pair.getCountry().getLongitude() < minLongitude) {
                    getCountriesByArea(countriesByArea, node.getRight(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
                } else {
                    if (pair.getCountry().getLatitude() >= minLatitude && pair.getCountry().getLatitude() <= maxLatitude) {
                        countriesByArea.add(pair);
                    }
                    getCountriesByArea(countriesByArea, node.getLeft(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
                    getCountriesByArea(countriesByArea, node.getRight(), maxLatitude, minLatitude, maxLongitude, minLongitude, depth+1);
                   
                }
            }
        }
        return countriesByArea;
    }
    
    @Override
    public Map nodesByLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Object element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
