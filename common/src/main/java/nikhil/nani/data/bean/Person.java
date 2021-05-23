package nikhil.nani.data.bean;

public class Person
{
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String city;

    public Person()
    {
    }

    public Person(int id, String firstName, String lastName, int age, String city)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }

        Person person = (Person) o;

        if (this.id != person.id)
        {
            return false;
        }
        if (this.age != person.age)
        {
            return false;
        }
        if (this.firstName != null ? !this.firstName.equals(person.firstName) : person.firstName != null)
        {
            return false;
        }
        if (this.lastName != null ? !this.lastName.equals(person.lastName) : person.lastName != null)
        {
            return false;
        }
        return this.city != null ? this.city.equals(person.city) : person.city == null;
    }

    @Override
    public int hashCode()
    {
        int result = this.id;
        result = 31 * result + (this.firstName != null ? this.firstName.hashCode() : 0);
        result = 31 * result + (this.lastName != null ? this.lastName.hashCode() : 0);
        result = 31 * result + this.age;
        result = 31 * result + (this.city != null ? this.city.hashCode() : 0);
        return result;
    }

    public String getRecordString()
    {
        return String.valueOf(id) + ',' + firstName + ',' + lastName + ',' + age + ',' + city;
    }
}
