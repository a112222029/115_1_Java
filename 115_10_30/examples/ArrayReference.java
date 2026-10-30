import java.util.Arrays;

public class ArrayReference {
    public static void main(String[] args) {
        int[] energy = {20, 14, 8};
        int[] alias = energy;
        alias[0] = 19;
        System.out.println("alias change=" + Arrays.toString(energy));
        changeFirst(energy);
        System.out.println("element change=" + Arrays.toString(energy));
        replaceLocal(energy);
        System.out.println("after local replace=" + Arrays.toString(energy));
        int[] sameContent = {18, 14, 8};
        System.out.println("same reference=" + (energy == sameContent));
        System.out.println("same content=" + Arrays.equals(energy, sameContent));
    }

    static void changeFirst(int[] values) {
        // 傳入的是參考值的副本，仍可透過它修改共同指向的陣列元素。
        values[0] = 18;
    }

    static void replaceLocal(int[] values) {
        // 只更換方法內的參考；呼叫端變數不會改指向這個新陣列。
        values = new int[] {20, 20, 20};
        System.out.println("inside local replace=" + Arrays.toString(values));
    }
}
