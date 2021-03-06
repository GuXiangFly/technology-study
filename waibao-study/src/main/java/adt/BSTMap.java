package adt;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V > implements  Map<K, V>  {

	BinarySearchTree<K,V> bst;

	public BSTMap () {
		bst = new BinarySearchTree<K,V>();
	}

	public boolean containsKey(K key) {
		V v = bst.find(key);
		if (v== null){
			return false;
		}else {
			return true;
		}
	}

	public V get (K key) throws KeyNotFoundException {
		return bst.find(key);
	}

	public List<Entry<K,V>>	entryList() {
		return bst.entryList();
	}

	public void put (K key, V value) {
		bst.insert(key,value);
	}

	public int size() {
		 return bst.size();
	}

	public void clear() {
		bst.clear();
	}


	// The remaining methods are for Part III:
	public long getGetLoopCount() {
		return bst.getFindLoopCount();
	}

	public long getPutLoopCount() {
		return bst.getInsertLoopCount();
	}

	public void resetGetLoops() {
		bst.resetFindLoops();
	}
	public void resetPutLoops() {
		bst.resetInsertLoops();
	}
}
