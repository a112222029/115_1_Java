// 類別：BatteryDemo（第 5 週範例）
// 意義：示範如何使用 RoverBattery 物件。
// 作用：透過 explore、charge 改變電池能源，觀察能源不足時的拒絕，以及充電的 20 格上限。
public class BatteryDemo {
    // main：程式進入點。建立兩顆不同初始能源的電池，依序呼叫方法並印出回傳值與能源。
    public static void main(String[] args) {
        RoverBattery low = new RoverBattery(5);
        System.out.println("探索=" + low.explore() + ", 能源=" + low.getEnergy());
        System.out.println("充電=" + low.charge() + ", 能源=" + low.getEnergy());
        System.out.println("探索=" + low.explore() + ", 能源=" + low.getEnergy());
        RoverBattery edge = new RoverBattery(19);
        System.out.println("充至上限=" + edge.charge() + ", 能源=" + edge.getEnergy());
        System.out.println("已充滿=" + edge.charge() + ", 能源=" + edge.getEnergy());
    }
}
