// 類別：CreateStepDemo（第 5 週範例）
// 意義：示範用建構子建立物件，以及兩個物件彼此獨立。
// 作用：建立兩張 StepCard，用 getter 讀出各自的資料；註解中的兩行是 private 存取與無參數建構子的錯誤練習。
public class CreateStepDemo {
    // main：程式進入點。建立兩張 StepCard，印出內容，並比較它們是不是同一個物件。
    public static void main(String[] args) {
        StepCard first = new StepCard(1, 14);
        StepCard second = new StepCard(2, 12);
        System.out.println("第一次=" + first.getCommand() + "/" + first.getEnergy());
        System.out.println("第二次=" + second.getCommand() + "/" + second.getEnergy());
        System.out.println("同一個物件=" + (first == second));
        // first.energy = 99; // 私有存取錯誤練習只在副本取消註解。
        // StepCard empty = new StepCard(); // 已宣告有參數建構子，不會自動補無參數版。
    }
}
