package cn.edu.bupt.sdmda.ds.linearlist;

public class SeqQueue<T> extends SeqList<T> implements MyQueue<T>{
	
	int head,tail,qsize;
	
	public SeqQueue() {
		super();
		head = 0;
		tail = -1;
		qsize = 0;
	}

	@Override
	public void offer(T t) {
		if(++qsize>getDataSize()) {
			super.insert(++tail,t);
			if(qsize!=1 && tail<=head) {
				head++;
				System.arraycopy(_data, head, 
						_data, getDataSize()-(qsize-tail-1), qsize-tail-1);
				head = getDataSize()-(qsize-tail-1);
			} 
		}else {
			super._size = Math.min(super._size+1,getDataSize());
			tail = nextPos(tail);
			set(tail,t);
		}
	}
	
	@Override
	public int getSize() {
		return qsize;
	}
	
	private int getDataSize(){
		if(super._data == null) {
			return 0;
		}
		return super._data.length;
	}
	
	@Override
	public void set(int i, T t) {
		if(i<0 || i>=getDataSize()) {
			return;
		}
        super._data[i] = t;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(int i) {
		if(i<0 || i>=getDataSize()) {
			return null;
		}
        return (T) super._data[i];
    }
	
	@Override
	public T poll() {
		if(qsize==0) {
			return null;
		}
		head = nextPos(head);
		--qsize;
		return get(prevPos(head));
	}
	
	private int prevPos(int x) {
		return (x-1+getDataSize())%getDataSize();
	}
	
	private int nextPos(int x) {
		return (x+1)%getDataSize();
	}

	@Override
	public T getHead() {
		if(isEmpty()) {
			return null;
		}
		return super.get(head);
	}
	
	@Override
	public boolean isEmpty() {
		return qsize == 0;
	}
	
	@Override
    public void clear() {
		while(!isEmpty()) {
			poll();
		}
    }
	
	@Override
    public void delete(T t) {
		throw new UnsupportedOperationException();
    }
    @Override
    public T deleteAt(int i) {
    	throw new UnsupportedOperationException();
    }
    @Override
	public int find(T t) {
    	throw new UnsupportedOperationException();
	}
}
