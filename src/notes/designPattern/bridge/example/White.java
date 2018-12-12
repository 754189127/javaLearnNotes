package src.notes.designPattern.bridge.example;

/**
 * @author wguo
 * @date 2018/12/12 10:38
 */
public class White implements Color {
    /**
     * 着色
     */
    public void bepaint() {
        System.out.print("白色的");
    }
}
