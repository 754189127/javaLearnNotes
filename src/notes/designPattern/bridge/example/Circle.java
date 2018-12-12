package src.notes.designPattern.bridge.example;

/**
 * 圆形
 * @author wguo
 * @date 2018/12/12 10:33
 */
public class Circle extends Shape{
    public void draw() {
        color.bepaint();
        System.out.println("圆形");
    }
}
