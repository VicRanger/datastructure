package cn.edu.bupt.sdmda.ds.tree;

@SuppressWarnings("rawtypes")
public class Heap<T extends Comparable> {
    Object[] data;
    private int size;

    public Heap(int size) {
        data = new Object[size];
    }

    public void insert(T v) {
        data[++size] = v;
        adjustUp(size);
    }

    @SuppressWarnings("unchecked")
    public void adjustUp(int pos) {
        while (pos > 1 && ((T) data[pos]).compareTo((T) data[pos >> 1]) == -1) {
            T t = (T) data[pos];
            data[pos] = data[pos >> 1];
            data[pos >> 1] = t;
            pos >>= 1;
        }
    }

    @SuppressWarnings("unchecked")
    public void adjustDown(int pos) {
        while (true) {
            if ((pos << 1) <= size && ((T) data[pos]).compareTo((T) data[pos << 1]) == 1) {
                T t = (T) data[pos];
                data[pos] = data[pos << 1];
                data[pos << 1] = t;
                pos <<= 1;
            } else if ((pos << 1) + 1 <= size && ((T) data[pos]).compareTo((T) data[(pos << 1) + 1]) == 1) {
                T t = (T) data[pos];
                data[pos] = data[(pos << 1) + 1];
                data[(pos << 1) + 1] = t;
                pos = (pos << 1) + 1;
            } else {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T top() {
        return (T) data[1];
    }

    public void delete() {
        if (size <= 0)
            return;
        T ret = (T) data[1];
        data[1] = data[size--];
        adjustDown(1);
    }
}
