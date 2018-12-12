package src.notes.designPattern.builder;

/**
 * 角色ConcreteBuilder
 * @author wguo
 * @date 2018/12/11 19:26
 */
public class ManBuilder implements PersonBuilder{
    Person person;
    public ManBuilder() {
        person = new Man();
    }
    public void buildBody() {
        person.setBody("建造男人的身体");
    }
    public void buildFoot() {
        person.setFoot("建造男人的脚");
    }
    public void buildHead() {
        person.setHead("建造男人的头");
    }

    public Person buildPerson() {
        return person;
    }
}
