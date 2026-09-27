    import java.util.Random;

    public class Benchmark {
        public static void main(String[] args){
            int[] sizes = {100, 1000, 10000, 100000};
            for(int n : sizes){
                randomAccess(n);
                search(n);
                insertionRemoval(n);
                heapWorkload(n);
            }
        }
        public static void randomAccess(int n){
            Random random = new Random(42);
            int[] values = new int[n];
            int[] indices = new int[10000];
            for(int i = 0; i < n; i++){
                values[i] = random.nextInt();
            }
            for(int i = 0; i < 10000; i++){
                indices[i] = random.nextInt(n);
            }
            DynamicArray dynamicArray = new DynamicArray();
            LinkedList linkedList = new LinkedList();
            for(int i = 0; i < n; i++){
                dynamicArray.add(values[i]);
            }
            for(int i = n - 1; i >= 0; i--){
                linkedList.add(0, values[i]);
            }
            long arrayTotalTime = 0;
            long listTotalTime = 0;
            long arrayAccesses = 0;
            long listAccesses = 0;
            for(int run = 0; run < 5; run++){
                dynamicArray.resetAccesses();
                long start = System.nanoTime();
                for(int index : indices){
                    dynamicArray.get(index);
                }
                long end = System.nanoTime();
                arrayTotalTime += end - start;
                arrayAccesses = dynamicArray.getAccesses();

                linkedList.resetAccesses();
                start = System.nanoTime();
                for(int index : indices){
                    linkedList.get(index);
                }
                end = System.nanoTime();
                listTotalTime += end - start;
                listAccesses = linkedList.getAccesses();
            }
            System.out.println("n = " + n);
            System.out.println("Dynamic Array average time: " + arrayTotalTime / 5.0 + " ns");
            System.out.println("Dynamic Array accesses: " + arrayAccesses);
            System.out.println("Linked List average time: " + listTotalTime / 5.0 + " ns");
            System.out.println("Linked List accesses: " + listAccesses);
        }
        public static void search(int n){
            Random random = new Random(42);
            int[] values = new int[n];
            int[] searchValues = new int[1000];
            for(int i = 0; i < n; i++){
                values[i] = random.nextInt();
            }
            for(int i = 0; i < 1000; i++){
                searchValues[i] = random.nextInt();
            }
            DynamicArray dynamicArray = new DynamicArray();
            LinkedList linkedList = new LinkedList();
            for(int i = 0; i < n; i++){
                dynamicArray.add(values[i]);
            }
            for(int i = n - 1; i >= 0; i--){
                linkedList.add(0, values[i]);
            }
            long arrayTotalTime = 0;
            long listTotalTime = 0;
            long arrayComparisons = 0;
            long listComparisons = 0;
            for(int run = 0; run < 5; run++){
                dynamicArray.resetComparisons();
                long start = System.nanoTime();
                for(int value : searchValues){
                    dynamicArray.contains(value);
                }
                long end = System.nanoTime();
                arrayTotalTime += end - start;
                arrayComparisons = dynamicArray.getComparisons();
                linkedList.resetComparisons();
                start = System.nanoTime();
                for(int value : searchValues){
                    linkedList.contains(value);
                }
                end = System.nanoTime();
                listTotalTime += end - start;
                listComparisons = linkedList.getComparisons();
            }
            System.out.println("n = " + n);
            System.out.println("Dynamic Array search average time: " + arrayTotalTime / 5.0 + " ns");
            System.out.println("Dynamic Array comparisons: " + arrayComparisons);
            System.out.println("Linked List search average time: " + listTotalTime / 5.0 + " ns");
            System.out.println("Linked List comparisons: " + listComparisons);
        }
        public static void insertionRemoval(int n){
            Random random = new Random(42);
            int[] values = new int[n];
            int[] insertValues = new int[1000];
            for(int i = 0; i < n; i++){
                values[i] = random.nextInt();
            }
            for(int i = 0; i < 1000; i++){
                insertValues[i] = random.nextInt();
            }
            long arrayBeginInsertTime = 0;
            long listBeginInsertTime = 0;
            long arrayMiddleInsertTime = 0;
            long listMiddleInsertTime = 0;
            long arrayBeginRemoveTime = 0;
            long listBeginRemoveTime = 0;
            long arrayMiddleRemoveTime = 0;
            long listMiddleRemoveTime = 0;
            long arrayBeginInsertMoves = 0;
            long listBeginInsertAccesses = 0;
            long arrayMiddleInsertMoves = 0;
            long listMiddleInsertAccesses = 0;
            long arrayBeginRemoveMoves = 0;
            long listBeginRemoveAccesses = 0;
            long arrayMiddleRemoveMoves = 0;
            long listMiddleRemoveAccesses = 0;
            int middle = n / 2;
            for(int run = 0; run < 5; run++){
                DynamicArray dynamicArray = createArray(values);
                LinkedList linkedList = createList(values);
                dynamicArray.resetMovements();
                long start = System.nanoTime();
                for(int value : insertValues){
                    dynamicArray.add(0, value);
                }
                long end = System.nanoTime();
                arrayBeginInsertTime += end - start;
                arrayBeginInsertMoves += dynamicArray.getMovements();
                linkedList.resetAccesses();
                start = System.nanoTime();
                for(int value : insertValues){
                    linkedList.add(0, value);
                }
                end = System.nanoTime();
                listBeginInsertTime += end - start;
                listBeginInsertAccesses += linkedList.getAccesses();
                dynamicArray = createArray(values);
                linkedList = createList(values);
                dynamicArray.resetMovements();
                start = System.nanoTime();
                for(int value : insertValues){
                    dynamicArray.add(middle, value);
                }
                end = System.nanoTime();
                arrayMiddleInsertTime += end - start;
                arrayMiddleInsertMoves += dynamicArray.getMovements();
                linkedList.resetAccesses();
                start = System.nanoTime();
                for(int value : insertValues){
                    linkedList.add(middle, value);
                }
                end = System.nanoTime();
                listMiddleInsertTime += end - start;
                listMiddleInsertAccesses += linkedList.getAccesses();
                dynamicArray = createArray(values);
                linkedList = createList(values);
                for(int i = 0; i < 1000; i++){
                    int value = dynamicArray.get(0);
                    dynamicArray.resetMovements();
                    start = System.nanoTime();
                    dynamicArray.remove(0);
                    end = System.nanoTime();
                    arrayBeginRemoveTime += end - start;
                    arrayBeginRemoveMoves += dynamicArray.getMovements();
                    dynamicArray.add(0, value);
                    int listValue = linkedList.get(0);
                    linkedList.resetAccesses();
                    start = System.nanoTime();
                    linkedList.remove(0);
                    end = System.nanoTime();
                    listBeginRemoveTime += end - start;
                    listBeginRemoveAccesses += linkedList.getAccesses();
                    linkedList.add(0, listValue);
                }
                dynamicArray = createArray(values);
                linkedList = createList(values);
                for(int i = 0; i < 1000; i++){
                    int value = dynamicArray.get(middle);
                    dynamicArray.resetMovements();
                    start = System.nanoTime();
                    dynamicArray.remove(middle);
                    end = System.nanoTime();
                    arrayMiddleRemoveTime += end - start;
                    arrayMiddleRemoveMoves += dynamicArray.getMovements();
                    dynamicArray.add(middle, value);
                    int listValue = linkedList.get(middle);
                    linkedList.resetAccesses();
                    start = System.nanoTime();
                    linkedList.remove(middle);
                    end = System.nanoTime();
                    listMiddleRemoveTime += end - start;
                    listMiddleRemoveAccesses += linkedList.getAccesses();
                    linkedList.add(middle, listValue);
                }
            }
            System.out.println("Workload 3, n = " + n);
            System.out.println("Dynamic Array insert beginning: " + arrayBeginInsertTime / 5.0 + " ns, movements: " + arrayBeginInsertMoves / 5.0);
            System.out.println("Linked List insert beginning: " + listBeginInsertTime / 5.0 + " ns, accesses: " + listBeginInsertAccesses / 5.0);
            System.out.println("Dynamic Array insert middle: " + arrayMiddleInsertTime / 5.0 + " ns, movements: " + arrayMiddleInsertMoves / 5.0);
            System.out.println("Linked List insert middle: " + listMiddleInsertTime / 5.0 + " ns, accesses: " + listMiddleInsertAccesses / 5.0);
            System.out.println("Dynamic Array remove beginning: " + arrayBeginRemoveTime / 5.0 + " ns, movements: " + arrayBeginRemoveMoves / 5.0);
            System.out.println("Linked List remove beginning: " + listBeginRemoveTime / 5.0 + " ns, accesses: " + listBeginRemoveAccesses / 5.0);
            System.out.println("Dynamic Array remove middle: " + arrayMiddleRemoveTime / 5.0 + " ns, movements: " + arrayMiddleRemoveMoves / 5.0);
            System.out.println("Linked List remove middle: " + listMiddleRemoveTime / 5.0 + " ns, accesses: " + listMiddleRemoveAccesses / 5.0);
        }
        public static void heapWorkload(int n){
            Random random = new Random(42);
            int[] values = new int[n];
            for(int i = 0; i < n; i++){
                values[i] = random.nextInt();
            }
            long insertTotalTime = 0;
            long extractTotalTime = 0;
            long insertComparisons = 0;
            long extractComparisons = 0;
            boolean nonDecreasing = true;
            for(int run = 0; run < 5; run++){
                MinHeap heap = new MinHeap();
                heap.resetComparisons();
                long start = System.nanoTime();
                for(int value : values){
                    heap.insert(value);
                }
                long end = System.nanoTime();
                insertTotalTime += end - start;
                insertComparisons += heap.getComparisons();
                heap.resetComparisons();
                int previous = Integer.MIN_VALUE;
                start = System.nanoTime();
                for(int i = 0; i < n; i++){
                    int current = heap.extractMin();
                    if(current < previous){
                        nonDecreasing = false;
                    }
                    previous = current;
                }
                end = System.nanoTime();
                extractTotalTime += end - start;
                extractComparisons += heap.getComparisons();
            }
            System.out.println("Workload 4, n = " + n);
            System.out.println("Heap insert average time: " + insertTotalTime / 5.0 + " ns");
            System.out.println("Heap insert comparisons: " + insertComparisons / 5.0);
            System.out.println("Heap extract average time: " + extractTotalTime / 5.0 + " ns");
            System.out.println("Heap extract comparisons: " + extractComparisons / 5.0);
            System.out.println("Non-decreasing: " + nonDecreasing);
        }
        public static DynamicArray createArray(int[] values){
            DynamicArray dynamicArray = new DynamicArray();
            for(int value : values){
                dynamicArray.add(value);
            }
            return dynamicArray;
        }
        public static LinkedList createList(int[] values){
            LinkedList linkedList = new LinkedList();
            for(int i = values.length - 1; i >= 0; i--){
                linkedList.add(0, values[i]);
            }
            return linkedList;
        }
    }