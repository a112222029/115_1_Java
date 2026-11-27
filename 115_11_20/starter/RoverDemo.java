// 類別：RoverDemo（第 6 週示範）
// 意義：單獨觀察 Rover 物件的小程式，不經過 Mission，也不產生任務日誌。
// 作用：建立三台不同初始狀態的探測車各下一個指令，確認「狀態搬到物件裡」的意思；
//       只需要和 Rover 一起編譯。
public class RoverDemo {
    // main：程式進入點。對正常、能源耗盡、已達標三台車各執行一個指令並印出結果。
    public static void main(String[] args) {
        Rover rover = new Rover(20, 0);
        System.out.println(rover.execute(1) + "/" + rover.getEnergy()
                + "/" + rover.getSamples() + "/" + rover.getActionCount());
        Rover empty = new Rover(0, 0);
        System.out.println(empty.execute(3) + "/" + empty.getEnergy());
        Rover done = new Rover(20, 5);
        System.out.println(done.execute(1) + "/" + done.getEnergy());
    }
}
