// 類別：Rover（探測車，第 7 週版本）
// 意義：一台火星探測車，保存目前的能源、樣本與成功行動次數，並集中管理行動規則。
// 作用：第 7 週新增三個 protected 輔助方法（spendEnergy、addSamples、countAction），
//       讓子類別 HeavyRover 不必直接碰 private 欄位，也能加入新的行動。
// 使用約定：建構時 energy 為 0～20、samples 不可為負；不自動修正不合法的參數。
// 【版本備註】這是 Week 07 版本；探索固定增加 2 份樣本，尚無 Week 08 的 exploreYield()。
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

    // 以下 getter 讓外部查詢狀態；欄位本身仍保持 private。
    public int getEnergy() { return energy; }
    public int getSamples() { return samples; }
    public int getActionCount() { return actionCount; }

    // 樣本達標或能源耗盡時，任務就停止。
    public boolean isSuccess() { return samples >= 5; }
    public boolean isExhausted() { return energy == 0; }
    public boolean isStopped() { return isSuccess() || isExhausted(); }

    // execute：執行一個指令（1 探索、2 掃描、3 充電、0 返回），回傳結果字串。
    // 原有指令入口。新增的 drill 不經過這裡，必須自行檢查停止。
    // 返回的任務流程由 Mission 記住；Rover 本身不記錄返回。
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

    // 【TODO 1 已完成】spendEnergy：嘗試扣除 amount 格能源。
    // amount 必須為正數且不能超過現有能源；若失敗，能源保持不變。
    // 只負責扣能源，不負責檢查任務是否停止、增加樣本或計次。
    // protected：允許 HeavyRover 等子類別使用，不需存取 private energy。
    protected boolean spendEnergy(int amount) {
        if (amount <= 0 || amount > energy) {
            return false;
        }
        energy -= amount;
        return true;
    }

    // addSamples：只接受正數的樣本增量；子類別可重用。
    protected void addSamples(int amount) {
        if (amount > 0) { samples += amount; }
    }

    // countAction：只有成功的行動才呼叫，避免失敗也被計次。
    protected void countAction() {
        actionCount++;
    }

    // 【TODO 1 已完成】探索：扣 6 格能源，增加 2 份樣本，成功行動 +1。
    // Week 07 固定 addSamples(2)；不要混成 Week 08 的 exploreYield()。
    private String explore() {
        if (!spendEnergy(6)) {
            return "能源不足";
        }
        addSamples(2);
        countAction();
        return "成功";
    }

    // 【TODO 1 已完成】掃描：扣 2 格能源，增加 1 份樣本，成功行動 +1。
    private String scan() {
        if (!spendEnergy(2)) {
            return "能源不足";
        }
        addSamples(1);
        countAction();
        return "成功";
    }

    // 充電不是扣能源，第七週保留原本寫法。
    // 若能源未滿則加 5 格（上限 20），成功行動 +1；已滿則不計次。
    private String charge() {
        if (energy == 20) { return "能源已滿"; }
        energy += 5;
        if (energy > 20) { energy = 20; }
        actionCount++;
        return "成功";
    }
}
