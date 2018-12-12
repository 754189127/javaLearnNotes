package src.notes.designPattern.adapter;

/**
 * 目标接口，或称为标准接口,客户所期待的接口
 * @author wguo
 * @date 2018/12/11 13:56
 */
public abstract class Target {
    public void request() {
        System.out.println("这是一个普通请求！");
    }
}
