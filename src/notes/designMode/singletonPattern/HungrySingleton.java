package src.notes.designMode.singletonPattern;

/**
 * 饿汉单例模式，在类初始化时，已经自行实例化
 * 饿汉式是典型的空间换时间，当类装载的时候就会创建类的实例，不管你用不用，先创建出来，
 * 然后每次调用的时候，就不需要再判断，节省了运行时间。
 * @author wguo
 * @date 2018/12/11 17:12
 */
public class HungrySingleton {
    private HungrySingleton(){
    }
    private static final HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return instance;
    }
}
