package src.notes.designPattern.bridge;

/**
 * 抽象化角色
 * 它的主要职责是定义出该角色的行为，同时保存一个对实现化角色的引用，该角色一般是抽象类。
 * @author wguo
 * @date 2018/12/12 9:53
 */
public abstract class Abstraction {
    /**
     * 桥接模式的关键，使得Abstraction聚合Implementor
     */
    private Implementor implementor;

    private String name;

    public Abstraction(String name) {
        this.name = name;
    }

    public void setImplementor(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation(){
        System.out.print("Abstraction-" + this.getName() + ": ");
        implementor.operation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class AbstractionA extends Abstraction {

    public AbstractionA(String name) {
        super(name);
    }

    @Override
    public void operation() {
        super.operation();
    }

}

class AbstractionB extends Abstraction {

    public AbstractionB(String name) {
        super(name);
    }

    @Override
    public void operation() {
        super.operation();
    }

}