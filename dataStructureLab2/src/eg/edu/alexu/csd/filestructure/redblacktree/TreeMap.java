package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.RuntimeErrorException;

public class TreeMap implements ITreeMap{
	
	private IRedBlackTree RB = new RedBlackTree();
	private HashMap<Comparable,Boolean> taken = new HashMap<Comparable,Boolean>();
	private int size = 0;
	
	
	private INode ceilingKey(Comparable key,INode node) {
		if(key == null) throw new RuntimeErrorException(null);
		if(node == null || node.isNull()) {
			return null;
		}
		if(node.getKey().compareTo(key) == 0) {
			return node;
		}
		if(node.getKey().compareTo(key) == 1) {
			INode tmp = node;
			INode check = ceilingKey(key,node.getLeftChild());
			if(check == null || check.isNull()) return tmp;
			return check;
		}else {
			return ceilingKey(key,node.getRightChild());
		}
	}

	@Override
	public Entry ceilingEntry(Comparable key) {
		// TODO Auto-generated method stub
		INode x = ceilingKey(key,RB.getRoot());
		if(x == null || x.isNull()) return null;
		return Map.entry(x.getKey(), x.getValue());
	}

	@Override
	public Comparable ceilingKey(Comparable key) {
		// TODO Auto-generated method stub
		INode x = ceilingKey(key,RB.getRoot());
		if(x == null || x.isNull()) return null;
		return x.getKey();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		RB.clear();
		taken.clear();
	}

