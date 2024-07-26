package fit_zone.domain;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String lastName;
    private int membership;

    public Client(){}

    public Client(int id){
        this.id = id;
    }

    public Client(String name, String lastName, int membership){
        this.name = name;
        this.lastName = lastName;
        this.membership = membership;

    }

    public Client(int id, String name, String lastName, int membership){
        this(name, lastName, membership);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", membership=" + membership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && membership == client.membership && Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, membership);
    }
}
