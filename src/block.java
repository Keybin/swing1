import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class block {
    public Image img;
    //Положение блока на игровом поле, в пикселях,
    // х - отступ слева, у - отступ сверху
    public int x, y;
    // Переменная логического типа, показывающая
    // активность блока(естъ он игровом поле или нет):
    public Boolean act;
    // Таймер, отвечающий за движение блока вниз
    Timer timerUpdate;
    public String napravX = "вправо";
    public int time = 100; // таймер


    // таймер, задаем, чтобы он срабатывал через каждые 100 мс. и вызывал движение блока вниз
    public block(Image img)
    {
        timerUpdate = new Timer(time, new ActionListener() {
            public void actionPerformed(ActionEvent e) { vniz(); vbok(); }
        }); //move
        this.img = img;
        act = false;
    }


    public void start() {                   // метод, который генерирует положение блока

        timerUpdate.start();
        y = 0;                              // по координате y, то есть вверху
        x = (int) (Math.random() * 900);    // по координате x, случайно от 0 до 700 пикселей
        act = true;
    }

    public void vniz() {                    // метод, который передвигает подарок вниз
        if (act == true) {
            y += 6;
        }
        if ((y + img.getHeight(null)) >= 1000) //если блок достиг нижней границы
        {
            timerUpdate.stop();
        }


    }

    public  void vbok()
    {
        if ((x + img.getWidth(null))  >= 1000) napravX="влево";
        if ((x + img.getWidth(null))  <= 0) napravX="вправо";
            switch (napravX)
            {
                case "вправо": x+=5;break;
                case "влево": x-=5;break;
            }

    }

    public void draw(Graphics gr) {
        if (act == true) {
            gr.drawImage(img, x, y, null);
        }
    }
}
