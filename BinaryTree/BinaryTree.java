// BinaryTree.java
import java.lang.Comparable;
import java.util.List;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class BinaryTree<T extends Comparable<T>> {

    private final T value;
    private final Supplier<BinaryTree<T>> leftTree;
    private final Supplier<BinaryTree<T>> rightTree;
    private final boolean amIEmpty;

    // Use Supplier to freeze left and right subtrees
    public BinaryTree(T value,
                      Supplier<BinaryTree<T>> left,
                      Supplier<BinaryTree<T>> right) {
        this.value = value;
        this.leftTree = left;
        this.rightTree = right;
        this.amIEmpty = false;
    }

    private BinaryTree() {
        this.value = null;
        this.leftTree = null;
        this.rightTree = null;
        this.amIEmpty = true;
    }

    public static <T extends Comparable<T>> BinaryTree<T> makeEmpty() {
        return new BinaryTree<T>();
    }

    public boolean isEmpty() {
        return this.amIEmpty;
    }

    public static <T extends Comparable<T>> BinaryTree<T>
        fromList(List<T> list) {
        BinaryTree<T> result = BinaryTree.makeEmpty();
        for (T item : list)
            result = result.add(item);

        return result;
    }

    public T value() {
        if (this.isEmpty())
            throw new IllegalArgumentException("getNodeValue: empty tree!"); 
        return this.value;
    }

    public BinaryTree<T> leftTree() {
        if (this.isEmpty())
            throw new IllegalArgumentException("leftTree: empty tree!");
        return this.leftTree.get();
    }
        
    public BinaryTree<T> rightTree() {
        if (this.isEmpty())
            throw new IllegalArgumentException("rightTree: empty tree!");
        return this.rightTree.get();
    }
        
    public BinaryTree<T> add(T thing) {
        if (this.isEmpty())
            return new BinaryTree<>(thing,
                                    ()-> makeEmpty(),
                                    ()-> makeEmpty());

        int compareResult = this.value().compareTo(thing);
        if (compareResult == 0)
            //already in tree
            return this;
        
        else if (compareResult > 0)
            //add to left tree
            return new BinaryTree<>(this.value(),
                                    ()-> this.leftTree().add(thing),
                                    ()-> this.rightTree());
        else //add to right tree
            return new BinaryTree<>(this.value(), 
                                    ()-> this.leftTree(),
                                    ()-> this.rightTree().add(thing));
    }

    public Lazy<Boolean> contains(T searchItem) {
        // Insert code here.
        return new Lazy<>(true);
    }
    

    public boolean eagerContains(T thing) {
        if (this.isEmpty())
            return false;

        int compareResult = this.value().compareTo(thing);
        if (compareResult == 0)
            return true;

        else if (compareResult > 0)
            return this.leftTree().eagerContains(thing);

        else
            return this.rightTree().eagerContains(thing);
    }

    public <U extends Comparable<U>> BinaryTree<U> map(Function<T,U> f) {
        if (this.isEmpty())
            return BinaryTree.makeEmpty();
        else
            return new BinaryTree<>(f.apply(this.value()),
                                    ()-> this.leftTree().map(f),
                                    ()-> this.rightTree().map(f));
    }

    public void preOrder(Consumer<T> action) {
        if (!this.isEmpty()) {
            action.accept(this.value());
            this.leftTree().preOrder(action);
            this.rightTree().preOrder(action);
        }
    }
}
