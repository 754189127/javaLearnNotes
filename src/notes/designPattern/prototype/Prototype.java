package src.notes.designPattern.prototype;

/**
 * 原型模式
 * 定义：用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象。
 * 创建类模式
 * @author wguo
 * @date 2018/12/12 16:02
 */
public class Prototype implements Cloneable{
    public Prototype clone(){
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return prototype;
    }
}



class  ConcretePrototype extends Prototype{
    public void show(){
        System.out.println("原型模式实现类");
    }
}


class Client{
    public static void main(String[] args) {
        ConcretePrototype cp = new ConcretePrototype();
        for (int i = 0; i < 10; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
            clonecp.show();
        }
    }
}