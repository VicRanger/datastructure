package cn.edu.bupt.sdmda.ds.linearlist;

import java.util.Arrays;

public class SeqList<T> implements LinearList<T> {

    protected Object[] _data;
    protected int _size;

    public SeqList(int s, T init) {
        init(s, init);
    }

    public SeqList() {
        init(0, null);
    }

    @Override
    public void init(int s, T init) {
        // TODO Auto-generated method stub
        _size = s;
        _data = new Object[s];
        for (int i = 0, len = _data.length; i < len; i++) {
            _data[i] = init;
        }
    }
    @Override
    public void reverse(int start,int end) {
    	for(int i=0;i<(end-start+1)/2;i++) {
    		@SuppressWarnings("unchecked")
			T t = (T) _data[start+i];
    		_data[start+i] = _data[end-i];
    		_data[end-i] = t;
    	}
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
        // TODO Auto-generated method stub
        return _size;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        _size = 0;
    }

    @Override
    public void insert(int i, T t) {
        if (!checkWritableRange(i)) {
            return;
        }
        _size++;
        if (_size > _data.length) {
            Object[] _tdata = new Object[_size*2];
            System.arraycopy(_data, 0, _tdata, 0, i);
            System.arraycopy(_data, i, _tdata, i + 1, _size - 1 - i);
            _data = _tdata;
        } else {
            System.arraycopy(_data, i, _data, i + 1, _size - 1 - i);
        }
        _data[i] = t;
    }

    @Override
    public void delete(T t) {
        // TODO Auto-generated method stub
        for (int i = 0, len = _data.length; i < len; i++) {
            if (_data[i].equals(t)) {
                deleteAt(i);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deleteAt(int i) {
        // TODO Auto-generated method stub
        if (!checkReadableRange(i)) {
            return null;
        }
        Object ret = _data[i];
        System.arraycopy(_data, i + 1, _data, i, _size - 1 - i);
        _size--;
        return (T) ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int i) {
        // TODO Auto-generated method stub
        if (checkReadableRange(i)) {
            return (T) _data[i];
        }
        return null;
    }

    @Override
    public void set(int i, T t) {
        // TODO Auto-generated method stub
        if (checkReadableRange(i)) {
            _data[i] = t;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public int find(T t) {
        // TODO Auto-generated method stub
        for (int i = 0; i < _size; i++) {
            if (_data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }
    
    public int findReverse(T t) {
        // TODO Auto-generated method stub
        for (int i=_size-1; i>=0; i--) {
            if (_data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
	@Override
    public LinearList<T> sort() {
    	Object a[] = new Object[_size];
    	for(int i=0;i<_size;i++) {
    		a[i] = _data[i];
    	}
    	Arrays.sort(a);
    	SeqList<T> ret = new SeqList<T>(_size,null);
    	for(int i=0;i<_size;i++) {
    		ret.set(i, (T) a[i]);
    	}
        return (LinearList<T>) ret;
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
