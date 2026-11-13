import java.util.Arrays;

// 固定的指令腳本：同一條路線可以重跑，逐步核對預測。
public class ArrayBasics {
    public static void main(String[] args) {
        // 指令編號：0＝返回、1＝探索、2＝掃描、3＝充電；其他整數為無效指令。
        int[] script = {1, 1, 2}; // 探索 → 探索 → 掃描。
        int[] counts = new int[4]; // 索引 0–3 依序對應返回、探索、掃描、充電的次數。
        String[] labels = new String[2];
        System.out.println("script=" + Arrays.toString(script));
        System.out.println("length=" + script.length);
        System.out.println("counts=" + Arrays.toString(counts));
        System.out.println("labels=" + Arrays.toString(labels));
        for (int i = 0; i < script.length; i++) {
            System.out.println("step " + (i + 1) + ": " + script[i]);
        }
        int total = 0;
        for (int command : script) {
            total += command;
        }
        System.out.println("sum=" + total);
        // 越界練習只在副本取消下一行註解；索引最後一格為 length - 1。
        // System.out.println(script[script.length]);
    }
}
