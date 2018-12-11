package src.notes.designMode.adapterPattern;

/**
 * 具体目标类，只提供普通功能
 * @author wguo
 * @date 2018/12/11 13:56
 */
public class ConcreteTarget implements  Target{
    public void request() {
        System.out.println("普通类 具有 普通功能...");
    }
}
