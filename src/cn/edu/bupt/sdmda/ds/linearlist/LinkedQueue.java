package cn.edu.bupt.sdmda.ds.linearlist;

public class LinkedQueue<T> extends MyLinkedList<T> implements MyQueue<T>{
	
	Node tail;
	
	public LinkedQueue() {
		super();
	}
	
	@Override
	public void offer(T t) {
		if(isEmpty()) {
			super.insert(0, t);
			tail = super._head._next;
		}else {
			tail = insertAt(tail, new Node(t,null));
		}
	}

	@Override
	public T poll() {
		if(isEmpty()) {
			return null;
		}
		return super.deleteAt(0);
	}

	@Override
	public T getHead() {
		return super.get(getSize()-1);
	}
	
	private Node insertAt(Node cur,Node node) {
		node._next = cur._next;
		cur._next = node;
		super._size ++;
		return node;
	}

}
