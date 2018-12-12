package src.notes.designPattern.builder;

/**
 * 建造者模式：将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 *
 * 实用范围
 * 1、当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时。
 * 2、当构造过程必须允许被构造的对象有不同表示时。
 *
 *
 * @author wguo
 * @date 2018/12/11 19:32
 */
public class Client {
    public static void main(String[] args) {
        PersonDirector pd = new PersonDirector();
        Person womanPerson = pd.constructPerson(new ManBuilder());
        Person manPerson = pd.constructPerson(new WomanBuilder());
    }
}
