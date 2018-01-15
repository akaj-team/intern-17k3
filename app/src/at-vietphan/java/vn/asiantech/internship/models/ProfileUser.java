package vn.asiantech.internship.models;

/**
 * Created by vietphan on 15/01/2017.
 * Class ProfileUser
 */
public class ProfileUser extends BaseObservable{
    private String name;
    private String email;
    private String birthDate;
    private int genDer;
    private String phone;

    public ProfileUser(String name, String email, String birthDate, int genDer, String phone) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.genDer = genDer;
        this.phone = phone;
    }

    public ProfileUser() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        name.notifyAll();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getGenDer() {
        return genDer;
    }

    public void setGenDer(int genDer) {
        this.genDer = genDer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
