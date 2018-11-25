// импортируем библеотеки
import javax.swing.*;    // для визуального интерфейса


public class Main {
    public static void main(String[] args)
    {
        // выводится запрос сложности игры
        String rez =
                JOptionPane.showInputDialog(null, "Введите сложность игры от 1 до 7:","Сложность игры",1);
        int slogn = rez.charAt(0)-'0';

        // допускается сложность от 1 до 7
        if ((slogn>=1) && (slogn <=7))
        {
            // создаем объект окна
            okno window = new okno(slogn);
        }
    }
}
