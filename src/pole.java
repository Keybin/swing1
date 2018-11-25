// импортируем библеотеки
import javax.imageio.*;
import javax.swing.*;       // для визуального интерфейса
import java.awt.*;
import java.awt.event.*;    // для таймера
import java.io.*;           // ввод\вывод

public class pole extends JPanel
{
    public Image arcanoid;
    public Image fon;
    public int x=400;
    private int slogn;
    private block[] gameBlock;
    public Image end_game;
    public Timer timerUpdate, timerDraw;
    private String absolutePath;



    // подгружаем картинки
    public pole(int slogn)
    {
        this.slogn = slogn;
        try	{   //  <---- для обработки ошибок: если в этом блоке ошибка, то выполняется блок catch
            arcanoid = ImageIO.read(new File("src//arcanoid.png"));   // создается доступ к файлу с картинкой
        }
        catch(IOException ex){   //  <---- если ошибка вылезла, выводится сообщение в консоль
            System.err.println("Не нашел путь к файлу: arcanoid.png");
        }

        try {   //  <---- для обработки ошибок: если в этом блоке ошибка, то выполняется блок catch
            fon = ImageIO.read(new File("src//fon.png"));   // создается доступ к файлу с картинкой
        }
        catch(IOException ex){   //  <---- если ошибка вылезла, выводится сообщение в консоль
            System.err.println("Не нашел путь к файлу: fon.png");
        }
        try
        {
            end_game = ImageIO.read(new File("src//end_game.png"));
        }
        catch (IOException ex){System.err.println("Не нашел путь к файлу: end_game.png");}


        gameBlock = new block[7];
        for (int i = 0; i<7; i++){
            try	{
                gameBlock[i] = new block(ImageIO.read(new File("src//block"+i+".png")));
            }
            catch(IOException ex){System.err.println("Не нашел путь к файлу: block"+i+".png");}

        }




        timerUpdate = new Timer(3000,new ActionListener(){    // создаем таймер с шагом в 3000 мс.
            public void actionPerformed(ActionEvent e){
                updateStart();
            }});
        timerUpdate.start();

        timerDraw = new Timer(50, new ActionListener(){       // создаем таймер с шагом в 50 мс.
            public void actionPerformed(ActionEvent e){
                repaint();           // перерисовывает форму через каждые 50 мс для корректного отображения
            }});
        timerDraw.start();
    }

    // Здесь задается положение картинок
    public void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);           // обращаемся к родительскому классу этого компонента
        gr.drawImage (fon,0,0,null);        // задаем координаты (местоположение)
        gr.drawImage(arcanoid,x,900,null);    // задаем координаты (местоположение)
        for(int i = 0;i<7;i++)
        {
           gameBlock[i].draw(gr);
            if (gameBlock[i].act == true)
            {
                if((gameBlock[i].y+gameBlock[i].img.getHeight(null))>=900)
                {
                    if(Math.abs(gameBlock[i].x-x)>300)  // abs - возвращает модуль числа (только положительное)
                    {
                        gr.drawImage(end_game, 140, 115, null);
                        timerDraw.stop();       // останавливает таймер
                        timerUpdate.stop();     // останавливает таймер
                        break;
                    }
                    else gameBlock[i].act = false;
                }
            }
        }
    }
    private void updateStart()
    {
        int kol = 0;
        for (int i=0; i<7; i++)
        {
            if(gameBlock[i].act==false)
            {
                if(kol<slogn)
                {
                    gameBlock[i].start();
                    break;
                }
            }
            else kol++;
        }
    }

    public String getAbsolutePath() {
        return absolutePath;
    }   // <---- убрать заглушку, когда разберусь с относительными путями
}       // keybin11@gmail.com
