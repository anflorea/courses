package main.java.ro.ubb.movies.domain;

/**
 * Created by andrapop on 2017-03-06.
 */
public class Client extends BaseEntity<Integer>{
    private String firstName;
    private String lastName;
    private String phoneNr;
    private String address;


    public Client(){

    }

    public Client(int id, String firstName, String lastName, String phoneNr, String address) {
        super.setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
        this.address = address;

    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (phoneNr != null ? !phoneNr.equals(client.phoneNr) : client.phoneNr != null) return false;
        return address != null ? address.equals(client.address) : client.address == null;
    }


    @Override
    public String toString() {
        return super.toString()+" Client {" +
                "firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", phoneNr = '" + phoneNr + '\'' +
                ", address = '" + address + '\'' +
                "}";
    }
}
