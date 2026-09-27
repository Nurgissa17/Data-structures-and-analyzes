public class MinHeap {
    private int[] heap;
    private int size;
    private long comparisons;
    public MinHeap(){
        heap = new int[1];
        size = 0;
        comparisons = 0;
    }
    public void insert(int x) {
        if (size == heap.length) {
            int[] newHeap = new int[heap.length * 2];
            for (int i = 0; i < size; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
        heap[size] = x;
        int current = size;
        size++;
        while (current > 0) {
            int parent = (current - 1) / 2;
            comparisons++;
            if (heap[parent] <= heap[current]){
                break;
            }
            int v = heap[parent];
            heap[parent] = heap[current];
            heap[current] = v;
            current = parent;
        }
    }
    public int peekMin(){
        if(size == 0){
            throw new IllegalStateException("heap is empty");
        }
        return heap[0];
    }
    public int extractMin(){
        if(size == 0){
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        int current = 0;
        while(true){
            int left = current * 2 + 1;
            int right = current * 2 + 2;
            int smallest = current;
            if(left < size){
                comparisons++;
                if(heap[left] < heap[smallest]){
                    smallest = left;
                }
            }
            if(right < size){
                comparisons++;
                if(heap[right] < heap[smallest]){
                    smallest = right;
                }
            }
            if(smallest == current){
                break;
            }
            int temp = heap[current];
            heap[current] = heap[smallest];
            heap[smallest] = temp;
            current = smallest;
        }
        return min;
    }
    public long getComparisons(){
        return comparisons;
    }
    public void resetComparisons(){
        comparisons = 0;
    }
}
