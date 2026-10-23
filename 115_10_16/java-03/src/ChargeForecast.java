public class ChargeForecast {
    public static void main(String[] args) {
        forecast(4);
        forecast(18);
    }

    static void forecast(int energy) {
        System.out.println("start=" + energy);
        for (int round = 1; round <= 3; round++) {
            energy += 5;
            if (energy > 20) {
                energy = 20;
            }
            System.out.println("round=" + round + ", energy=" + energy);
        }
        // round 只存在於 for 的有效範圍；此處可讀 energy，不能讀 round。
    }
}
