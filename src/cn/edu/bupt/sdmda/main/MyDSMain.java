package cn.edu.bupt.sdmda.main;

import cn.edu.bupt.sdmda.ds.linearlist.BinarySearchSeqList;
import cn.edu.bupt.sdmda.ds.linearlist.LinearList;
import cn.edu.bupt.sdmda.ds.linearlist.MyCircleList;
import cn.edu.bupt.sdmda.ds.linearlist.MyDoubleCircleList;
import cn.edu.bupt.sdmda.ds.linearlist.MyDoubleList;
import cn.edu.bupt.sdmda.ds.linearlist.MyLinkedList;
import cn.edu.bupt.sdmda.ds.linearlist.SeqList;
import cn.edu.bupt.sdmda.ds.linearlist.SoldierSeqList;
import cn.edu.bupt.sdmda.ds.tree.Heap;

public class MyDSMain {
    
    private static long time;
    private static long totalTime;
    private static void startTick() {
        time = System.currentTimeMillis();
    }
    private static void endTick() {
        totalTime += System.currentTimeMillis()-time;
        System.out.print(".");
    }
    private static void clear() {
        totalTime = 0;
        System.out.println();
    }
    private static long getTick() {
        return totalTime;
    }
    
    public static void testSortMain(String[] args) {
        int N = Integer.parseInt(args[1]);
        int M = Integer.parseInt(args[2]);
        double data[] = new double[N];
        double t[] = new double[N];
        for(int i=0;i<N;i++) {
            data[i] = (Math.random()*1e9);
        }
        clear();
        for(int i=0;i<M;i++) {
            System.arraycopy(data, 0, t, 0, N);
            startTick();
            t = SortAlgorithm.QSort(t);
//            for(int j=0;j<N;j++) {System.out.print(t[j]+" ");}
//            System.out.println();
            endTick();
        }
        System.out.println();
        System.out.println("QSort: "+getTick()+"ms");
        clear();
        for(int i=0;i<M;i++) {
            System.arraycopy(data, 0, t, 0, N);
            startTick();
            t = SortAlgorithm.QuickSort(t);
//            for(int j=0;j<N;j++) {System.out.print(t[j]+" ");}
//            System.out.println();
            endTick();
        }
        System.out.println();
        System.out.println("QuickSort: "+getTick()+"ms");
        clear();
        for(int i=0;i<M;i++) {
            System.arraycopy(data, 0, t, 0, N);
            startTick();
            t = SortAlgorithm.MergeSort(t);
//            for(int j=0;j<N;j++) {System.out.print(t[j]+" ");}
//            System.out.println();
            endTick();
        }
        System.out.println();
        System.out.println("MergeSort: "+getTick()+"ms");
        clear();
        for(int i=0;i<M;i++) {
            System.arraycopy(data, 0, t, 0, N);
            startTick();
            t = SortAlgorithm.InsertionSort(t);
//          for(int j=0;j<N;j++) {System.out.print(t[j]+" ");}
//            System.out.println();
            endTick();
        }
        System.out.println();
        System.out.println("InsertionSort: "+getTick()+"ms");
        clear();
        for(int i=0;i<M;i++) {
            System.arraycopy(data, 0, t, 0, N);
            startTick();
            t = SortAlgorithm.SelectionSort(t);
//            for(int j=0;j<N;j++) {System.out.print(t[j]+" ");}
//            System.out.println();
            endTick();
        }
        System.out.println();
        System.out.println("SelectionSort: "+getTick()+"ms");
        clear();
        for(int i=0;i<M;i++) {
            System.arraycopy(data, 0, t, 0, N);
            startTick();
            t = SortAlgorithm.BubbleSort(t);
//            for(int j=0;j<N;j++) {System.out.print(t[j]+" ");}
//            System.out.println();
            endTick();
        }
        System.out.println();
        System.out.println("BubbleSort: "+getTick()+"ms");
        

    }
    
    

    public static void testLinearlistSearchMain(String[] args) {
        SeqList<Integer> sql = new SeqList<Integer>();
        SoldierSeqList<Integer> ssql = new SoldierSeqList<Integer>();
        BinarySearchSeqList<Integer> bssql = new BinarySearchSeqList<Integer>();
        int n = Integer.parseInt(args[1]);
        System.out.println("n="+args[1]);
        for(int i=0;i<n;i++) {
            sql.insert(i, i);
            ssql.insert(i, i);
            bssql.insert(i);
        }
        long start = System.currentTimeMillis();
        for(int i=0;i<n;i++) {
            int p = sql.findReverse(i);
//            System.out.print(p+" ");
        }
        System.out.println();
        System.out.println("Normal Search Complete");
        System.out.println("time:"+(System.currentTimeMillis()-start)+"ms");
        start = System.currentTimeMillis();
        for(int i=0;i<n;i++) {
            int p = ssql.find(i);
//            System.out.print(p+" ");
        }
        System.out.println();
        System.out.println("Soldier Search Complete");
        System.out.println("time:"+(System.currentTimeMillis()-start)+"ms");
        start = System.currentTimeMillis();
        for(int i=0;i<n;i++) {
            int p = bssql.findExact(i);
//            System.out.print(p+" ");
        }
        System.out.println();
        System.out.println("Binary Search Complete");
        System.out.println("time:"+(System.currentTimeMillis()-start)+"ms");
    }
    
