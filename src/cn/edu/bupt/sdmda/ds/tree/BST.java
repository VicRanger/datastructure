package cn.edu.bupt.sdmda.ds.tree;

public class BST<T extends Comparable> {
    BiTreeNode<Data> _root;

    class Data {
        T v;
        int size;

        Data(T _v) {
            v = _v;
        }
    }

    public BST() {
        _root = null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void insert(T x) {
        if (_root == null) {
            _root = new BiTreeNode(new Data(x));
            _root._data.size = 1;
        } else {
            BiTreeNode<Data> node = _root;
            while (x.compareTo(node._data.v) <= 0 && node._leftChild != null
                    || x.compareTo(node._data.v) > 0 && node._rightChild != null) {

                if (x.compareTo(node._data.v) <= 0) {
                    node = node._leftChild;
                } else {
                    node = node._rightChild;
                }
            }
            if (x.compareTo(node._data.v) <= 0) {
                node._leftChild = new BiTreeNode(new Data(x));
                node._leftChild._parent = node;
                updateSize(node._leftChild);
            } else {
                node._rightChild = new BiTreeNode(new Data(x));
                node._rightChild._parent = node;
                updateSize(node._rightChild);
            }
        }
    }

    private void swap(BiTreeNode<Data> pa, BiTreeNode<Data> son) {
        Data temp = pa._data;
        pa._data = son._data;
        son._data = temp;
    }

    private int getLeftSize(BiTreeNode<Data> node) {
        return node._leftChild == null ? 0 : node._leftChild._data.size;
    }

    private int getRightSize(BiTreeNode<Data> node) {
        return node._rightChild == null ? 0 : node._rightChild._data.size;
    }

    @SuppressWarnings("unchecked")
    private void adjust(BiTreeNode<Data> node) {
        if (node._leftChild != null && node._data.v.compareTo(node._leftChild._data.v) <= 0) {
            swap(node, node._leftChild);
            adjust(node._leftChild);
        } else if (node._rightChild != null && node._data.v.compareTo(node._rightChild._data.v) > 0) {
            swap(node, node._rightChild);
            adjust(node._rightChild);
        }
    }

    private void updateSize(BiTreeNode<Data> node) {
        while (node != null) {
            node._data.size = getLeftSize(node) + getRightSize(node) + 1;
            node = node._parent;
        }
    }

    public void top() {
        
    }
    
    @SuppressWarnings("unchecked")
    public void delete(T x) {
        BiTreeNode<Data> node = _root;
        BiTreeNode<Data> pa = null;
        while (x.compareTo(node._data.v) < 0 && node._leftChild != null
                || x.compareTo(node._data.v) > 0 && node._rightChild != null) {

            if (x.compareTo(node._data.v) < 0) {
                node = node._leftChild;
            } else if (x.compareTo(node._data.v) > 0) {
                pa = node;
                node = node._rightChild;
            }
        }

        if (x.compareTo(node._data.v) == 0) {
            if (getLeftSize(node) + getRightSize(node) == 0) {
                if (pa._leftChild == node) {
                    pa._leftChild = null;
                } else {
                    pa._rightChild = null;
                }
                updateSize(pa);
                return;
            }
            if (node._rightChild == null
                    || node._leftChild != null && node._leftChild._data.size > node._rightChild._data.size) {
                BiTreeNode<Data> max = findMaxInLeft(node);
                swap(node, max);
                adjust(max);
            } else {
                BiTreeNode<Data> min = findMinInRight(node);
                swap(node, min);
                adjust(min);
            }
        }

    }

    private BiTreeNode<Data> findMaxInLeft(BiTreeNode<Data> node) {
        BiTreeNode<Data> n = node._leftChild;
        while (n._rightChild != null) {
            n = n._rightChild;
        }
        return n;
    }

    private BiTreeNode<Data> findMinInRight(BiTreeNode<Data> node) {
        BiTreeNode<Data> n = node._rightChild;
        while (n._leftChild != null) {
            n = n._leftChild;
        }
        return n;
    }

}
