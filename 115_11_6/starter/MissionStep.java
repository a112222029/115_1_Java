// 類別：MissionStep（第 5 週學生實作）
// 意義：任務日誌中「已經發生的一步」，保存那一步的指令、執行後能源與結果。
// 作用：取代第 4 週的三條平行清單，讓同一步的三個值放在同一個物件裡。
// 目前的回傳值都是佔位，只是讓骨架能編譯執行，不代表已完成需求。
public class MissionStep {
    // command：這一步讀到的指令編號（0 返回、1 探索、2 掃描、3 充電；其他整數為無效指令）。
    private int command;
    // energy：這一步執行「之後」的能源格數，是當下的數值快照，之後不會改變。
    private int energy;
    // result：這一步的結果，例如「成功」「能源不足」「能源已滿」「返回」「無效指令」。
    private String result;

    // 建構子：建立一筆紀錄時一次收齊三個值，所以不會出現只記到一半的紀錄。
    public MissionStep(int command, int energy, String result) {
        // TODO 1：以 this 將三個參數保存到目前物件的三個欄位。
    }

    // getCommand：回傳這一步的指令編號。
    // TODO 2：三個 getter 應讀取本物件對應欄位，取代以下佔位回傳值。
    public int getCommand() {
        return 0;
    }

    // getEnergy：回傳這一步執行後的能源。
    public int getEnergy() {
        return 0;
    }

    // getResult：回傳這一步的結果字串。
    public String getResult() {
        return "待處理";
    }

    // isSuccessful：這一步的結果是不是「成功」；是回傳 true，否則回傳 false。
    public boolean isSuccessful() {
        // TODO 3：以內容比較判斷本物件的 result 是否為 成功。
        return false;
    }
}
