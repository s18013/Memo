public abstract class AbsChocolate {
    public void make() {
        System.out.println(type());
        System.out.println(price());
    }
    // 必ず実装する必要あり
    public abstract String type();
    public int price(){
        return 100;
    }
}

public class AbsChocolate extend AbsChocolate{

    public void make() {
        // 以下は親クラスのmake()が実行され出力されるtypeについては、
        // 子クラスの実装された結果を返す(この場合"White")
        super.make();
        // 以下については子クラスのpriceではなく親クラスのpriceの結果を返す(100)
        System.out.println(super.price);
    }

    @Override
    public String type(){
        return "White"
    }

    @Override
    public int price(){
        return 111;
    }
}