import javax.swing.JOptionPane;

/**
 * Toy draw program menu:
 * We list menu items, describe the structure of function calls.
 */
public class Menu {

    public void run(){
        Model m=new Model();
        int choice=Integer.parseInt(JOptionPane.showInputDialog(null,
                """
                        Select a menu item:
                        1.Play a toy
                        2.Add a toy
                        3.Change the weight of the toy
                        4.Get a toy""",
                "Command window",
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
                    "Something went wrong",
                    "An error has occurred",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
