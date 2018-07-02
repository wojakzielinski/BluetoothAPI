package scrapper;

public class Person {
    private String firstName;
    private String secondName;
    private String lastName;
    private String maidenName;
    private Person father;
    private Person mother;
    private int bornYear;
    private boolean searchedInWebpage = false;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public String getMaidenName() {
        return maidenName;
    }

    public void setMaidenName(String maidenName) {
        this.maidenName = maidenName;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public boolean isSearchedInWebpage() {
        return searchedInWebpage;
    }

    public void setSearchedInWebpage(boolean searchedInWebpage) {
        this.searchedInWebpage = searchedInWebpage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", maidenName='" + maidenName + '\'' +
                ", father=" + (father != null? father.toString(): "null") +
                ", mother=" + (mother != null? mother.toString(): "null")  +
                ", bornYear=" + bornYear +
                ", searchedInWebpage=" + searchedInWebpage +
                '}';
    }
}
