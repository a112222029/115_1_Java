// 類別：Rover（探測車，第 7 週版本）
// 意義：一台火星探測車，保存目前的能源、樣本與成功行動次數，並集中管理行動規則。
// 作用：第 7 週新增三個 protected 輔助方法（spendEnergy、addSamples、countAction），
//       讓子類別 HeavyRover 不必直接碰 private 欄位，也能加入新的行動。
// 使用約定：建構時 energy 為 0～20、samples 不可為負；不自動修正不合法的參數。
public class Rover {
    // energy：目前能源格數（0～20）。
    private int energy;
    // samples：目前已收集的樣本數，達到 5 份就算任務成功。
    private int samples;
    // actionCount：成功行動的次數；被拒絕的指令不算。
    private int actionCount;

    // 建構子：設定初始能源與樣本，行動次數從 0 開始。
    public Rover(int energy, int samples) {
        this.energy = energy;
        this.samples = samples;
        actionCount = 0;
    }

    // getEnergy：回傳目前能源。
    public int getEnergy() { return energy; }
    // getSamples：回傳目前樣本數。
    public int getSamples() { return samples; }
    // getActionCount：回傳成功行動次數。
    public int getActionCount() { return actionCount; }
    // isSuccess：樣本數是否已達 5 份（任務成功的條件）。
    public boolean isSuccess() { return samples >= 5; }
    // isExhausted：能源是否已經用完（等於 0）。
    public boolean isExhausted() { return energy == 0; }
    // isStopped：成功或耗盡任一成立就停止，之後不再接受行動。
    public boolean isStopped() { return isSuccess() || isExhausted(); }

    // execute：執行一個指令（1 探索、2 掃描、3 充電、0 返回），回傳結果字串。
    // 原有指令入口。新增的 drill 不經過這裡，必須自行檢查停止。
    // 返回 的任務流程由 Mission 記住；Rover 本身不記錄返回。
    public String execute(int command) {
        if (isStopped()) { return "任務已停止"; }
        switch (command) {
            case 1: return explore();
            case 2: return scan();
            case 3: return charge();
            case 0: return "返回";
            default: return "無效指令";
        }
    }

    // spendEnergy：嘗試扣除 amount 格能源。amount 必須是正數且不超過目前能源，
    // 成功就扣除並回傳 true；否則回傳 false，能源不變。
    // TODO 1：支援擴充及局部重構。先完成扣能源，跟做 explore，再獨立改 scan。
    // 這個方法只守住扣能源規則，不負責停止、加樣本或計次。
    protected boolean spendEnergy(int amount) {
        // 佔位：尚未完成。本方法目前未接上 explore、scan，所以舊任務仍可執行。
        return false;
    }

    // addSamples：增加 amount 份樣本。只接受正數，0 或負數會被忽略。
    // 已提供；停止檢查與「整組行動」仍由呼叫它的行動方法（例如 explore）負責。
    protected void addSamples(int amount) {
        if (amount > 0) { samples += amount; }
    }

    // countAction：成功行動次數加 1。由行動方法在行動成功後呼叫。
    protected void countAction() {
        actionCount++;
    }

    // explore：探索。能源至少 6 格才執行：扣 6 格、加 2 份樣本、行動次數加 1；否則回「能源不足」。
    private String explore() {
        // 完成 spendEnergy 後，依講義 D2 示範改寫這三項更新。
        if (energy < 6) { return "能源不足"; }
        energy -= 6;
        samples += 2;
        actionCount++;
        return "成功";
    }

    // scan：掃描。能源至少 2 格才執行：扣 2 格、加 1 份樣本、行動次數加 1；否則回「能源不足」。
    private String scan() {
        // 模仿 explore 的重構；目前保留上週完成碼，不代表已完成本週要求。
        if (energy < 2) { return "能源不足"; }
        energy -= 2;
        samples++;
        actionCount++;
        return "成功";
    }

    // 充電不是扣能源，本週保留既有寫法。
    // charge：充電。能源未滿時加 5 格（最多 20）、行動次數加 1；已滿回「能源已滿」。
    private String charge() {
        if (energy == 20) { return "能源已滿"; }
        energy += 5;
        if (energy > 20) { energy = 20; }
        actionCount++;
        return "成功";
    }
}
