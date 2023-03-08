import javax.swing.JOptionPane;

/**
 * Класс меню программы
 */
public class Menu {

    public void run(){
        Model m=new Model();
        int choice=Integer.parseInt(JOptionPane.showInputDialog(null,
                "Выберите пункт меню:\n1.Разыграть игрушку\n2.Добавить игрушку\n3.Изменить вес игрушки\n4.Получить игрушку",
                "Окно команд",
                JOptionPane.QUESTION_MESSAGE));

        if (choice==1){
            m.playLottery();

        } else if (choice==2) {
            m.addToy();

        } else if (choice==3) {
            m.changeWightChance();

        } else if (choice==4) {
            m.getToy();

        }else {
            JOptionPane.showMessageDialog(null,
                    "Что-то пошло не так",
                    "Произошла ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
