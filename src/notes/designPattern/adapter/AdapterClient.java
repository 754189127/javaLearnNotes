package src.notes.designPattern.adapter;

/**
 * 适配器客户端
 * 将一个类的接口转换成客户希望的另外一个接口。
 *
 * Adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作。
 * 测试类
 * @author wguo
 * @date 2018/12/11 13:59
 */
public class AdapterClient {
    public static void main(String[] args) {
        Target target;
        // 使用特殊功能类，即适配类
         target = new Adapter();
        target.request();
    }
}
