package cn.edu.bupt.sdmda.main;

import cn.edu.bupt.sdmda.ds.linearlist.LinearList;
import cn.edu.bupt.sdmda.ds.linearlist.MyCircleList;
import cn.edu.bupt.sdmda.ds.linearlist.MyDoubleCircleList;
import cn.edu.bupt.sdmda.ds.linearlist.MyDoubleList;
import cn.edu.bupt.sdmda.ds.linearlist.MyLinkedList;
import cn.edu.bupt.sdmda.ds.linearlist.SeqList;
import cn.edu.bupt.sdmda.ds.tree.Heap;

public class MyDSMain {

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
