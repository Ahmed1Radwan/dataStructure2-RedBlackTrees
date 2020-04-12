package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.Optional;

import javax.management.RuntimeErrorException;

public class RedBlackTree implements IRedBlackTree{
	
	private static final boolean RED   = true;
	private static final boolean BLACK = false;
	private Node root = null;
	private int nodeCount = 0;
	

	@Override
	public INode getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return nodeCount == 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = null;
		nodeCount=0;
	}

	@Override
	public Object search(Comparable key) {
		// TODO Auto-generated method stub
		if(key==null) throw new RuntimeErrorException(null);
		Node node = root;
		if(node ==null) return null;
		while(node!=null) {
			int cmp = key.compareTo(node.getKey());
			if(cmp<0) node = (Node)node.getLeftChild();
			else if(cmp>0) node = (Node)node.getRightChild();
			else return node.getValue();
		}
		return null;
	}
	
	@Override
	public boolean contains(Comparable key) {
		// TODO Auto-generated method stub
		if(key == null) throw new RuntimeErrorException(null);
		return search(key)!=null;
	}

	@Override
	public void insert(Comparable key, Object value) {
		// TODO Auto-generated method stub
		if(key == null || value == null) throw new RuntimeErrorException(null);
		if(root == null) {
			root = new Node(key,value,null);
			insertionRelabel(root);
			nodeCount++;
			return;
		}
		for(Node node = root; ;) {
			int cmp = key.compareTo(node.getKey());
			if(cmp<0) {
				if(node.getLeftChild() == null) {
					node.setLeftChild(new Node(key,value,node));
					insertionRelabel(node.getLeftChild());
					nodeCount++;
					return;
				}
				node = (Node)node.getLeftChild();
			}else if(cmp > 0) {
				if(node.getRightChild()==null) {
					node.setRightChild(new Node(key,value,node));
					insertionRelabel(node.getRightChild());
					nodeCount++;
					return;
				}
				node = (Node)node.getRightChild();
			}else {
				return;
			}
		}
	}

	
	

	private void insertionRelabel(INode node) {
		// TODO Auto-generated method stub
		Node parent = (Node) node.getParent();
		if(parent == null) {
			node.setColor(BLACK);
			root = (Node)node;
			return;
		}
		Node grandParent = (Node) parent.getParent();
		if(grandParent == null) return;
		if(parent.getColor() == BLACK || node.getColor() == BLACK) return;
		
		boolean nodeIsLeftChild = (parent.getLeftChild() == node);
		boolean parentIsLeftChild = (parent==grandParent.getLeftChild());
		Node uncle = parentIsLeftChild ? (Node)grandParent.getRightChild() : (Node)grandParent.getLeftChild();
		boolean uncleIsRedNode = (uncle == null) ? BLACK : uncle.getColor();
		
		if(uncleIsRedNode) {
			parent.setColor(BLACK);
			grandParent.setColor(RED);
			uncle.setColor(BLACK);
		}else {
			if(parentIsLeftChild) {
				if(nodeIsLeftChild) {
					grandParent = leftLeftCase(grandParent);
				}else {
					grandParent= leftRightCase(grandParent);
				}
			}else {
				if(nodeIsLeftChild) {
					grandParent=rightLeftCase(grandParent);
				}else {
					grandParent=rightRightCase(grandParent);
				}
			}
		}
		insertionRelabel(grandParent);
	}

	private void swapColors(Node a,Node b) {
		boolean tmpColor = a.getColor();
		
		a.setColor(b.getColor());
		b.setColor(tmpColor);
	}
	private Node rightRightCase(Node node) {
		// TODO Auto-generated method stub
		node = (Node) leftRotate(node);
		swapColors(node,(Node)node.getLeftChild());
		return node;
	}

	private Node rightLeftCase(Node node) {
		// TODO Auto-generated method stub
		node.setRightChild(rightRotate((Node)node.getRightChild()));
		return rightRightCase(node);
	}

	private Node leftRightCase(Node node) {
		// TODO Auto-generated method stub
		node.setLeftChild(leftRotate(node.getLeftChild()));
		return leftLeftCase(node);
	}


	private Node leftLeftCase(Node node) {
		// TODO Auto-generated method stub
		node = rightRotate(node);
		swapColors(node,(Node) node.getRightChild());
		return node;
	}
	
	private INode leftRotate(INode parent) {
		// TODO Auto-generated method stub
		Node grandParent = (Node)parent.getParent();
		Node child = (Node)parent.getRightChild();
		parent.setRightChild(child.getLeftChild());
		if(child.getLeftChild() != null) child.getLeftChild().setParent(parent);
		child.setLeftChild(parent);
		parent.setParent(child);
		child.setParent(grandParent);
		updateParentChildLink(grandParent,parent,child);
		return child;
	}
	
	private Node rightRotate(Node parent) {
		// TODO Auto-generated method stub
		Node grandParent = (Node)parent.getParent();
		Node child = (Node)parent.getLeftChild();
		parent.setLeftChild(child.getRightChild());
		if(child.getRightChild() != null) child.getRightChild().setParent(parent);
		
		child.setRightChild(parent);
		parent.setParent(child);
		child.setParent(grandParent);
		
		updateParentChildLink(grandParent,parent,child);
		return child;
	}
	
	private void updateParentChildLink(Node parent, INode oldChild, Node newChild) {
		// TODO Auto-generated method stub
		if(parent!=null) {
			if(parent.getLeftChild()==oldChild) {
				parent.setLeftChild(newChild);
			}else {
				parent.setRightChild(newChild);
			}
		}
	}
	
	@Override
	public boolean delete(Comparable key) {
		// TODO Auto-generated method stub
		if(key == null) throw new RuntimeErrorException(null);
		Node node = find(key);
		if(node != null) {
			if(!isLeaf((Node)node.getLeftChild()) && !isLeaf((Node)node.getRightChild())) {
				node = copyMaxpredecessor(node);
			}
			Node child = isLeaf((Node)node.getRightChild()) ? (Node)node.getLeftChild() : (Node)node.getRightChild();
			if(node.getColor()==BLACK) {
				if(!isBlack(child)) {
					node.setColor(RED);
				}
				deleteCase1(node);
			}
			replace(node,child);
		}
		return node!=null;
	}

	private Node copyMaxpredecessor(Node node) {
		// TODO Auto-generated method stub
		Node predecessor = maxPredecessor(node);
		node.setKey(predecessor.getKey());
		node.setValue(predecessor.getValue());
		return predecessor;
	}

	private Node maxPredecessor(Node node) {
		// TODO Auto-generated method stub
		node = (Node)node.getLeftChild();
		while(node.getRightChild()!=null) {
			node =  (Node)node.getRightChild();
		}
		return node;
	}

	private void replace(Node node, Node replacement) {
		// TODO Auto-generated method stub
		if(node==root) {
			root = replacement;
			if(replacement != null) root.setColor(BLACK);
		}else {
			if(node==node.getParent().getLeftChild()) {
				node.getParent().setLeftChild(replacement);
			}else {
				node.getParent().setRightChild(replacement);
			}
		}
		if(replacement != null) {
			replacement.setParent(node.getParent());
		}
	}

	private void deleteCase1(Node node) {
		// TODO Auto-generated method stub
		if(node.getParent()!=null) {
			deleteCase2(node);
		}
	}

	private void deleteCase2(Node node) {
		// TODO Auto-generated method stub
		Node sibling = sibling(node);
		if(sibling.getColor() == RED) {
			node.getParent().setColor(RED);
			sibling.setColor(BLACK);
			if(node==node.getParent().getLeftChild()) {
				rotateLeft(node.getParent());
			}else {
				rotateRight(node.getParent());
			}
		}
		deleteCase3(node);
	}

	private void deleteCase3(Node node) {
		// TODO Auto-generated method stub
		Node sibling = sibling(node);
		if(node.getParent().getColor() == BLACK 
				&& sibling!=null
				&&sibling.getColor()==BLACK
				&&sibling.getLeftChild().getColor()==BLACK
				&&sibling.getRightChild().getColor()==BLACK) {
			sibling.setColor(RED);
			deleteCase1((Node)node.getParent());
		}else {
			deleteCase4(node);
		}
	}

	private void deleteCase4(Node node) {
		// TODO Auto-generated method stub
		Node sibling = sibling(node);
		if(node.getParent().getColor() == RED 
				&& sibling!=null
				&&sibling.getColor()==BLACK
				&&sibling.getLeftChild().getColor()==BLACK
				&&sibling.getRightChild().getColor()==BLACK) {
			sibling.setColor(RED);
			node.getParent().setColor(BLACK);
		}else {
			deleteCase5(node);
		}
	}

	private void deleteCase5(Node node) {
		// TODO Auto-generated method stub
		Node sibling = sibling(node);
		if(node==node.getParent().getLeftChild()
				&& sibling!=null
				&&sibling.getColor()==BLACK
				&&sibling.getLeftChild().getColor()==RED
				&&sibling.getRightChild().getColor()==BLACK) {
			sibling.setColor(RED);
			if(sibling.getLeftChild()!=null) sibling.getLeftChild().setColor(BLACK);
			rotateRight(sibling);
		}else if(node==node.getParent().getRightChild()
				&&sibling!=null
				&&sibling.getColor()==BLACK
				&&sibling.getLeftChild().getColor()==BLACK
				&&sibling.getRightChild().getColor()==RED){
				
			sibling.setColor(RED);
			if(sibling.getRightChild()!=null) sibling.getRightChild().setColor(BLACK);
			rotateLeft(sibling);
		}
		deleteCase6(node);
	}

	private void deleteCase6(Node node) {
		// TODO Auto-generated method stub
		Node sibling = sibling(node);
		setColorOfOther(sibling,node.getParent());
		node.getParent().setColor(BLACK);
		if(node==node.getParent().getLeftChild()) {
			sibling.getRightChild().setColor(BLACK);
			rotateLeft(node.getParent());
		}else {
			sibling.getLeftChild().setColor(BLACK);
			rotateRight(node.getParent());
		}
	}

	private void rotateRight(INode node) {
		// TODO Auto-generated method stub
		if(node!=null) {
			Node left = (Node)node.getLeftChild();
			replace((Node)node,left);
			node.setLeftChild(left==null?null:left.getRightChild());
			if(left!=null) {
				if(left.getRightChild()!=null) {
					left.getRightChild().setParent(node);
				}
				left.setRightChild(node);
			}
			node.setParent(left);
		}
	}

	private void rotateLeft(INode node) {
		// TODO Auto-generated method stub
		if(node != null) {
			Node right = (Node)node.getRightChild();
			replace((Node)node,right);
			node.setRightChild(right==null?null:right.getLeftChild());
			if(right!=null) {
				if(right.getLeftChild()!=null) {
					right.getLeftChild().setParent(node);
				}
				right.setLeftChild(node);
			}
			node.setParent(right);
		}
	}

	private void setColorOfOther(Node node, INode other) {
		// TODO Auto-generated method stub
		if(node !=null && other !=null) {
			node.setColor(other.getColor());
		}
	}

	private Node find(Comparable key) {
		Node node = root;
		while(node != null) {
			if(node.getKey().compareTo(key) == 0) {
				return node;
			}else if(key.compareTo(node.getKey()) < 0) {
				node = (Node)node.getLeftChild();
			}else {
				node = (Node)node.getRightChild();
			}
		}
		return null;
	}
	private boolean isLeaf(Node node) {
		return node == null;
	}
	private boolean isBlack(Node node) {
		return node ==null || node.getColor()==BLACK;
	}
	private boolean isRed(Node node) {
		return node != null && node.getColor()==RED;
	}
	
	private Node sibling(Node node) {
		if(node == node.getParent().getLeftChild()) {
			return (Node)node.getParent().getRightChild();
		}else {
			return (Node)node.getParent().getLeftChild();
		}
	}
	
	
}
