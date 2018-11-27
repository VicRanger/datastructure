package cn.edu.bupt.sdmda.ds.linearlist;



public class SeqStack<T> extends SeqList<T> implements MyStack<T>{

	public SeqStack(){
		super();
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(!super.isEmpty()) {
			return deleteAt(getSize()-1);
		}else {
			return null;
		}
	}

	@Override
	public void push(T t) {
		// TODO Auto-generated method stub
		insert(getSize(), t);
	}

	@Override
	public T getTop(){
		if(!super.isEmpty()) {
			return get(getSize()-1);
		}else {
			return null;
		}
	}
	
	private Boolean judgeIw(int i) {
		return i==getSize();
	}
	private Boolean judgeIr(int i) {
		return i==getSize()-1;
	}


	@Override
	public void insert(int i, T t) {
		if(judgeIw(i)) {
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
		if(judgeIr(i)) {
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
