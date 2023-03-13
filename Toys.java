abstract class Toys {

    /**
     * This code contains two classes, Toy and Toys. The Toy class is a subclass of Toys
     * and has a constructor that calls the superclass constructor with four parameters:
     * id, toyName, countOfToy, and weigthChance.
     *
     * The Toys class is an abstract class that defines four instance variables: id, toyName,
     * countOfToy, and weigthChance, and a constructor that initializes these variables
     * with the provided values. The class also provides getter and
     * setter methods for each of these variables, as well as a getAllInfo() method that returns
     * a formatted string containing all of the instance variable values.
     */

    int id;
    String toyName;
    int countOfToy;
    int weigthChance;

    public Toys(int id, String toyName, int countOfToy, int weigthChance) {
        this.id = id;
        this.toyName = toyName;
        this.countOfToy = countOfToy;
        this.weigthChance = weigthChance;
    }

    public String getToyName() {
        return toyName;
    }

    public int getCountOfToy() {
        return countOfToy;
    }

    public int getId() {
        return id;
    }

    public int getWeigthChance() {
        return weigthChance;
    }

    public String getAllInfo() {
        return id+";"+toyName+";"+countOfToy+";"+ weigthChance;
    }

    public void setWeigthChance(int weigthChance) {
        this.weigthChance = weigthChance;
    }

    public void setCountOfToy(int countOfToy) {
        this.countOfToy = countOfToy;
    }
}