    public static void testHeapMain(String[] args) {
        Heap<Integer> heap = new Heap<Integer>(200);
        int i = 1;
        System.out.println("inserting one half:");
        for (; i <= args.length / 2; i++) {
            heap.insert(new Integer(args[i]));
            System.out.println(new Integer(args[i]));
        }
        System.out.println("getting the minimum");
        System.out.println(heap.top());
        System.out.println("deleteing");
        heap.delete();
        System.out.println("getting the minimum");
        System.out.println(heap.top());
        System.out.println("inserting another half:");
        for (; i < args.length; i++) {
            heap.insert(new Integer(args[i]));
            System.out.println(new Integer(args[i]));
        }
        System.out.println("getting the minimum");
        System.out.println(heap.top());
    }

    public static void testLinearListPlusMain(String[] args) {

        System.out.println("=========SeqList=========");

        SeqList<Integer> seql = new SeqList<Integer>(args.length - 1, 0);
        for (int i = 0; i < args.length - 1; ++i) {
            seql.set(i, Integer.parseInt(args[i + 1]));
        }
        testLinearListReverse(seql);
//		testLinearListSort(seql);
        testLinearList(seql);

        System.out.println("=========MyLinkedList=========");

        MyLinkedList<Integer> lnkl = new MyLinkedList<Integer>(args.length - 1, 0);
        for (int i = 0; i < args.length - 1; ++i) {
            lnkl.set(i, Integer.parseInt(args[i + 1]));
        }

        testLinearListReverse(lnkl);
//		testLinearListSort(lnkl);
        testLinearList(lnkl);

        System.out.println("=========MyCircleList=========");

        MyCircleList<Integer> ccll = new MyCircleList<Integer>(args.length - 1, 0);
        for (int i = 0; i < args.length - 1; ++i) {
            ccll.set(i, Integer.parseInt(args[i + 1]));
        }
        testLinearList(ccll);

        System.out.println("=========MyDoubleList=========");

        MyDoubleList<Integer> dbll = new MyDoubleList<Integer>(args.length - 1, 0);
        for (int i = 0; i < args.length - 1; ++i) {
            dbll.set(i, Integer.parseInt(args[i + 1]));
        }
        testLinearList(dbll);

        System.out.println("=========MyDoubleCircleList=========");

        MyDoubleCircleList<Integer> dblccll = new MyDoubleCircleList<Integer>(args.length - 1, 0);
        for (int i = 0; i < args.length - 1; ++i) {
            dblccll.set(i, Integer.parseInt(args[i + 1]));
        }
        testLinearList(dblccll);

    }

    public static void testLinearListSort(LinearList<Integer> ll) {
        System.out.println("reversing...");
        ll.reverse(0, ll.getSize() / 2);
        printInfoOfLinearList(ll);
        System.out.println("Sorting");
        printInfoOfLinearList(ll.sort());
        ll.reverse(0, ll.getSize() / 2);
    }

    public static void testLinearListReverse(LinearList<Integer> ll) {
        System.out.println("testing reverse()");
        System.out.println("reverse(0,size-1)");
        ll.reverse(0, ll.getSize() - 1);
        printInfoOfLinearList(ll);
        System.out.println("reverse(0,size-1)");
        ll.reverse(0, ll.getSize() - 1);
        printInfoOfLinearList(ll);
        System.out.println("reverse(0,size/2)");
        ll.reverse(0, ll.getSize() / 2);
        printInfoOfLinearList(ll);
        System.out.println("reverse(0,size/2)");
        ll.reverse(0, ll.getSize() / 2);
        printInfoOfLinearList(ll);
    }

    private static void testLinearList(LinearList<Integer> ll) {
        Integer ele1 = ll.get(0) + ll.get(1);
        Integer ele2 = ll.get(0) - ll.get(1);
        Integer ele3 = ll.get(0) * ll.get(1);

        // print size and all
        printInfoOfLinearList(ll);

        // insert in head
        // insert in mid
        // insert in tail
        System.out.println("inserting 3 elements");
        ll.insert(0, ele1);
        ll.insert(ll.getSize() / 2, ele2);
        ll.insert(ll.getSize(), ele3);

        // print size and all
        printInfoOfLinearList(ll);

        // delete in head
        // delete in mid
        // delete in tail
        System.out.println("deleting 3 elements");

        System.out.print(ll.deleteAt(0) + "\t");
        System.out.print(ll.deleteAt(ll.getSize() / 2) + "\t");
        System.out.println(ll.deleteAt(ll.getSize() - 1));

        // print size and all
        printInfoOfLinearList(ll);

        // find element like the tail
        // delete element like the tail
        System.out.println("finding last element");
        Integer found = ll.find(ll.get(ll.getSize() - 1));
        System.out.println(found);
        ll.delete(ll.get(found));

        // print size and all
        printInfoOfLinearList(ll);

        // clear
        System.out.println("clearing");
        ll.clear();
        // print size and all
        printInfoOfLinearList(ll);
    }

    private static void printInfoOfLinearList(LinearList<Integer> ll) {
        System.out.println("List Summay:");
        System.out.println(ll.getSize());
        for (int i = 0; i < ll.getSize(); ++i) {
            System.out.print(ll.get(i) + "\t");
        }
        System.out.println("");
    }
    

}
