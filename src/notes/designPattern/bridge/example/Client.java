package src.notes.designPattern.bridge.example;

/**
 * @author wguo
 * @date 2018/12/12 10:39
 */
public class Client {
    public static void main(String[] args) {
        //白色
        Color white = new White();
        //长方形
        Shape rectangle = new Rectangle();
        rectangle.setColor(white);
        //白色的长方形
        rectangle.draw();

        //圆形
        Shape circle = new Circle();
        circle.setColor(white);
        circle.draw();
    }
}
