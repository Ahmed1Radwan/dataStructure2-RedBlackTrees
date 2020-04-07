package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.NoSuchElementException;


import javax.management.RuntimeErrorException;

import org.junit.Assert;

public class RedBlackBST implements IRedBlackTree{
	
	private static final boolean RED   = true;
	private static final boolean BLACK = false;
	private Node root;
	
	public RedBlackBST() {}
	
	
	private boolean isRed(Node x ) {
		if(x==null) return false;
		return x.getColor()==RED;
	}
	private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    } 

	@Override
	public INode getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root==null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		root = null;
	}

	@Override
	public Object search(Comparable key) {
		// TODO Auto-generated method stub
		if (key == null) throw new RuntimeErrorException(null);
        return get(root, key);
	}

	private Object get(Node x, Comparable key) {
		// TODO Auto-generated method stub
		 while (x != null) {
	            int cmp = key.compareTo(x.getKey());
	            if      (cmp < 0) x = (Node)x.getLeftChild();
	            else if (cmp > 0) x = (Node)x.getRightChild();
	            else              return x.getValue();
	        }
	        return null;
	}


	@Override
	public boolean contains(Comparable key) {
		// TODO Auto-generated method stub
		if(key==null) throw new RuntimeErrorException(null);
		return search(key) != null;
	}

	@Override
	public void insert(Comparable key, Object val) {
		// TODO Auto-generated method stub
		 if (key == null || val == null) throw new RuntimeErrorException(null);
	        if (val == null) {
	            delete(key);
	            return;
	        }

	        root = put(root, key, val);
	        root.setColor(BLACK);
	}

	private Node put(Node h,Comparable key, Object val) {
		// TODO Auto-generated method stub
		if (h == null) return new Node(h,key, val, RED, 1);

		int cmp = key.compareTo(h.getKey());
		if(cmp < 0) h.setLeftChild(put((Node)h.getLeftChild(),key,val));
		else if(cmp > 0) h.setRightChild(put((Node)h.getRightChild(),key,val));
		else h.setValue(val);
		
		if(isRed((Node) h.getRightChild()) && !isRed((Node)h.getLeftChild())) h = rotateLeft(h);
		if(isRed((Node)h.getLeftChild()) && isRed((Node)h.getLeftChild().getLeftChild())) h = rotateRight(h);
		if(isRed((Node)h.getLeftChild()) && isRed((Node)h.getRightChild())) flipColors(h);
		
		return h;
	}


	@Override
	public boolean delete(Comparable key) {
		// TODO Auto-generated method stub
		if (key == null) throw new RuntimeErrorException(null);
        if (!contains(key)) return false;

        // if both children of root are black, set root to red
        if (!isRed((Node)root.getLeftChild()) && !isRed((Node)root.getRightChild()))
            root.setColor(RED);

        root = delete(root, key);
        if (!isEmpty()) root.setColor(BLACK);
        return true;
	}
	private Node delete(Node h, Comparable key) { 
        // assert get(h, key) != null;

        if (key.compareTo(h.getKey()) < 0)  {
            if (!isRed((Node)h.getLeftChild()) && !isRed((Node)h.getLeftChild().getLeftChild()))
                h = moveRedLeft(h);
            h.setLeftChild(delete((Node)h.getLeftChild(), key));
        }
        else {
            if (isRed((Node)h.getLeftChild()))
                h = rotateRight(h);
            if (key.compareTo(h.getKey()) == 0 && (h.getRightChild() == null))
                return null;
            if (!isRed((Node)h.getRightChild()) && !isRed((Node)h.getRightChild().getLeftChild()))
                h = moveRedRight(h);
            if (key.compareTo(h.getKey()) == 0) {
                Node x = min((Node)h.getRightChild());
                h.setKey(x.getKey());
                h.setValue(x.getValue());
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.setRightChild(deleteMin((Node)h.getRightChild()));
            }
            else h.setRightChild(delete((Node)h.getRightChild(), key));
        }
        return balance(h);
    }
	
	
	 public void deleteMin() {
	        if (isEmpty()) throw new RuntimeErrorException(null);

	        // if both children of root are black, set root to red
	        if (!isRed((Node)root.getLeftChild()) && !isRed((Node)root.getRightChild()))
	            root.setColor(RED);

	        root = deleteMin(root);
	        if (!isEmpty()) root.setColor(BLACK);
	        // assert check();
	    }

	    
	    private Node deleteMin(Node h) { 
	        if (h.getLeftChild() == null)
	            return null;

	        if (!isRed((Node)h.getLeftChild()) && !isRed((Node)h.getLeftChild().getLeftChild()))
	            h = moveRedLeft(h);

	        h.setLeftChild(deleteMin((Node)h.getLeftChild()));
	        return balance(h);
	    }

	    public void deleteMax() {
	        if (isEmpty()) throw new RuntimeErrorException(null);

	        // if both children of root are black, set root to red
	        if (!isRed((Node)root.getLeftChild()) && !isRed((Node)root.getRightChild()))
	            root.setColor(RED);

	        root = deleteMax(root);
	        if (!isEmpty()) root.setColor(BLACK);
	        // assert check();
	    }
	    private Node deleteMax(Node h) { 
	        if (isRed((Node)h.getLeftChild()))
	            h = rotateRight(h);

	        if (h.getRightChild() == null)
	            return null;

	        if (!isRed((Node)h.getRightChild()) && !isRed((Node)h.getRightChild().getLeftChild()))
	            h = moveRedRight(h);

	        h.setRightChild(deleteMax((Node)h.getRightChild()));

	        return balance(h);
	    }
	
	    
	    
	 // make a left-leaning link lean to the right
	    private Node rotateRight(Node h) {
	        // assert (h != null) && isRed(h.left);
	        Node x = (Node) h.getLeftChild();
	        h.setLeftChild(x.getRightChild());
	        x.setRightChild(h);
	        x.setColor(x.getRightChild().getColor());
	        x.getRightChild().setColor(RED);
	        x.size = h.size;
	        h.size = size((Node)h.getLeftChild()) + size((Node)h.getRightChild()) + 1;
	        return x;
	    }

	    // make a right-leaning link lean to the left
	    private Node rotateLeft(Node h) {
	        // assert (h != null) && isRed(h.right);
	        Node x = (Node) h.getRightChild();
	        h.setRightChild(x.getLeftChild());
	        x.setLeftChild(h);
	        x.setColor(x.getLeftChild().getColor());
	        x.getLeftChild().setColor(RED);
	        x.size = h.size;
	        h.size = size((Node)h.getLeftChild()) + size((Node)h.getRightChild()) + 1;
	        return x;
	    }

	    // flip the colors of a node and its two children
	    private void flipColors(Node h) {
	        // h must have opposite color of its two children
	        // assert (h != null) && (h.left != null) && (h.right != null);
	        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
	        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
	        h.setColor(!h.getColor());
	        h.getLeftChild().setColor(!h.getLeftChild().getColor());
	        h.getRightChild().setColor(!h.getRightChild().getColor());
	    }

	    // Assuming that h is red and both h.left and h.left.left
	    // are black, make h.left or one of its children red.
	    private Node moveRedLeft(Node h) {
	        // assert (h != null);
	        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

	        flipColors(h);
	        if (isRed((Node)h.getRightChild().getLeftChild())) { 
	            h.setRightChild(rotateRight((Node)h.getRightChild()));
	            h = rotateLeft(h);
	            flipColors(h);
	        }
	        return h;
	    }

	    // Assuming that h is red and both h.right and h.right.left
	    // are black, make h.right or one of its children red.
	    private Node moveRedRight(Node h) {
	        // assert (h != null);
	        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
	        flipColors(h);
	        if (isRed((Node)h.getLeftChild().getLeftChild())) { 
	            h = rotateRight(h);
	            flipColors(h);
	        }
	        return h;
	    }

	    // restore red-black tree invariant
	    private Node balance(Node h) {
	        // assert (h != null);

	        if (isRed((Node)h.getRightChild()))                      h = rotateLeft(h);
	        if (isRed((Node)h.getLeftChild()) && isRed((Node)h.getLeftChild().getLeftChild())) h = rotateRight(h);
	        if (isRed((Node)h.getLeftChild()) && isRed((Node)h.getRightChild()))     flipColors(h);

	        h.size = size((Node)h.getLeftChild()) + size((Node)h.getRightChild()) + 1;
	        return h;
	    }
	
	    public Comparable min() {
	        if (isEmpty()) throw new RuntimeErrorException(null);
	        return min(root).getKey();
	    } 

	    // the smallest key in subtree rooted at x; null if no such key
	    private Node min(Node x) { 
	        // assert x != null;
	        if (x.getLeftChild() == null) return x; 
	        else                return min((Node)x.getLeftChild()); 
	    } 

	    public Comparable max() {
	        if (isEmpty()) throw new RuntimeErrorException(null);
	        return max(root).getKey();
	    } 

	    // the largest key in the subtree rooted at x; null if no such key
	    private Node max(Node x) { 
	        // assert x != null;
	        if (x.getRightChild() == null) return x; 
	        else                 return max((Node)x.getRightChild()); 
	    } 

	
	
}
