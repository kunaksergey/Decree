package ua.shield.jsf.model;

import org.primefaces.model.UploadedFile;
import ua.shield.entity.User;
import ua.shield.jsf.DateCombine;

import java.time.LocalDate;

/**
 * Created by sa on 12.06.17.
 */
public class UserModel {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private DateCombine birthDate=new DateCombine();
    private UploadedFile file;
    private String passwordConfime;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateCombine getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(DateCombine birthDate) {
        this.birthDate = birthDate;
    }

    public String getPasswordConfime() {
        return passwordConfime;
    }

    public void setPasswordConfime(String passwordConfime) {
        this.passwordConfime = passwordConfime;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
        
    }

    public static UserModel UserModelFromUser(User user){
        UserModel userModel=new UserModel();
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setLogin(user.getLogin());
            userModel.setEmail(user.getEmail());
            DateCombine dateCombine=new DateCombine();
            dateCombine.setDay(user.getBirthDate().getDayOfMonth());
            dateCombine.setMonth(user.getBirthDate().getMonthValue());
            dateCombine.setYear(user.getBirthDate().getYear());
            userModel.setBirthDate(dateCombine);
        return userModel;
    }

    public static User UserFromUserModel(UserModel userModel){
        User user=new User();
        user.setFirstName(userModel.getFirstName());
        user.setLogin(userModel.getLogin());
        user.setLastName(userModel.getLastName());
        user.setPassword(userModel.getPassword());
        user.setEmail(userModel.getEmail());
        user.setBirthDate(userModel.getBirthDate().getLocaleDateOf());
        return user;
    }

    public void reset() {
        firstName=null;
        lastName=null;
        login=null;
        password=null;
        passwordConfime=null;
        birthDate=null;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", passwordConfime='" + passwordConfime + '\'' +
                '}';
    }


}
