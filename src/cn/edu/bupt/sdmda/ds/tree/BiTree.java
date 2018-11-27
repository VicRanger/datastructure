package cn.edu.bupt.sdmda.ds.tree;

import java.util.LinkedList;

public class BiTree<T> {
	BiTreeNode<T> _root;
  // a queue to store the order of traverse
	LinkedList<BiTreeNode<T>> _queue = new LinkedList<>();

  // Create a tree whose root is root and data is data[loc]
	private void createTree(BiTreeNode<T> root, T[] data, int loc){
    // if the left child of root is valid,
    // create a tree whose root is it

    // if the right child of root is valid,
    // create a tree whose root is it
		int l = loc<<1,r = (loc<<1)+1;
		
		if(l<data.length) {
			root._leftChild = new BiTreeNode<T>(data[l]);
			createTree(root._leftChild, data, l);
		}
		if(r<data.length) {
			root._rightChild = new BiTreeNode<T>(data[r]);
			createTree(root._rightChild, data, r);
		}
		
	}

	public BiTreeNode<T> getRoot(){
		return _root;
	}

	public BiTree(T[] data) {
		// TODO Auto-generated constructor stub
		// note that the loc starts from 1 not 0
		_root = new BiTreeNode<T>(data[1]);
		createTree(_root, data, 1);
	}

	public BiTree(BiTreeNode<T> root) {
		// TODO Auto-generated constructor stub
		_root = root;
	}

	public int getDepth(BiTreeNode<T> node) {
		// if node is null return 0
	    // return MAX(depth of left, depth of right)+1
		if(node==null) return 0;
		int ld=0,rd=0;
		ld = getDepth(node._leftChild)+1;
		rd = getDepth(node._rightChild)+1;
	    return Math.max(ld, rd);
	}

	public void preOrder(BiTreeNode<T> _node){
		// note offer the correct note to _queue
		if(_node==null) return;
		_queue.offer(_node);
		preOrder(_node._leftChild);
		preOrder(_node._rightChild);
	}

	public void inOrder(BiTreeNode<T> _node){
		// note offer the correct note to _queue
		if(_node==null) return;
		inOrder(_node._leftChild);
		_queue.offer(_node);
		inOrder(_node._rightChild);
	}

	public void postOrder(BiTreeNode<T> _node){
		// note offer the correct note to _queue
		if(_node==null) return;
		postOrder(_node._leftChild);
		postOrder(_node._rightChild);
		_queue.offer(_node);
	}

	public void levelOrder() {
		// note offer the correct note to _queue
		LinkedList<BiTreeNode<T>> q = new LinkedList<>();
		q.offer(_root);
		while(!q.isEmpty()) {
			BiTreeNode<T> n = q.poll();
			_queue.offer(n);
			if(n._leftChild!=null) {
				q.offer(n._leftChild);
			}
			if(n._rightChild!=null) {
				q.offer(n._rightChild);
			}
		}
	}

	public LinkedList<BiTreeNode<T>> getQueue(){
		return _queue;
	}


  // make queue empty
	public void clearQueue(){
		_queue.clear();
	}

  // search item in the tree whose root is _node in preOrder
	public BiTreeNode<T> searchPreOrder(BiTreeNode<T> _node, T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		if(_node==null) return null;
		if(_node._data == item) return _node;
		BiTreeNode<T> l,r;
		l = searchPreOrder(_node._leftChild,item);
		if(l!=null) return l;
		r = searchPreOrder(_node._rightChild,item);
		if(r!=null) return r;
		return null;
	}

  // search item in the tree whose root is _node in inOrder
	public BiTreeNode<T> searchInOrder(BiTreeNode<T> _node, T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		if(_node==null) return null;
		BiTreeNode<T> l,r;
		l = searchInOrder(_node._leftChild,item);
		if(l!=null) return l;
		if(_node._data == item) return _node;
		r = searchInOrder(_node._rightChild,item);
		if(r!=null) return r;
		return null;
	}

  // search item in the tree whose root is _node in postOrder
	public BiTreeNode<T> searchPostOrder(BiTreeNode<T> _node, T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		if(_node==null) return null;
		BiTreeNode<T> l,r;
		l = searchPostOrder(_node._leftChild,item);
		if(l!=null) return l;
		r = searchPostOrder(_node._rightChild,item);
		if(r!=null) return r;
		if(_node._data == item) return _node;
		return null;
	}

  // search item in this tree in levelOrder
	public BiTreeNode<T> SearchLevelOrder(T item) {
    // if current node is item, return
    // search the next element in the treeld, item);
		// note offer the correct note to _queue
		LinkedList<BiTreeNode<T>> q = new LinkedList<>();
		q.offer(_root);
		while(!q.isEmpty()) {
			BiTreeNode<T> n = q.poll();

			if(n._leftChild!=null) {
				q.offer(n._leftChild);
			}
			if(n._rightChild!=null) {
				q.offer(n._rightChild);
			}
		}
		return null;
	}

}