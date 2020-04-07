package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public interface ITreeMap<T extends Comparable<T>, V> {
	
    public Map.Entry<T, V> ceilingEntry(T key);

    public T ceilingKey(T key);

    public void clear();

    public boolean containsKey(T key);

    public boolean containsValue(V value);

    public Set<Map.Entry<T, V>> entrySet();

    public Map.Entry<T, V> firstEntry();

    public T firstKey();

    public Map.Entry<T, V> floorEntry(T key);

    public T floorKey(T key);

    public V get(T key);

    public ArrayList<Entry<T, V>> headMap(T toKey);

    public ArrayList<Entry<T, V>> headMap(T toKey, boolean inclusive);

    public Set<T> keySet();

    public Map.Entry<T, V> lastEntry();

    public T lastKey();

    public Entry<T, V> pollFirstEntry();

    public Entry<T, V> pollLastEntry();

    public void put(T key, V value);

    public void putAll(Map<T, V> map);

    public boolean remove(T key);

    public int size();

    public Collection<V> values();

	
}
