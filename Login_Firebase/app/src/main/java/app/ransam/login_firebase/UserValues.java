package app.ransam.login_firebase;

public class UserValues {

    public String name;
    public String age;
    public String email;


    // Press Alt + O  or Alt + insert

    public  UserValues(){

    }


    public UserValues(String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }


    // Press Alt + O  or Alt + insert


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
