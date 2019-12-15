import javax.swing.*;

/**
 * @CreateTime: 2019-11-21-14:12
 * @Author :杨阳
 * @ClassName :Msnake
 **/
public class Msnake {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);//能否手动改变大小？
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MPanel());
        frame.setVisible(true);

    }
}
