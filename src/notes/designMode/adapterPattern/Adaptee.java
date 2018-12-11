package src.notes.designMode.adapterPattern;

/**
 * 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
 * @author wguo
 * @date 2018/12/11 13:53
 */
public class Adaptee {
    public void specificRequest() {
        System.out.println("被适配类具有 特殊功能...");
    }
}
