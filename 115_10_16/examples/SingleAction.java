public class SingleAction {
    public static void main(String[] args) {
        int energy = 6;
        int samples = 0;
        int command = 1;
        // 先固定指令，將「分派行動」與稍後的輸入操作分開學。
        switch (command) {
            case 1:
                if (energy >= 6) {
                    energy -= 6;
                    samples += 2;
                } else {
                    System.out.println("NOT_ENOUGH_ENERGY");
                }
                break;
            case 2:
                if (energy >= 2) {
                    energy -= 2;
                    samples++;
                } else {
                    System.out.println("NOT_ENOUGH_ENERGY");
                }
                break;
            default:
                System.out.println("UNKNOWN_COMMAND");
                break;
        }
        System.out.println("energy=" + energy + ", samples=" + samples);
    }
}
