package src.notes.designPattern.builder;

/**
 * Director导演类
 * @author wguo
 * @date 2018/12/11 19:28
 */
public class PersonDirector {
    public Person constructPerson(PersonBuilder pb) {
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.buildPerson();
    }
}
