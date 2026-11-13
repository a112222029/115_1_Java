// 類別：RoverBattery（第 5 週範例）
// 意義：探測車的電池，保存「目前」能源，數值會隨探索和充電改變。
// 作用：示範用 private 欄位保護狀態，外部只能透過 explore、charge 這些方法依規則修改能源。
// 建構前提：initialEnergy 為 0～20；本週不處理不合法的呼叫。
public class RoverBattery {
    // energy：目前能源格數（0～20）。宣告為 private，外部不能直接改。
    private int energy;

    // 建構子：以 initialEnergy 設定電池的初始能源。
    public RoverBattery(int initialEnergy) {
        energy = initialEnergy;
    }

    // getEnergy：回傳目前能源。
    public int getEnergy() {
        return energy;
    }

    // explore：探索一次，消耗 6 格能源。能源不足 6 格時拒絕並回傳 false，能源不變；成功回傳 true。
    public boolean explore() {
        if (energy < 6) {
            return false;
        }
        energy -= 6;
        return true;
    }

    // charge：充電一次，增加 5 格能源，最多 20 格。已經是 20 格時拒絕並回傳 false；成功回傳 true。
    public boolean charge() {
        if (energy == 20) {
            return false;
        }
        energy += 5;
        if (energy > 20) {
            energy = 20;
        }
        return true;
    }
}
