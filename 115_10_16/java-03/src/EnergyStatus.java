public class EnergyStatus {
    public static void main(String[] args) {
        showStatus(-1);
        showStatus(0);
        showStatus(5);
        showStatus(6);
        showStatus(20);
        showStatus(21);
    }

    static void showStatus(int energy) {
        // 先排除範圍外資料，後續分支才能安全解讀為有效狀態。
        if (energy < 0 || energy > 20) {
            System.out.println(energy + ": INVALID");
        } else if (energy < 6) {
            System.out.println(energy + ": LOW");
        } else {
            System.out.println(energy + ": READY");
        }
    }
}
