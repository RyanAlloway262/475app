package com.rxa392.shake;

public class Model {
    String currentEmail;
    String currentPassword;
    String developerEmail = "d";
    String developerPassword = "p";

    public int CreateAccountLogic(String email, String password){
        this.currentEmail = email;
        this.currentPassword = password;
        //if(currentEmail == enter email in database &&
        // currentPassword == enter password in database ) return 0;
        // finish this
        return 1;
    }

    public int SignInLogic(String email, String password) {
        this.currentEmail = email;
        this.currentPassword = password;
        //if (developerEmail.compareTo(currentEmail) == 0 && developerPassword.compareTo(currentPassword) == 0) {
        //    return 1;
        //}
        return 1;
    }
}
