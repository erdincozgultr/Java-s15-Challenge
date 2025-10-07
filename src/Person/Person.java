package Person;

public abstract class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String whoyouare();

    public String toString() {
        return "Person.Person [name=" + name + "]";
    }
}
