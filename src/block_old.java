import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class block_old {
    public Image img;
    //Положение подарка на игровом поле, в пикселях,
    // х - отступ слева, у - отступ сверху
    public int x, y;
    // Переменная логического типа, показывающая
    // активность подарка(естъ он игровом поле или нет):
    public Boolean act;
    // Таймер, отвечающий за движение подарка вниз
    Timer timerUpdate;
    public int speed = 20;

    // таймер, задаем, чтобы он срабатывал через каждые 100 мс. и вызывал движение подарка вниз
    public block_old(Image img)
    {
        timerUpdate = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) { vniz(); }
        });
        this.img = img;
        act = false;
    }


    public void start() {                   // метод, который генерирует положение подарка

        timerUpdate.start();
        y = 0;                              // по координате y, то есть вверху
        x = (int) (Math.random() * 900);    // по координате x, случайно от 0 до 700 пикселей
        act = true;
    }

    public void vniz() {                    // метод, который передвигает подарок вниз
        if (act == true) {
            y += 6;
        }
        if ((y + img.getHeight(null)) >= 1000) //если подарок достиг нижней границы
        {
            timerUpdate.stop();
        }
    }

    public void draw(Graphics gr) {
        if (act == true) {
            gr.drawImage(img, x, y, null);
        }
    }
}
