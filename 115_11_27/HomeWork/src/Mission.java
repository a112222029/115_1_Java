import java.util.ArrayList;

// 類別：Mission（任務）
// 意義：一次探測車任務，負責「流程」與「紀錄」。
// 作用：依序把腳本中的指令交給探測車執行，每一步存成一筆 MissionStep 放進日誌，
//       任務結束後提供結果、指令次數、成功與拒絕筆數、最低能源等查詢。
// 使用約定：每個新任務只呼叫一次 run，script 不可為 null；執行與讀取結果期間，
//           外部不要另外操作同一台 Rover。
public class Mission {
    // rover：執行這次任務的探測車（每個任務連到一台）。目前能源、樣本由它保存。
    private Rover rover;
    // history：任務日誌。每讀取並執行一筆指令，就加入一筆 MissionStep，順序就是執行順序。
    private ArrayList<MissionStep> history;
    // returned：是否已經收到返回指令（指令 0）；收到後為 true，不再讀後面的指令。
    private boolean returned;

    // 建構子：記住要執行任務的探測車，並準備一份空的日誌。
    public Mission(Rover rover) {
        this.rover = rover;
        history = new ArrayList<>();
        returned = false;
    }

    // run：執行一次任務腳本。
    // 依序執行腳本中的指令，每一筆的處理順序是：
    // 1. 探測車已達標或耗盡能源，就停止，不再讀後面的指令。
    //    沒讀到的指令不會進日誌，報表用「腳本長度 − 日誌筆數」算出略過幾筆。
    // 2. 執行指令，取得結果字串（成功、能源不足、返回……）。
    // 3. 把「指令、執行後的能源、結果」存成一筆 MissionStep 放進日誌。
    // 4. 指令 0 代表返回：已經在第 3 步記錄過，接著標記 returned 並結束迴圈。
    public void run(int[] script) {
        for (int i = 0; i < script.length; i++) {
            if (rover.isStopped()) { break; }
            int command = script[i];
            String result = rover.execute(command);
            history.add(new MissionStep(command, rover.getEnergy(), result));
            if (command == 0) {
                returned = true;
                break;
            }
        }
    }

    // getOutcome：回傳任務結果：「任務成功」「能源耗盡」「已返回」或「任務未完成」。
    // 依這個順序判斷；樣本達標與能源耗盡同時成立時，以成功為準。
    public String getOutcome() {
        if (rover.isSuccess()) { return "任務成功"; }
        if (rover.isExhausted()) { return "能源耗盡"; }
        if (returned) { return "已返回"; }
        return "任務未完成";
    }

    // getStepCount：回傳日誌筆數，也就是實際讀取並執行了幾筆指令。
    public int getStepCount() { return history.size(); }
    // getStep：回傳第 index 筆紀錄（從 0 開始）。
    // 只交出單筆紀錄（MissionStep 沒有 setter，外部改不了），不交出整個 history 清單，
    // 避免外部直接新增或刪除日誌。
    public MissionStep getStep(int index) { return history.get(index); }

    // countCommands：回傳長度 4 的陣列。
    // 統計指令 0～3 各出現幾次：counts[0] 是返回次數、counts[1] 是探索次數，依此類推。
    // 無效指令（例如 -1、9）沒有對應的格子，所以不計入；但它們仍留在日誌裡。
    public int[] countCommands() {
        int[] counts = new int[4];
        for (MissionStep step : history) {
            int command = step.getCommand();
            if (command >= 0 && command < counts.length) { counts[command]++; }
        }
        return counts;
    }

    // countOk：回傳日誌中結果為「成功」的筆數。
    // 逐筆問日誌：「這一步成功了嗎？」成功就加 1。
    // 判斷是否成功的字串比較寫在 MissionStep.isSuccessful()，這裡不必再寫 "成功".equals(...)。
    public int countOk() {
        int ok = 0;
        for (MissionStep step : history) {
            if (step.isSuccessful()) { ok++; }
        }
        return ok;
    }

    // countRejected：回傳被拒絕的筆數。
    // 日誌裡的每一筆只會是三種之一：成功、返回、被拒絕。
    // 所以「拒絕筆數 = 全部筆數 − 成功筆數 − 返回筆數」。
    // 被拒絕的例子：能源不足、能源已滿、無效指令。
    public int countRejected() {
        int returns = 0;
        for (MissionStep step : history) {
            if ("返回".equals(step.getResult())) { returns++; }
        }
        return history.size() - countOk() - returns;
    }

    // lowestEnergy：找出日誌中最低的「執行後能源」。
    // 日誌是空的就回傳 -1，表示「沒有紀錄」（MissionLog 在日誌為空時直接印「無紀錄」）。
    // 否則先假設第一筆最低，再逐筆比較，遇到更低的就換成它。
    public int lowestEnergy() {
        if (history.isEmpty()) { return -1; }
        int lowest = history.get(0).getEnergy();
        for (MissionStep step : history) {
            if (step.getEnergy() < lowest) { lowest = step.getEnergy(); }
        }
        return lowest;
    }
}
