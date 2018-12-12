package src.notes.designPattern.bridge;

/**
 * 模式定义：即将抽象部分与它的实现部分分离开来，使他们都可以独立变化。
 * 桥接模式将继承关系转化成关联关系，它降低了类与类之间的耦合度，减少了系统中类的数量，也减少了代码量。
 * @author wguo
 * @date 2018/12/12 9:58
 */
public class BridgeClient {
    public static void main(String[] args) {

        Abstraction a = new AbstractionA("A");
        a.setImplementor(new ConcreteImplemtorA());
        a.operation();
        a.setImplementor(new ConcreteImplemtorB());
        a.operation();

        Abstraction b = new AbstractionB("B");
        b.setImplementor(new ConcreteImplemtorA());
        b.operation();
        b.setImplementor(new ConcreteImplemtorB());
        b.operation();

        // 这样通过使用“组合/聚合复用原则”
        // 如果继续有AbstractionC ... 或者ConcreteImplemtorC ...
        // 只需要扩展类即可，不需要修改现有类，符合“开放-封闭”原则
    }

}
