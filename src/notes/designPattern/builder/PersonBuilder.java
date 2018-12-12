package src.notes.designPattern.builder;

/**
 * Builder抽象建造者
 * @author wguo
 * @date 2018/12/11 19:25
 */
public interface PersonBuilder {
    void buildHead();
    void buildBody();
    void buildFoot();
    Person buildPerson();
}
