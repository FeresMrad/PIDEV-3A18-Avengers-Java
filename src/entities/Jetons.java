/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author feres
 */
public class Jetons {
    private int user_id;
    private String email;
    private int count;
    
    public Jetons(){
    }
    
    public Jetons(int user_id, int count, String email){
        this.user_id = user_id;
        this.email = email;
        this.count = count;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCount() {
        return count;
    }

    public String getEmail() {
        return email;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
    return "Jetons [user_id=" + user_id + ", count=" + count + "]";
}

}
