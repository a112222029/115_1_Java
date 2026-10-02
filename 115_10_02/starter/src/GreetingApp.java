public class GreetingApp {
    public static void main(String[] args) {
        // 使用固定資料，讓兩套 IDE 與 clone 後的結果可以逐行比較。
        String first = buildGreeting("Ada");
        System.out.println(first);
        System.out.println(buildGreeting("Grace"));
        System.out.println(buildGreeting("Ada Lovelace"));
    }

    static String buildGreeting(String name) {
        // TODO：回傳 "Hello, " + 姓名 + "!"；此方法不負責輸出。
        // 暫時回傳 TODO 使起始碼能編譯；能執行不代表需求已完成。
        return "TODO";
    }
}
