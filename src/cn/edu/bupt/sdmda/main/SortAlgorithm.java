package cn.edu.bupt.sdmda.main;

public class SortAlgorithm {
    public static double[] InsertionSort(double[] data) {
        double[] ret = new double[data.length];
//        double[] ret = data;
        ret[0] = data[0];
        int size = 1;
        for(int i=1,len=data.length;i<len;i++) {
            int j=0;
            while(j<i && ret[j]<data[i]) j++;
            System.arraycopy(ret, j, ret, j+1, i-j);
            ret[j] = data[i];
        }
        return ret;
    }
    public static double[] SelectionSort(double[] data) {
//        double[] ret = new double[data.length];
        double[] ret = data;
        int len = data.length;
//        System.arraycopy(data,0,ret,0,len);
        for(int i=0;i<len;i++) {
            int pos = i;
            for(int j=i+1;j<len;j++) {
                if(ret[j]<ret[pos]) {
                    pos = j;
                }
            }
            swap(ret,i,pos);
        }
        return ret;
    }
    
    public static double[] BubbleSort(double[] data) {
//        double[] ret = new double[data.length];
        double[] ret = data;
        int len = data.length;
//        System.arraycopy(data,0,ret,0,len);
        for(int i=0;i<len;i++) {
            for(int j=1;j<len-i;j++) {
                if(ret[j-1]>ret[j]) {
                    swap(ret,j-1,j);
                }
            }
        }
        return ret;
    }
    public static double[] QuickSort(double[] data) {
        double[] ret = data;
        QuickSortFunc(ret,0,ret.length-1);
        return ret;
    }
    public static void QuickSortFunc(double[] data,int l,int r) {
        if(l>=r) {
            return;
        }
        int i=l,j=r;
        double p=data[l];
        while(i<j) {
            while(data[j]>=p && i<j) j--;
            data[i]=data[j];
            while(data[i]<=p && i<j) i++;
            data[j]=data[i];
        }
        data[i]=p;
        QuickSortFunc(data,l,i-1);
        QuickSortFunc(data,j+1,r);
    }
    public static double[] QSort(double[] data) {
        double[] ret = data;
        QSortFunc(ret,0,ret.length-1);
        return ret;
    }
    public static void QSortFunc(double[] data,int l,int r) {
        if(l>=r) {
            return;
        }
        int i=l,j=r;
        int pos = (int)(Math.random()*(r-l+1))+l;
        swap(data,l,pos);
        double p=data[l];
        while(i<j) {
            while(data[j]>=p && i<j) j--;
            data[i]=data[j];
            while(data[i]<=p && i<j) i++;
            data[j]=data[i];
        }
        data[i]=p;
        QSortFunc(data,l,i-1);
        QSortFunc(data,j+1,r);
    }
    public static double[] MergeSort(double[] data) {
        double[] ret = data;
        MergeSortFunc(ret,0,ret.length-1);
        return ret;
    }
    private static void MergeSortFunc(double[] data,int l,int r) {
        if(l==r) {
            return;
        }
        MergeSortFunc(data,l,(l+r)>>1);
        MergeSortFunc(data,((l+r)>>1)+1,r);
        int p1=l,p2=((l+r)>>1)+1;
        double[] t = new double[r-l+1];
//        System.out.println(l+" "+r);
//        for(int i=l;i<=r;i++) {
//            System.out.print(data[i]+" ");
//        }
//        System.out.println();
        for(int i=0,len=r-l+1;i<len;i++) {
            if(data[p1]<data[p2]) {
                t[i] = data[p1];
                if(++p1>(l+r)>>1) {
                    while(i<len && p2<=r) {
                        t[++i] = data[p2];
                        p2++;
                    }
                    break;
                }
            }else{
                t[i] = data[p2];
                if(++p2>r) {
                    while(i<len && p1<=(l+r)>>1) {
                        t[++i] = data[p1];
                        p1++;
                    }
                    break;
                }
            }
        }
        System.arraycopy(t, 0, data, l, r-l+1);
//        for(int i=l;i<=r;i++) {
//            System.out.print(data[i]+" ");
//        }
//        System.out.println();
    }
    private static void swap(double[] data,int i,int j) {
        double t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