	@Override
	public boolean containsKey(Comparable key) {
		// TODO Auto-generated method stub
		return RB.contains(key);
	}
	
	

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		
		return searchValue(RB.getRoot(),value);
	}
	
	private boolean searchValue(INode node,Object value) {
		
		if(value == null) throw new RuntimeErrorException(null);
		if(node == null || node.isNull()) return false;
		
		if(node.getValue().equals(value)) return true;
		
		if(searchValue(node.getLeftChild(),value)) return true;
		
		if(searchValue(node.getRightChild(),value)) return true;
		
		return false;
	}
	

	@Override
	public Set entrySet() {
		// TODO Auto-generated method stub
		Set<Map.Entry> set = new LinkedHashSet<Map.Entry>();
		entrySet(set, RB.getRoot());
		return set;
	}
	private void entrySet(Set<Map.Entry> set,INode node) {
		if(node == null | node.isNull()) return;
		entrySet(set,node.getLeftChild());
		
		set.add(Map.entry(node.getKey(), node.getValue()));
		entrySet(set,node.getRightChild());
	}

	@Override
	public Entry firstEntry() {
		// TODO Auto-generated method stub
		INode x = firstKey(RB.getRoot());
		if(x==null || x.isNull()) return null;
		return Map.entry(x.getKey(), x.getValue());
	}
	private INode firstKey(INode node) {
		if(node == null || node.isNull()) return null;
		if(node.getLeftChild().isNull()) return node;
		return firstKey(node.getLeftChild());
	}

	@Override
	public Comparable firstKey() {
		// TODO Auto-generated method stub
		INode x = firstKey(RB.getRoot());
		if(x== null ) return null;
		return x.getKey();
	}

	@Override
	public Entry floorEntry(Comparable key) {
		// TODO Auto-generated method stub
		INode x = floorKey(key,RB.getRoot());
		if(x==null||x.isNull()) return null;
		return Map.entry(x.getKey(), x.getValue());
	}
	
	private INode floorKey(Comparable key,INode node) {
		if(key==null) throw new RuntimeErrorException(null);
		if(node == null || node.isNull()) return null;
		
		if(node.getKey().compareTo(key) == 0) return node;
		if(node.getKey().compareTo(key) == 1) return floorKey(key,node.getLeftChild());
		else {
			INode x = node;
			INode check = floorKey(key,node.getRightChild());
			if(check==null||check.isNull()) {
				return x;
			}
			return check;
		}
	}

	@Override
	public Comparable floorKey(Comparable key) {
		// TODO Auto-generated method stub
		INode x = floorKey(key,RB.getRoot());
		if(x==null) return null;
		return x.getKey();
	}

	@Override
	public Object get(Comparable key) {
		// TODO Auto-generated method stub
		//if(key==null) return new RuntimeErrorException(null);
		return RB.search(key);
	}
	
	private void headMap(ArrayList<Map.Entry> arr,Comparable toKey,INode node,boolean inclusive) {
		if(node==null ) return;
		if(node.getKey().compareTo(toKey) == 0 && inclusive) {
			
			headMap(arr,toKey,node.getLeftChild(),inclusive);
			arr.add(Map.entry(node.getKey(), node.getValue()));
		}else if(node.getKey().compareTo(toKey) == -1) {
			
			headMap(arr,toKey,node.getLeftChild(),inclusive);
			arr.add(Map.entry(node.getKey(), node.getValue()));
			headMap(arr,toKey,node.getRightChild(),inclusive);
			
		}else {
			
			headMap(arr,toKey,node.getLeftChild(),inclusive);
			
		}
		return;
	}

	@Override
	public ArrayList headMap(Comparable toKey) {
		// TODO Auto-generated method stub
		ArrayList<Map.Entry> ans = new ArrayList<>();
		headMap(ans,toKey,RB.getRoot(),false);
		return ans;
	}

	@Override
	public ArrayList headMap(Comparable toKey, boolean inclusive) {
		// TODO Auto-generated method stub
		ArrayList<Map.Entry> ans = new ArrayList<>();
		headMap(ans,toKey,RB.getRoot(),inclusive);
		return ans;
	}

	private void keySet(Set s,INode node) {
		if(node == null) return;
		keySet(s,node.getLeftChild());
		s.add(node.getKey());
		keySet(s,node.getRightChild());
	}
	
	@Override
	public Set keySet() {
		// TODO Auto-generated method stub
		Set<Comparable> set = new LinkedHashSet<>();
		keySet(set,RB.getRoot());
		return set;
	}
	
	private INode lastKey(INode node) {
		if(node==null||node.isNull()) return null;
		
		if(node.getRightChild() == null) {
			System.out.println("here1");
			return node;
		}
		return lastKey(node.getRightChild());
	}

	@Override
	public Entry lastEntry() {
		// TODO Auto-generated method stub
		INode x = lastKey(RB.getRoot());
		System.out.println("here2");
		System.out.println(x);
		if(x==null) return null;
		return Map.entry(x.getKey(), x.getValue());
	}

	@Override
	public Comparable lastKey() {
		// TODO Auto-generated method stub
		INode x = lastKey(RB.getRoot());
		if(x==null) return null;
		return x.getKey();
	}

	@Override
	public Entry pollFirstEntry() {
		// TODO Auto-generated method stub
		INode x = firstKey(RB.getRoot());
		if(x==null) return null;
		
		Map.Entry entry = Map.entry(x.getKey(), x.getValue());
		if(!this.remove(x.getKey())) return null;
		
		return entry;
	}

	@Override
	public Entry pollLastEntry() {
		// TODO Auto-generated method stub
		INode x = lastKey(RB.getRoot());
		if(x==null) return null;
		
		Map.Entry entry = Map.entry(x.getKey(), x.getValue());
		if(!this.remove(x.getKey())) return null;
		
		return entry;
	}

	@Override
	public void put(Comparable key, Object value) {
		// TODO Auto-generated method stub
		
		RB.insert(key, value);
		if(taken.get(key)== null || taken.get(key) == false) {
			size++;
			taken.put(key,true);
		}
	}

	@Override
	public void putAll(Map map) {
		// TODO Auto-generated method stub
		if(map == null) throw new RuntimeErrorException(null);
		map.forEach((K,V)->{
			this.put((Comparable) K, V);
		});
	}

	@Override
	public boolean remove(Comparable key) {
		// TODO Auto-generated method stub
		boolean x = RB.delete(key);
		if(x==true) {
			taken.put(key,false);
			size--;
		}
		return x;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Collection values() {
		// TODO Auto-generated method stub
		ArrayList<Object> ans = new ArrayList<>();
		values(ans,RB.getRoot());
		return ans;
	}
	private void values(Collection ans,INode node) {
		if(node==null||node.isNull()) return;
		values(ans,node.getLeftChild());
		ans.add(node.getValue());
		values(ans,node.getRightChild());
	}
	
}
