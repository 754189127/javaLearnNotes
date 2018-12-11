package src.notes.designMode.adapterPattern;

/**
 * 适配器类，继承了被适配类，同时实现标准接口
 * @author wguo
 * @date 2018/12/11 13:58
 */
public class Adapter extends Adaptee implements Target{
    public void request() {
        super.specificRequest();
    }
}
