package src.notes.designPattern.builder;

/**
 * 角色ConcreteBuilder
 * @author wguo
 * @date 2018/12/11 19:27
 */
public class WomanBuilder implements PersonBuilder{
    Person person;
    public WomanBuilder() {
        person = new Woman();
    }
    public void buildBody() {
        person.setBody("建造女人的身体");
    }
    public void buildFoot() {
        person.setFoot("建造女人的脚");
    }
    public void buildHead() {
        person.setHead("建造女人的头");
    }

    public Person buildPerson() {
        return person;
    }
}
