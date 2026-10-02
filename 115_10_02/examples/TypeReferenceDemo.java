public class TypeReferenceDemo {
    public static void main(String[] args) {
        byte smallCount = 20;
        short storage = 300;
        int energy = 7;
        long distance = 3000000000L;
        float sensor = 3.5F;
        double percent = 35.0;
        char zone = 'A';
        boolean locked = false;
        System.out.println(smallCount + " " + storage + " " + energy + " " + distance);
        System.out.println(sensor + " " + percent + " " + zone + " " + locked);

        int backupEnergy = energy;
        energy = 6;
        System.out.println("energy=" + energy + ", backupEnergy=" + backupEnergy);

        String consoleName = "赤砂號";
        String logName = consoleName;
        consoleName = "晨星號";
        // 重新指定 consoleName，沒有修改原本的 String 物件。
        System.out.println(consoleName + " / " + logName);

        String code = "MARS";
        String receivedCode = new String("MARS");
        // new String 只為刻意產生不同參考，平日不需要這樣複製字串。
        System.out.println(code == receivedCode);
        System.out.println(code.equals(receivedCode));
    }
}
