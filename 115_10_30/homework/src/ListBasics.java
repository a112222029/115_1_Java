import java.util.ArrayList;

public class ListBasics {
    public static void main(String[] args) {
        // 指令編號：0＝返回、1＝探索、2＝掃描、3＝充電；本例只操作清單，不執行動作。
        ArrayList<Integer> commands = new ArrayList<>();
        commands.add(1); // 加入指令 1（探索）；int 自動裝箱成 Integer，不需自行 new Integer。
        commands.add(1); // 再加入一筆指令 1（探索），示範清單允許重複。
        commands.add(2); // 加入指令 2（掃描）。
        int first = commands.get(0); // Integer 自動拆箱成 int。
        System.out.println("commands=" + commands + ", size=" + commands.size());
        System.out.println("first=" + first);
        commands.set(1, 3); // 把索引 1 的指令改成 3（充電）；改內容，筆數不變。
        System.out.println("after set=" + commands);
        commands.remove(0); // 這個 0 是索引，刪除第一筆探索指令；不是指令 0（返回）。
        System.out.println("after remove index=" + commands);
        commands.remove(Integer.valueOf(3)); // 刪除第一筆指令 3（充電）；包成 Integer 才是按值刪除。
        System.out.println("after remove value=" + commands);
        System.out.println("empty=" + commands.isEmpty());
        // ArrayList<int> wrong; // 泛型型態參數須為參考型態，請在副本試編譯。
    }
}
