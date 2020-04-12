package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node<T extends Comparable<T>, V> implements INode{
	
	private T key;
	private V value;
	private Node left,right;
	private boolean color = RED;
	public int size;
	private Node parent;
	
	public Node(T key,V value,Node parent) {
		this.key=key;
		this.value=value;
		this.parent=parent;
	}
	
	
	@Override
	public void setParent(INode parent) {
		// TODO Auto-generated method stub
		this.parent = (Node) parent;
	}

	@Override
	public INode getParent() {
		// TODO Auto-generated method stub
		return parent;
	}

	@Override
	public void setLeftChild(INode leftChild) {
		// TODO Auto-generated method stub
		this.left = (Node) leftChild;
	}

	@Override
	public INode getLeftChild() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public void setRightChild(INode rightChild) {
		// TODO Auto-generated method stub
		this.right = (Node) rightChild;
	}

	@Override
	public INode getRightChild() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public void setKey(Comparable key) {
		// TODO Auto-generated method stub
		this.key = (T)key;
	}

	@Override
	public Comparable getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		this.value = (V) value;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public boolean getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setColor(boolean color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public boolean isNull() {
		// TODO Auto-generated method stub
		return this == null;
	}
	

}
