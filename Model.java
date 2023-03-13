import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the beginning of the class definition for Model which implements ProgrammFunction.
 * playLottery() method is an overridden method from the ProgrammFunction interface.
 * Random is a class from java.util package which generates random numbers.
 * forIdUser is a double variable initialized with a random decimal number between 0 and 1 multiplied by 1563.
 * idUser is an integer variable casted from forIdUser.
 * str is a String variable to read lines of text from a file.
 * totalDropRate is a double variable initialized with zero.
 * path2 is a File object referring to the "PRIZE-TOY.txt" file.
 * bw is a BufferedWriter object initialized to write to the "PRIZE-TOY.txt" file.
 * path is a File object referring to the "TOYWAREHOUSE.txt" file.
 * br is a BufferedReader object initialized to read from the "TOYWAREHOUSE.txt" file.
 * priorityArray is an ArrayList of String objects to store the text read from the "TOYWAREHOUSE.txt"
 * file.
 *
 * A for loop is used to iterate through all the elements of priorityArray.
 * secondArray is a String array initialized to hold the 4 sections of text separated by ";"
 * from each element in priorityArray.
 *
 * If the fourth section of secondArray is 0, the corresponding element is removed from priorityArray.
 * The second section of secondArray is added to totalDropRate.
*/

public class Model implements ProgrammFunction{
    @Override
    public void playLottery(){
        Random random=new Random();
        double forIdUser=random.nextDouble()*1563;

        int idUser=(int)forIdUser;

        String str;
        final double[] totalDropRate = {0};
        try {
            File path2=new File("PRIZE-TOY.txt");
            BufferedWriter bw=new BufferedWriter(new FileWriter(path2,true));

            File path=new File("TOYWAREHOUSE.txt");
            BufferedReader br=new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray =new ArrayList<>();
            while ((str=br.readLine())!=null){
                priorityArray.add(str);
            }


            priorityArray.removeIf(s -> {
                String[] secondArray = s.split(";");
                if (Integer.parseInt(secondArray[3]) == 0) {
                    return true;
                } else {
                    totalDropRate[0] += Double.parseDouble(secondArray[2]);
                    return false;
                }
            });

            double randomValue=random.nextDouble()* totalDropRate[0];
            System.out.println(randomValue);
            double currentSum=0;

            for (int k=0;k<priorityArray.size();k++){
                String[] secondArray=new String[4];
                secondArray=priorityArray.get(k).split(";");
                currentSum+=Double.parseDouble(secondArray[2]);

                if (Integer.parseInt(secondArray[3])!=0){
                    if (randomValue<=currentSum){

                        priorityArray.set(k,
                                (secondArray[0]+";"
                                        +secondArray[1]+";"
                                        +(Integer.parseInt(secondArray[2])-1)+";"
                                        +secondArray[3]));

                        JOptionPane.showMessageDialog(null,
                                "You won: "+secondArray[1]+"\nyour winning code: "+idUser,
                                "Win window",
                                JOptionPane.INFORMATION_MESSAGE);

                        bw.write(secondArray[0]+";"+secondArray[1]+";"+idUser);

                        bw.newLine();

                        bw.close();

                        br.close();

                        break;


                    }
                }
            }

            BufferedWriter bwp=new BufferedWriter(new FileWriter(path));

            String replace="";

            for(int p=0;p<priorityArray.size();p++) {
                replace += priorityArray.get(p) + "\n";
            }

            System.out.println(replace);
            bwp.write(replace);
            bwp.close();

        }
        catch (IOException e){
            System.out.println(e);
        }



    }


    @Override
    public void changeWightChance() {
        int idToy = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter the Id of the toy to change the weight: ",
                "Changing the weight of the toy",
                JOptionPane.INFORMATION_MESSAGE));
        String str;
        try {
            File path = new File("TOYWAREHOUSE.txt");
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);
            }
            br.close();

            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[4];
                secondArray = priorityArray.get(j).split(";");
                if (idToy == Integer.parseInt(secondArray[0])) {
                    int weigthChance = Integer.parseInt(JOptionPane.showInputDialog(null,
                            priorityArray.get(j) + "\nEnter a new weight value in %: ",
                            "Changing the weight of the toy",
                            JOptionPane.INFORMATION_MESSAGE));
                    String res = secondArray[0] + ";" + secondArray[1] + ";" + secondArray[2] +
                            ";" + weigthChance;
                    priorityArray.set(j, res);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void addToy() {

        Toy toy=new Toy(Integer.parseInt(JOptionPane.showInputDialog(null,
                "Enter id: ",
                "Adding a toy",
                JOptionPane.QUESTION_MESSAGE)),
                JOptionPane.showInputDialog(null,
                        "Enter a name: ",
                        "Adding a toy",
                        JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Enter quantity:",
                        "Adding toys",
                        JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Enter weight in %: ",
                        "Adding a toy",
                        JOptionPane.QUESTION_MESSAGE)));
        try {
            File path=new File("TOYWAREHOUSE.txt");

            if (!path.exists()){
                path.createNewFile();
            }

            BufferedWriter bw=new BufferedWriter(new FileWriter(path,true));
            bw.write(toy.getAllInfo());
            bw.newLine();

            JOptionPane.showMessageDialog(null,
                    "Toy: \nid: "
                            +toy.getId()+"\nname: "
                            +toy.getToyName()+"\namount: "
                            +toy.getCountOfToy()+"\nweight: "
                            +toy.getWeigthChance()+"\nsuccessfully added",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
            bw.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,e,"ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void getToy(){
        int idUser = Integer.parseInt(JOptionPane.showInputDialog(null,
        "Enter Member ID: ",
        "Issuance of a toy",
        JOptionPane.INFORMATION_MESSAGE));
        String str;
        try {
            File path = new File("PRIZE-TOY.txt");
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);

            }
            br.close();

            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[3];
                secondArray = priorityArray.get(j).split(";");
                if (idUser == Integer.parseInt(secondArray[2])) {
                    JOptionPane.showMessageDialog(null,
                            "Congratulations on your win\n" + secondArray[0] + "," +
                                    "" + secondArray[1],
                            "Issuance of a toy",
                            JOptionPane.INFORMATION_MESSAGE);

                    priorityArray.remove(j);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
