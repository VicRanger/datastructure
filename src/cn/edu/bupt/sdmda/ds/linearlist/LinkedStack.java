package cn.edu.bupt.sdmda.ds.linearlist;

public class LinkedStack<T> extends MyLinkedList<T>  implements MyStack<T>{

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(!super.isEmpty()) {
			return deleteAt(0);
		}else {
			return null;
		}
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		insert(0, t);
	}

	@Override
	public T getTop(){
		if(!super.isEmpty()) {
			return get(0);
		}else {
			return null;
		}
	}
	
	private Boolean judgeI(int i) {
		return i==0;
	}
	
	@Override
	public void insert(int i, T t) {
		if(judgeI(i)) {
			super.insert(i, t);
		}else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public void delete(T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T deleteAt(int i) {
		if(judgeI(i)) {
			return super.deleteAt(i);
		}else {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public T get(int i) {
		return super.get(i);
	}

	@Override
	public void set(int i, T t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int find(T t) {
		return super.find(t);
	}

	@Override
	public LinearList<T> sort() {
		throw new UnsupportedOperationException();
	}



}
