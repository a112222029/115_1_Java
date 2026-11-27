
public class MyHeavyRoverTest {
    public static void main(String[] args) {
        HeavyRover rover = new HeavyRover(9, 4);

        System.out.println("第一次鑽探：" + rover.drill());

        System.out.println("目前狀態：" +
                rover.getEnergy() + "/" +
                rover.getSamples() + "/" +
                rover.getActionCount() + "/" +
                rover.getDrillCount());

        System.out.println("再次鑽探：" + rover.drill());
    }
}
