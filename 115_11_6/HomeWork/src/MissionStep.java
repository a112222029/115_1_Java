// 類別：MissionStep（任務日誌的一步）
// 意義：任務日誌中「已經發生的一步」，保存那一步的指令、執行後能源與結果。
// 作用：讓同一步的三個值放在同一個物件裡；建立後只能讀取、不能修改（沒有 setter）。
// 契約：energy 為 0..20，result 為非 null 的既有結果代碼；command 可為任何 int。
public class MissionStep {
    // command：這一步讀到的指令編號（0 返回、1 探索、2 掃描、3 充電；其他整數為無效指令）。
    private int command;
    // energy：這一步執行「之後」的能源格數，是當下的數值快照，之後不會改變。
    private int energy;
    // result：這一步的結果，例如「成功」「能源不足」「能源已滿」「返回」「無效指令」。
    private String result;

    // 建構子：建立一筆紀錄時一次收齊三個值，所以不會出現只記到一半的紀錄。
    public MissionStep(int command, int energy, String result) {
        this.command = command;
        this.energy = energy;
        this.result = result;
    }

    // getCommand：回傳這一步的指令編號。
    public int getCommand() {
        return command;
    }

    // getEnergy：回傳這一步執行後的能源。
    public int getEnergy() {
        return energy;
    }

    // getResult：回傳這一步的結果字串。
    public String getResult() {
        return result;
    }

    // isSuccessful：這一步的結果是不是「成功」。
    // 用 equals 比較字串內容，而不是用 == 比較是不是同一個字串物件。
    public boolean isSuccessful() {
        return "成功".equals(result);
    }
}
