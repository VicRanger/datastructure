package cn.edu.bupt.sdmda.ds.linearlist;

import java.util.Arrays;

public class MyLinkedList<T> implements LinearList<T> {

    class Node {
        public T _ele;
        public Node _next;

        public Node() {
            init(null, null);
        }

        public Node(T e) {
            init(e, null);
        }

        public Node(T e, Node n) {
            init(e, n);
        }

        private void init(T e, Node n) {
            _ele = e;
            _next = n;
        }
    }

    Node _head;
    int _size;

    public MyLinkedList(int s, T init) {
    	init(s,init);
    }

    public MyLinkedList() {
    	init(0,null);
    }

    @Override
    public void init(int s, T init) {
    	_size = Math.max(0,s);
    	_head = new Node(null,null);
    	Node cur = _head;
		for(int i=0;i<s;i++) {
			cur._next = new Node(init,null);
			cur = cur._next;
		}
    }
    @Override
    public void reverse(int start,int end) {
    	Node prevNode = findAt(start-1);
    	Node curNode = findAt(start);
    	Node endNode = findAt(end); 
    	Node endNextNode = endNode._next;
    	Node nextNode = curNode;
    	Node lastNode = endNode._next;
    	while(nextNode!=endNextNode) {
    		curNode = nextNode;
    		nextNode = curNode._next;
    		curNode._next = lastNode;
    		lastNode = curNode;
    	}
    	prevNode._next = endNode;
    }
    @Override
    public void reverse() {
    	reverse(0,_size-1);
    }
    
    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public int getSize() {
        return _size;
    }

    @Override
    public void clear() {
    	Node next=_head._next,cur=_head;
    	while(next != null) {
    		cur = next;
    		next = cur._next;
    		cur._next = null;
    	}
        _head._next = null;
        _head = null;
        _size = 0;
    }

    @Override
    public void insert(int i, T t) {
        // i=_size is OK!
        if (!checkWritableRange(i)) {
            return;
        }
        Node cur = _head;
        Node node = new Node(t, null);
        cur = findAt(i-1);
        node._next = cur._next;
        cur._next = node;
        _size++;
    }

    @Override
    public void delete(T t) {
        Node cur = _head;
        while (cur._next != null) {
            if (t.equals(cur._next._ele)) {
                cur._next = cur._next._next;
                _size--;
                return;
            }
            cur = cur._next;
        }
    }

    @Override
    public T deleteAt(int i) {
    	if(!checkReadableRange(i)) {
    		return null;
    	}
    	Node node = findAt(i-1);
    	Node ret = node._next;
    	node._next = node._next._next;
    	_size --;
    	return ret._ele;
    }

    private Node findAt(int i) {
    	if(i<-1 || i>=_size) {
    		return null;
    	}
    	Node ret = _head;
    	int ind = -1;
    	while(ind < i) {
    		ret = ret._next;
    		ind++;
    	}
    	return ret;
    }
    
    @Override
    public T get(int i) {
    	if(!checkReadableRange(i)) {
    		return null;
    	}
    	Node node = findAt(i);
        return node._ele;
    }

    @Override
    public void set(int i, T t) {
    	if(!checkReadableRange(i)) {
    		return;
    	}
    	Node node = findAt(i);
        node._ele = t;
    }

    @Override
    public int find(T t) {
    	Node cur = _head._next;
    	int ind = 0;
    	while(cur != null) {
    		if(t.equals(cur._ele)) {
    			return ind;
    		}
    		cur = cur._next;
    		ind ++;
    	}
        return 0;
    }

    @Override
    public LinearList<T> sort() {
    	Object a[] = new Object[_size];
    	Node cur = _head._next;
    	for(int i=0;i<_size;i++) {
    		a[i] = cur._ele;
    		cur = cur._next;
    	}
    	Arrays.sort(a);
    	MyLinkedList<T> ret = new MyLinkedList<T>(_size,null);
    	for(int i=0;i<_size;i++) {
    		ret.set(i, (T) a[i]);
    	}
        return (LinearList<T>) ret;
    }

    private Node getNodeBefore(int i) {
        // assume i is a valid value
        return null;
    }

    private boolean checkReadableRange(int i) {
        if (i < 0 || i >= _size) {
            return false;
        }
        return true;
    }

    private boolean checkWritableRange(int i) {
        if (i < 0 || i > _size) {
            return false;
        }
        return true;
    }
}
