public class Toy extends Toys{

    /**
     *The Toy class has a constructor that takes four parameters: an integer id, a string toyName,
     * an integer countOfToy, and an integer weigthChance.
     * The constructor calls the constructor of the superclass (Toys)
     * using the super() keyword to pass in the same four parameters.
     */

    public Toy(int id, String toyName, int countOfToy, int weigthChance) {
        super(id, toyName, countOfToy, weigthChance);
    }
}
