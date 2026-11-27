// 類別：StepCard（第 5 週範例）
// 意義：一張簡化的「一步紀錄」卡片，只保存指令和能源兩個值。
// 作用：示範有參數的建構子、this 與 getter；完整的三欄設計（多了結果）留給 MissionStep 作業。
public class StepCard {
    // command：這一步的指令編號。
    private int command;
    // energy：這一步執行後的能源。
    private int energy;

    // 建構子：用參數設定兩個欄位；this.command 指的是欄位，等號右邊的 command 是參數。
    public StepCard(int command, int energy) {
        this.command = command;
        this.energy = energy;
    }

    // getCommand：回傳指令編號。
    public int getCommand() {
        return command;
    }

    // getEnergy：回傳能源。
    public int getEnergy() {
        return energy;
    }
}
