import java.util.ArrayList;

// 類別：SnapshotDemo（第 5 週範例）
// 意義：示範「目前狀態」和「歷史紀錄」的差別。
// 作用：電池的能源會一直改變；每次行動後建立一張 StepCard 抄下當下的能源，之後電池再改變，舊紀錄也不受影響。
public class SnapshotDemo {
    // main：程式進入點。電池先探索再充電，每次行動後記一筆，最後比較紀錄與目前能源。
    public static void main(String[] args) {
        RoverBattery battery = new RoverBattery(20);
        ArrayList<StepCard> history = new ArrayList<>();
        battery.explore();
        history.add(new StepCard(1, battery.getEnergy()));
        battery.charge();
        history.add(new StepCard(3, battery.getEnergy()));
        for (StepCard step : history) {
            System.out.println("指令=" + step.getCommand() + ", 能源=" + step.getEnergy());
        }
        System.out.println("目前能源=" + battery.getEnergy());
        System.out.println("各自獨立的紀錄=" + (history.get(0) != history.get(1)));
    }
}
