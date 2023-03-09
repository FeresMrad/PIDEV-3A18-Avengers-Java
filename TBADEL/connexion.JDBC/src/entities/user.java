/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author elbarhoumi
 */
public class user {
    private int id ;
    private String firstname ;
    private String lastname;  
    private String email ;
       private String password ;
     private String telephone ;
         private String adresse;

    public user(String firstname, String lastname,String email, String password, String telephone,  String adresse) {
          this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
      this.password = password;
        this.telephone = telephone;
          this.adresse = adresse;
    }


    public user(int id, String firstname, String lastname, String email, String password, String telephone, String adresse ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
       this.password = password;  
        this.telephone = telephone;
       this.adresse = adresse;

    }

    

    public user() {
       
    }

    
    



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

   public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", telephone=" + telephone + ", adresse=" + adresse + '}';
    }

   
  
    


     
}
