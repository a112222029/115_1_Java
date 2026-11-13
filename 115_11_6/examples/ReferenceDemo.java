// 類別：ReferenceDemo（第 5 週範例）
// 意義：示範物件變數存的是「參考」。
// 作用：比較別名（兩個變數指向同一顆電池）、把物件傳進方法修改，以及在方法內改指向新物件三種情況。
public class ReferenceDemo {
    // main：程式進入點。依序執行三種參考實驗，每一步印出能源供預測核對。
    public static void main(String[] args) {
        RoverBattery first = new RoverBattery(20);
        RoverBattery alias = first;
        RoverBattery other = new RoverBattery(20);
        alias.explore();
        System.out.println("建立別名後=" + first.getEnergy() + "/" + other.getEnergy());
        System.out.println("同一個參考=" + (first == alias));
        spend(first);
        System.out.println("扣除能源後=" + first.getEnergy());
        replaceLocal(first);
        System.out.println("重新指定參考後=" + first.getEnergy());
    }

    // spend：讓傳入的電池探索一次。用來觀察方法能否改變呼叫端那顆電池的狀態。
    static void spend(RoverBattery battery) {
        battery.explore();
    }

    // replaceLocal：在方法內讓參數改指向一顆新電池。用來觀察呼叫端的變數會不會跟著改變。
    static void replaceLocal(RoverBattery battery) {
        battery = new RoverBattery(1);
        System.out.println("方法內新物件的能源=" + battery.getEnergy());
    }
}
