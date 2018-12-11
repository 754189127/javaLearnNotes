package src.notes.designMode.singletonPattern;

/**
 * 枚举单例（最佳方法）
 * 使用枚举来实现单实例控制会更加简洁，而且无偿地提供了序列化机制，并由JVM从根本上提供保障，
 * 绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 *
 * 自由序列化，线程安全，保证单例
 *
 * @author wguo
 * @date 2018/12/11 15:38
 */
public class EnumSingleton {
    private EnumSingleton(){}
    public EnumSingleton getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton{
        INSTANCE;
        private EnumSingleton singleton;

        //JVM会保证此方法绝对只调用一次
        private Singleton(){
            singleton = new EnumSingleton();
        }
        public EnumSingleton getInstance(){
            return singleton;
        }
    }
}
