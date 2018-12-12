package src.notes.designPattern.adapter;

/**
 * 需要适配的类
 * 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
 * @author wguo
 * @date 2018/12/11 13:53
 */
public class Adaptee {
    public void specificRequest() {
        System.out.println("这是一个特殊的请求，需要被适配...");
    }
}
