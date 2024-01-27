package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 *  
 */
 public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */
	K key;
	V value;
	Tree<K,V> left, right;
	/**
	 * Only constructor we need.
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K,V> left, Tree<K,V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		//checks if the key is in the tree and returns the value if it is
		if(this.key.compareTo(key)==0){
			return value;
		}
		//checks if the key is in the left subtree and searches it
		else if(this.key.compareTo(key)>0){
			return left.search(key);
		}
		//checks if the key is in the right subtree and searches it
		else{
			return right.search(key);
		}
	}
	
	public NonEmptyTree<K, V> insert(K key, V value) {
		//checks if the key is in the tree and replaces the value if it is
		if(this.key.compareTo(key)==0){
			this.value = value;
			return this;
		}
		//checks if the key is in the left subtree and inserts it
		else if(this.key.compareTo(key)>0){
			left = left.insert(key, value);
			return this;
		}
		//checks if the key is in the right subtree and inserts it
		else{
			right = right.insert(key, value);
			return this;
		}
	}
	
	public Tree<K, V> delete(K key) {
		//checks if the key is in the tree and deletes it if it is
		if(this.key.compareTo(key)==0){
			try{
				//checks if the left subtree is empty and returns the right subtree if it is
				this.key=left.max();
				this.value=left.search(left.max());
				left=left.delete(left.max());
				
			} catch(TreeIsEmptyException e){
				try{
					//checks if the right subtree is empty and returns the left subtree if it is
					this.key=right.min();
					this.value=right.search(right.min());
					right=right.delete(right.min());
				}catch(TreeIsEmptyException f){
					return EmptyTree.getInstance();
				}
			}
		}
		if(this.key.compareTo(key)>0){
			left=left.delete(key);
			return this;
		}
		else if(this.key.compareTo(key)<0){
			right=right.delete(key);
			return this;
		}else{
			return this;
		}
		
	}

	public K max() {
		try{
			return right.max();
		}
		catch(TreeIsEmptyException e){
			return key;
		}
	}

	public K min() {
		try{
			return left.min();
		}
		catch(TreeIsEmptyException e){
			return key;
		}
	}

	public int size() {
		return 1 + left.size() + right.size();
	}

	public void addKeysToCollection(Collection<K> c) {
		c.add(key);
		left.addKeysToCollection(c);
		right.addKeysToCollection(c);
	}
	
	public Tree<K,V> subTree(K fromKey, K toKey) {
		//checks if the key is in the tree
		if(this.key.compareTo(fromKey)>=0 && this.key.compareTo(toKey)<=0){
			return new NonEmptyTree<K,V>(key, value, left.subTree(fromKey, toKey), right.subTree(fromKey, toKey));
		}
		//checks if the key is in the left subtree
		else if(this.key.compareTo(fromKey)<0){
			return right.subTree(fromKey, toKey);
		}
		//checks if the key is in the right subtree
		else{
			return left.subTree(fromKey, toKey);
		}
	}
	
	public int height() {
		return 1 + Math.max(left.height(), right.height());
	}
	
	public void inorderTraversal(TraversalTask<K,V> p) {
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}
	
	public void rightRootLeftTraversal(TraversalTask<K,V> p) {
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}	
}