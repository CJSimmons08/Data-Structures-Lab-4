import java.util.function.BiPredicate;

class BinomialHeap<K> {
    K key;
    int height;
    PList<BinomialHeap<K>> children;
    BiPredicate<K, K> lessEq;

    BinomialHeap(K k, int h, PList<BinomialHeap<K>> kids, BiPredicate<K, K> le) {
        this.key = k;
        this.height = h;
        this.children = kids;
        this.lessEq = le;
    }

    /*
     * @precondition this.height == other.height
     */
    BinomialHeap<K> link(BinomialHeap<K> other) {
        if (this.height != other.height)
            throw new UnsupportedOperationException("attempt to link trees of different height");
        if (lessEq.test(other.key, this.key)) {
            PList<BinomialHeap<K>> kids = PList.addFront(other, this.children);
            return new BinomialHeap<>(this.key, this.height + 1, kids, lessEq);
        } else {
            PList<BinomialHeap<K>> kids = PList.addFront(this, other.children);
            return new BinomialHeap<>(other.key, other.height + 1, kids, lessEq);
        }
    }

    /**
     * TODO
     * <p>
     * The isHeap method checks whether or not the subtree of this node
     * satisfies the heap property.
     */
    boolean isHeap() {
        BinomialHeap<K> other = PList.getFirst(this.children);
        if(lessEq.test(other.key, this.key)){
            return false;
        }
        if(lessEq.test(this.key, other.key)){
            return other.isHeap();
        }
        else if(lessEq.equals(other.lessEq)){
            return other.isHeap();
        }
        return true;  // replace this line with your code
    }

    public String toString() {
        String ret = "(" + key.toString();
        if (children != null)
            ret = ret + " " + children.toString();
        return ret + ")";
    }

}

public class BinomialQueue<K> {
    PList<BinomialHeap<K>> forest;
    BiPredicate<K, K> lessEq;

    public BinomialQueue(BiPredicate<K, K> le) {
        forest = null;
        lessEq = le;
    }

    public void push(K key) {
        BinomialHeap<K> heap = new BinomialHeap<>(key, 0, null, lessEq);
        this.forest = insert(heap, this.forest);
    }

    public K pop() {
        BinomialHeap<K> max = PList.find_max(this.forest, (h1,h2) -> this.lessEq.test(h1.key, h2.key));
        this.forest = PList.remove(max, this.forest);
        PList<BinomialHeap<K>> kids = PList.reverse(max.children, null);
        this.forest = merge(this.forest, kids);
        return max.key;
    }

    public boolean isEmpty() {
        return forest == null;
    }

    /**
     * TODO
     * The isHeap method returns whether or not the Binomial Queue (a forest of Binomial Trees)
     * satisfies the heap property.
     */
    public boolean isHeap() {
        /*First heap in the forest*/
        PList<BinomialHeap<K>> first = forest;
        /*Next heap after the first one*/
        PList<BinomialHeap<K>> other = PList.getNext(forest);

        while(other != null){
            /*Creating these variables to avoid calling "Plist.getFirst" at every if statement in the loop
            * which I think will save run time? unsure*/
            K firstKey = PList.getFirst(first).key;
            K otherKey = PList.getFirst(other).key;
            System.out.printf("Forest: \n" + forest + "\n");
            System.out.printf("First: \n" + first + "\n");
            System.out.printf("First Key: \n" + firstKey + "\n");
            System.out.printf("Other: \n" + other + "\n");
            System.out.printf("Other Key: \n" + otherKey + "\n");
            if(lessEq.test(firstKey, otherKey)){
                return false;
            }
            first = other;
            other = PList.getNext(other);
        }
        return true;
    }

    public String toString() {
        if (this.forest == null)
            return "";
        else
            return this.forest.toString();
    }

    /**********************************
     * Helper functions
     */

    /**
     * TODO
     * The insert method is analogous to binary addition. That is,
     * it inserts the node n into the list ns to produce a new list
     * that is still sorted by height.
     *
     * @param <K> The type of the keys.
     * @param n   The node to insert (must not be null).
     * @param ns  The binomial forest into which to insert, ordered by height. (may be null)
     * @return A new binomial forest that includes the new node.
     */
    static <K> PList<BinomialHeap<K>>
    insert(BinomialHeap<K> n, PList<BinomialHeap<K>> ns) {
        if(ns == null){
            ns = PList.addFront(n, null);
            return ns;
        }
        if(n.height == PList.getFirst(ns).height){
            n = n.link(PList.getFirst(ns));
            ns = PList.remove(PList.getFirst(ns), ns);
            ns = PList.addFront(n, ns);
        }
        return insert(n, PList.getNext(ns));
    }

    /**
     * TODO
     * The merge method is analogous to the merge part of merge sort. That is,
     * it takes two lists that are sorted (by height) and returns a new list that
     * contains the elements of both lists, and the new list is sorted by height.
     *
     * @param ns1
     * @param ns2
     * @return A list that is sorted and contains all and only the elements in ns1 and ns2.
     */
    static <K> PList<BinomialHeap<K>>
    merge(PList<BinomialHeap<K>> ns1, PList<BinomialHeap<K>> ns2) {
        PList<BinomialHeap<K>> newForest;
        if(ns1 == null && ns2 == null){
            return null;
        }
        else if(ns2 == null){
            newForest = new PList<>(PList.getFirst(ns1), null);
            ns1 = PList.remove(PList.getFirst(ns1), ns1);
        }
        else if(ns1 == null){
            newForest = new PList<>(PList.getFirst(ns2), null);
            ns2 = PList.remove(PList.getFirst(ns2), ns2);
        }
        else if(PList.getFirst(ns1).height < PList.getFirst(ns2).height){
            newForest = new PList<>(PList.getFirst(ns1), null);
            ns1 = PList.remove(PList.getFirst(ns1), ns1);
        }
        else{
            newForest = new PList<>(PList.getFirst(ns2), null);
            ns2 = PList.remove(PList.getFirst(ns2), ns2);
        }

        while(ns1 != null || ns2 != null){
            if(ns1 == null){
                newForest = PList.addFront(PList.getFirst(ns2), newForest);
                ns2 = PList.remove(PList.getFirst(ns2), ns2);
                continue;
            }
            else if(ns2 == null){
                newForest = PList.addFront(PList.getFirst(ns1), newForest);
                ns1 = PList.remove(PList.getFirst(ns1), ns1);
                continue;
            }
            if(PList.getFirst(ns1).height < PList.getFirst(ns2).height){
                newForest = PList.addFront(PList.getFirst(ns1), newForest);
                ns1 = PList.remove(PList.getFirst(ns1), ns1);
                continue;
            }
            if(PList.getFirst(ns1).height == PList.getFirst(ns2).height){
                newForest = PList.addFront((PList.getFirst(ns1).link(PList.getFirst(ns2))), newForest);
                ns1 = PList.remove(PList.getFirst(ns1), ns1);
                ns2 = PList.remove(PList.getFirst(ns2), ns2);
                continue;
            }
            newForest = PList.addFront(PList.getFirst(ns2), newForest);
            ns2 = PList.remove(PList.getFirst(ns2), ns2);
        }
        newForest = PList.reverse(newForest, null);
        return newForest;
    }
    
}
