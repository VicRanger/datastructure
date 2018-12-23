package cn.edu.bupt.sdmda.ds.linearlist;

import java.util.Arrays;

public class BinarySearchSeqList<T> extends SeqList<T> {
    
    public BinarySearchSeqList(int s, T init) {
        init(s, init);
    }

    public BinarySearchSeqList() {
        init(0, null);
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

    public void insert(T t) {
        if(_size==0) {
            super.insert(0,t);
            return;
        }
        int pos = find(t);
        super.insert(pos,t);
    }
    
    @Override
    public void set(int i, T t) {
        super.set(i, t);
        sortSelf();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int find(T t) {
        // TODO Auto-generated method stub
        int l=0,r=_size,mid=(l+r)>>1;
        while(l<r) {
            if(((Comparable<Object>)_data[mid]).compareTo((Comparable<Object>)t)==1) {
                r=mid;
            }else {
                l=mid+1;
            }
            mid = (l+r)>>1;
        }
        return mid;
    }
    
    public int findExact(T t) {
        int l=0,r=_size,mid=(l+r)>>1;
        while(l<r) {
            if(((Comparable<Object>)_data[mid]).compareTo((Comparable<Object>)t)==1) {
                r=mid-1;
            }else if(((Comparable<Object>)_data[mid]).compareTo((Comparable<Object>)t)==-1){
                l=mid+1;
            }else {
                r=mid;
            }
            mid = (l+r)>>1;
        }
        return _data[mid]==t?mid:-1;
    }

    public void sortSelf() {
        Object a[] = new Object[_size];
        for(int i=0;i<_size;i++) {
            a[i] = _data[i];
        }
        Arrays.sort(a);
        for(int i=0;i<_size;i++) {
            _data[i] = a[i];
        }
    }
}
