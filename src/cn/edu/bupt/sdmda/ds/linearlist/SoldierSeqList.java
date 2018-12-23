package cn.edu.bupt.sdmda.ds.linearlist;

public class SoldierSeqList<T> extends SeqList<T>{
    
    public SoldierSeqList(int s, T init) {
        init(s+1, init);
    }
    
    public SoldierSeqList() {
        init(1, null);
    }
    
    @Override
    public boolean isEmpty() {
        return _size == 1;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return _size-1;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        _size = 1;
    }
    
    @Override
    public void insert(int i, T t) {
        super.insert(i+1,t);
    }
    
    @Override
    public void delete(T t) {
        // TODO Auto-generated method stub
        super.delete(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deleteAt(int i) {
        return super.deleteAt(i+1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int i) {
        return super.get(i+1);
    }

    @Override
    public void set(int i, T t) {
        set(i+1,t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int find(T t) {
        // TODO Auto-generated method stub
        _data[0] = t;
        int i = _size-1;
        while(!_data[i].equals(t)) i--;
        _data[0] = null;
        return i-1;
    }
    
}
