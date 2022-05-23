package builder;

/**
 * @author Amimul Ehsan
 * @project design_pattern
 */

//Fluent builder
//To solve the problem introduced in chaining fluent builder we introduce java recursive generics
public class Person {

    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

}

class PersonBuilder<SELF extends PersonBuilder<SELF>> {

    protected Person person = new Person();

    public SELF withName( String name ) {
        person.name = name;
        return self();
    }

    public Person build() {
        return person;
    }

    //Want to override the self
    protected SELF self() {
        return (SELF) this;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder worksAt( String position ) {
        person.position = position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

class Demo {
    public static void main( String[] args ) {

        EmployeeBuilder employeeBuilder = new EmployeeBuilder();

        //TODO: Read recursive generics
        //Let's chain the person builder and employee builder
        Person dimitri = employeeBuilder
                .withName("Dmitri")
                .worksAt("ABCD")
                .build();

        System.out.println(dimitri);
    }
}