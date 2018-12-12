package src.notes.designPattern.singleton;

/**
 * 懒汉式单例方式1 双重锁检查
 * 懒汉式是典型的时间换空间,就是每次获取实例都会进行判断，看是否需要创建实例，
 * 浪费判断的时间。当然，如果一直没有人使用的话，那就不会创建实例，则节约内存空间
 * 懒汉式单例类.在第一次调用的时候实例化自己
 * @author wguo
 * @date 2018/12/11 15:01
 */
public class LazySingleton1 {
    private static LazySingleton1 instance = null;

    /**
     * 私有默认构方法
     */
    private LazySingleton1() {
    }

    /**
     * 静态工厂方法(性能存在问题)
     * @return
     */
    public static LazySingleton1 getInstance(){
        if(instance==null) {
            synchronized(LazySingleton1.class) {
                instance = new LazySingleton1();
            }
        }
        return instance;
    }


}
