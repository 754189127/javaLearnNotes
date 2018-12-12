package src.notes.designPattern.bridge.example;

/**
 * 长方形
 * @author wguo
 * @date 2018/12/12 10:35
 */
public class Rectangle extends Shape{
    public void draw() {
        color.bepaint();
        System.out.println("长方形");
    }
}
