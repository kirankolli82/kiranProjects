package com.kiran.heaps;

/**
 * Created by Kiran Kolli on 25-09-2016.
 */
public class Heap<HeapTypeT extends Comparable<HeapTypeT>> {

    private Object[] heap;

    @SuppressWarnings("WeakerAccess")
    public Heap(HeapTypeT[] heap) {
        this.heap = heap;
    }

    @SuppressWarnings("unchecked")
    public HeapTypeT getMin() {
        if (heap == null || heap.length == 0) {
            return null;
        }

        return (HeapTypeT) heap[0];
    }

    @SuppressWarnings("unchecked")
    public HeapTypeT extractMin() {
        swapElements(0, heap.length - 1);
        HeapTypeT toReturn = (HeapTypeT) heap[heap.length - 1];
        Object[] newHeap = new Object[heap.length - 2];
        System.arraycopy(heap, 0, newHeap, 0, heap.length - 1);
        heap = newHeap;
        shiftDown(0);
        return toReturn;
    }

    public void addElement(HeapTypeT element) {
        Object[] newHeap = new Object[heap.length + 1];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
        heap[heap.length - 1] = element;
        shiftUp(heap.length - 1);
    }

    private void shiftDown(int index) {
        int left, right, toSwapWith;
        if ((2 * index + 1) < heap.length) {
            left = 2 * index + 1;
        } else {
            return;
        }

        if ((2 * index + 2) < heap.length) {
            right = 2 * index + 2;
            toSwapWith = getMin(new int[]{index, left, right});
            if (toSwapWith == index) {
                return;
            }
            swapElements(index, toSwapWith);
        } else {
            toSwapWith = getMin(new int[]{index, left});
            if (toSwapWith == index) {
                return;
            }
            swapElements(index, toSwapWith);
        }

        shiftDown(toSwapWith);
    }

    private void shiftUp(int index) {
        int parent;
        if (index % 2 == 0) {
            if (index - 2 == 0) {
                parent = 0;
            } else {
                parent = (index - 2) / 2;
            }
        } else {
            if (index - 1 == 0) {
                parent = 0;
            } else {
                parent = (index - 1) / 2;
            }
        }

        int toSwapWith = getMin(new int[]{index, parent});
        if (toSwapWith != index) {
            swapElements(index, parent);
            shiftUp(parent);
        }
    }

    public static <HeapTypeT extends Comparable<HeapTypeT>> Heap<HeapTypeT> heapify(HeapTypeT[] elements) {
        int lastPos = elements.length - 1;
        int index;
        if ((lastPos % 2) == 0) {
            index = lastPos - 1;
        } else {
            index = lastPos;
        }
        Heap<HeapTypeT> heap = new Heap<>(elements);
        while (index >= 0) {
            heap.shiftDown(index);
            index = index - 1;
        }

        return heap;
    }

    /*private void heapify(int leftIndex){
        int parent = (leftIndex - 1) / 2;
        if(leftIndex + 1 >= heap.length){
            int toSwapWith = getMin(new int[]{leftIndex, parent});
            swapElements(toSwapWith, parent);
        } else {
            int toSwapWith = getMin(new int[]{leftIndex, leftIndex + 1, parent});
            swapElements(toSwapWith, parent);
        }
    }*/


    @SuppressWarnings("unchecked")
    private void swapElements(int index, int toSwapWith) {
        HeapTypeT child = (HeapTypeT) heap[toSwapWith];
        heap[toSwapWith] = heap[index];
        heap[index] = child;
    }

    @SuppressWarnings("unchecked")
    private int getMin(int[] indexes) {
        int min = indexes[0];

        for (int index : indexes) {
            int comparison = ((HeapTypeT) heap[index]).compareTo((HeapTypeT) heap[min]);
            if (comparison < 0) {
                min = index;
            }
        }

        return min;
    }

}
