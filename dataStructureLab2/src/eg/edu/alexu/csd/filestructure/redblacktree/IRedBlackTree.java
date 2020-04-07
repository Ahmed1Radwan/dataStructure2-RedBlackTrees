package eg.edu.alexu.csd.filestructure.redblacktree;

public interface IRedBlackTree<T extends Comparable<T>,V> {
	
	public INode<T,V> getRoot();
	public boolean isEmpty();
	public void clear();
	public V search(T key);
	public boolean contains(T key);
	public void insert(T key,V value);
	public boolean delete(T key);
}
