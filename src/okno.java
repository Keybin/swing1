import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class okno extends JFrame
{


    private pole gameP;
    public int spdArc = 50; // скорость перемещения арканойда

    // myKey - класс-слушатель или перехватчик нажатий на клавиатуре, он является интерфейсом KeyListener
    private class myKey implements KeyListener
    {
        // метод перехватывающий именно нажатие
        public void keyPressed(KeyEvent e)
        {
            // получаю код нажатой клавиши
            int key_=e.getKeyCode();
            // если нажата клавиша Esc - закрыть программу
            if(key_ == 27)System.exit(0);

            else if(key_ == 37) // иначе, если нажата клавиша влево
            {                   // то
                if(gameP.x-spdArc>-spdArc)gameP.x -=spdArc;  // перемещение арканойда влево
                else gameP.x = gameP.fon.getWidth(null)-gameP.arcanoid.getWidth(null)-spdArc; // если достиг левой границы, переместить к правой
            }
            else if(key_ == 39) //иначе, если нажата клавиша вправо
            {
                if(gameP.x + spdArc < gameP.fon.getWidth(null) - gameP.arcanoid.getWidth(null))gameP.x +=spdArc;  // перемещение арканойда вправо
                else gameP.x =0; // если достиг правой границы, переместить к левой
            }

        }
        public void keyReleased(KeyEvent e) {}  // это заглушки, они нужны, чтобы перегрузить KeyListener
        public void keyTyped(KeyEvent e) {}     // читай выше
    }

    // это конструктор класса, в нем мы задаем параметры окна, когда оно инициализируется
    public okno(int slogn)
    {
        addKeyListener(new myKey());          // устанавливаем слушатель нажатий
        setFocusable(true);                   // окно может получать фокус (ну, жмакануть по нему сможешь)
        setBounds(0,0,1000,1000);               // положение и размер окна
        setTitle("Arcanoid");   // заголовок окна
        gameP = new pole(slogn);              // вызываем поле в котором игра будет
        Container con = getContentPane();     // контейнер, который лежит в поле
        con.add(gameP);                       // добавляем контейнер на поле
        setVisible(true);                     // делаем окно видимым
    }
}
