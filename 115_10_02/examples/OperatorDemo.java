public class OperatorDemo {
    public static void main(String[] args) {
        int energy = 7;
        System.out.println(energy / 20 * 100.0);
        System.out.println((double) (energy / 20) * 100);
        System.out.println((double) energy / 20 * 100);
        System.out.println(energy / 20.0 * 100);
        System.out.println((int) 7.9);
        System.out.println("合計=" + 2 + 3);
        System.out.println("合計=" + (2 + 3));
        int samples = 0;
        samples += 2;
        samples++;
        samples--;
        System.out.println("樣本=" + samples);

        boolean locked = false;
        System.out.println(energy >= 0 && energy <= 20);
        System.out.println(energy >= 6 && !locked);
        System.out.println(energy < 0 || energy > 20);
        // 可觀察的呼叫紀錄顯示右側是否真的被執行。
        System.out.println(false && reportCheck());
        System.out.println(true || reportCheck());
        System.out.println(true && reportCheck());

        int pearls = 53;
        System.out.println("配料份數=" + pearls / 8 + "，剩餘=" + pearls % 8);
    }

    static boolean reportCheck() {
        System.out.println("右側檢查已執行");
        return true;
    }
}
