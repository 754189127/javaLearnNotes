package src.notes.designPattern.bridge;

/**
 * 实现化角色
 * 它是接口或者抽象类，定义角色必需的行为和属性。
 * @author wguo
 * @date 2018/12/12 9:54
 */
public abstract class Implementor {
    public abstract void operation();
}

class ConcreteImplemtorA extends Implementor {
    @Override
    public void operation() {
        System.out.println("ConcreteImplemtorA的方法执行");

    }
}

class ConcreteImplemtorB extends Implementor {
    @Override
    public void operation() {
        System.out.println("ConcreteImplemtorB的方法执行");

    }
}