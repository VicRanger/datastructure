package cn.edu.bupt.sdmda.ds.hash;

import java.util.LinkedList;

public class MyHash<K, V> implements IHashTable<K, V> {
    
    private LinkedList<Node>[] _tables;
    private final int INIT_CAP = 1 << 4;
    // capasity of this hash table
    private int _capasity;
    // number of stored elements in the hash table
    private int _eleNum;
    // a factor less than 1
    // if _eleNum >= factor*_capasity, we should resize
    private double _factor;

    @SuppressWarnings("unchecked")
    public MyHash() {
        // init _table
        _capasity = INIT_CAP;
        _eleNum = 0;
        _factor = 0.75f;
        _tables = new LinkedList[_capasity];
        for(int i=0,len=_tables.length;i<len;i++) {
            _tables[i] = new LinkedList<Node>();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void put(K key, V val) {
        // get index of key
        // construct a node of K,V
        // find a linkedList in _tables
        // find if a node with the same key is contained in the linkedList
        // if contained, replace it with new value
        // if not, insert it to the linkedlist
        // REMEMBER the element number is increased
        // REMEMBER to check if resize is necessary
        int ind = getIdx(key);
        Node node = new Node(key,val);
        LinkedList<Node> list = _tables[ind];
        int indInList = list.indexOf(node);
        if(indInList != -1) {
            node = (Node)list.get(indInList);
            node._val = val;
        }else {
            list.add(node);
            _eleNum += 1;
            if(_eleNum > _capasity * _factor) {
                resize();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void resize() {
        _capasity <<= 1;
        LinkedList<Node>[] tTables = new LinkedList[_capasity];
        for(int i=0;i<_capasity;i++) {
            tTables[i] = new LinkedList<Node>();
        }
        for(int i=0;i<_capasity>>1;i++) {
            for(int j=0;j<_tables[i].size();j++) {
                Node node = _tables[i].get(j);
                tTables[getIdx(node._key)].add(node);
            }
        }
        _tables = tTables;
    }

    @Override
    public V get(K key) {
        // get the value of key
        // if not found, return null
        int ind = getIdx(key);
        LinkedList<Node> list = _tables[ind];
        int indInList  = list.indexOf(new Node(key,null));
        if(indInList != -1) {
            return list.get(indInList)._val;
        }else {
            return null;
        }
    }

    @Override
    public V remove(K key) {
        int ind = getIdx(key);
        LinkedList<Node> list = _tables[ind];
        int indInList  = list.indexOf(new Node(key,null));
        if(indInList != -1) {
            V ret = list.get(indInList)._val;
            list.remove(indInList);
            return ret;
        }else {
            return null;
        }
    }

    @Override
    public int getIdx(K key) {
        // design a method to get index from key
        int h = ((Object)key).hashCode();
        long ret = (long)(h*h+0x1234)&(_capasity-1);
        return (int)ret;
    }

    // Node represent a K,V pair
    class Node {
        K _key;
        V _val;

        Node(K key, V val) {
            _key = key;
            _val = val;
        }

        // override this method to make
        // node1.equals(node2) return true
        // when node1.key == node2._key
        // node1.val and node2.val is NOT CONSIDERATE HERE!
        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            if(_key.equals(((Node)obj)._key)) {
                return true;
            }
            return false;
        }
    }
}
