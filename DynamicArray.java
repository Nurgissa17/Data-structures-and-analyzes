public class DynamicArray {
    private int[] array;
    private int size;//not the actual size but the number of added elements
    private long accesses;
    private long comparisons;
    private long movements;
    public DynamicArray(){
        array = new int[1];
        size = 0;
        accesses = 0;
        comparisons = 0;
        movements = 0;
    }
    public void add(int x){
        add(size, x);
    }
    public void add(int index, int x){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("invalid index");
        }
        if(size == array.length){
            int[] newArray = new int[array.length * 2];
            for(int i = 0; i < size; i++){
                newArray[i] = array[i];
                movements++;
            }
            array = newArray;
        }
        for(int i = size; i > index; i--){
            array[i] = array[i -1];
            movements++;
        }
        array[index] = x;
        size++;
    }
    public void remove(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        for(int i = index; i < size - 1; i++){
            array[i] = array[i + 1];
            movements++;
        }
        size -= 1;
    }
    public int get(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        accesses++;
        return array[index];
    }
    public boolean contains(int x){
        for(int i = 0; i < size; i++){
            comparisons++;
            if(array[i] == x){
                return true;
            }
        }
        return false;
    }
    public long getAccesses(){
        return accesses;
    }
    public void resetAccesses(){
        accesses = 0;
    }
    public long getComparisons(){
        return comparisons;
    }
    public void resetComparisons(){
        comparisons = 0;
    }
    public long getMovements(){
        return movements;
    }
    public void resetMovements(){
        movements = 0;
    }
}