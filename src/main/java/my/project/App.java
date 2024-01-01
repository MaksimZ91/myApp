package my.project;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Serialize serialize = new Serialize();
        Person person = new Person("Mike", "Kotov", 29);
        String json = serialize.serialize(person);
        System.out.println(json);
        System.out.println(serialize.deSerialize(json, Person.class));


    }


}
