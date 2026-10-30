import java.util.Arrays;

// 固定四個計數位置：counts[k] 表示指令 k 出現幾次，不是成功行動次數。
public class CommandCounter {
    public static void main(String[] args) {
        // 指令編號：0＝返回、1＝探索、2＝掃描、3＝充電；9、-1 等其他整數為無效指令。
        int[] script = {3, 9, 0, 1, 1, -1}; // 充電、無效、返回、探索、探索、無效。
        int[] counts = new int[4]; // 索引 0–3 依序對應返回、探索、掃描、充電的次數。
        // 本例只統計整份腳本，不執行任務，也不會遇到 0 就停止計數。
        for (int command : script) {
            // 輸入可能無效；先判斷範圍，再把指令當索引。
            if (command >= 0 && command < counts.length) {
                counts[command]++;
            }
        }
        System.out.println("counts=" + Arrays.toString(counts));
    }
}
