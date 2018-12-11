package src.notes.designMode.adapterPattern;

/**
 * 将一个类的接口转换成客户希望的另外一个接口。
 * Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作。
 * 测试类
 * @author wguo
 * @date 2018/12/11 13:59
 */
public class Client {
    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类
        Target adapter = new Adapter();
        adapter.request();
    }
}
