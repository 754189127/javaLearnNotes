package src.notes.designPattern.bridge.example;

/**
 * 抽象出来形状基类
 * @author wguo
 * @date 2018/12/12 10:31
 */
public abstract class Shape {
    public  Color color;

    //设置颜色
    public void setColor(Color color){
        this.color = color;
    }

    //绘图
    public abstract void draw();
}
