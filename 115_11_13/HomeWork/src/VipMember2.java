
public class VipMember2  {
    // name：會員名字。
    private String name;
    // points：目前累積的點數；private，只能透過方法依規則增加。
    private int points;
    
    // bonusCount：已領取加贈點數的次數；是 VIP 自己才有的欄位。
    private int bonusCount;

    // 建構子：第一行 super(name) 先執行父類別 Member 的建構子，再設定 VIP 自己的欄位。
    public VipMember(String name) {
        this.name = name;
        bonusCount = 0;
        System.out.println("VipMember 建構子: " + name);
    }

     // getName：回傳會員名字。
    public String getName() { return name; }
    // getPoints：回傳目前點數。
    public int getPoints() { return points; }

    // buy：購物 price 元，每 10 元得 1 點（整數除法，不足 10 元的部分不計）。
    public void buy(int price) {
        if (price > 0) { addPoints(price / 10); }

    }

    // addPoints：增加 amount 點；只接受正數。宣告為 protected，讓子類別也能使用。
    // 本例在同一套件，其他類別也能呼叫；保留它給子類別擴充的意圖。
    protected void addPoints(int amount) {
        if (amount > 0) { points += amount; }
    }


    // getBonusCount：回傳已領取加贈的次數。
    public int getBonusCount() { return bonusCount; }

    // claimBonus：領取加贈 20 點，並把領取次數加 1；透過父類別的 addPoints 加點數。
    public void claimBonus() {
        addPoints(20);
        bonusCount++;
    }
}
