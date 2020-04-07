package eg.edu.alexu.csd.filestructure.redblacktree;

public interface INode<T extends Comparable<T>,V> {
	
	static final boolean RED = true;
	static final boolean BLACK = false;
	
	void setParent(INode<T,V> parent);
	INode<T,V> getParent();
	
	void setLeftChild(INode<T,V> leftChild);
	INode<T,V> getLeftChild();
	
	void setRightChild(INode<T,V> rightChild);
	INode<T,V> getRightChild();
	
	void setKey(T key);
	T getKey(); // the current node
	
	void setValue(V value);
	V getValue();
	
	boolean getColor();
	void setColor(boolean color);
	
	boolean isNull();

}
