/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;

/**
 *
 * @author aaren
 */
public class Member {
    
    private String name;
    private String surname;
    private String telephone;
    private String login;
    private String password;
    private String creditCard;
    private String svc;
    private Image image;
   
    
    
    
   public Member(String n, String sur, String t, String l, String p, String c, String s, Image i) {
       
        name=n;
        surname=sur;
        telephone=t;
        login=l;
        password=p;
        creditCard = c;
        svc=s;
        image = i;  
   
   }
   
   
   public void setName(String n){name=n;}
   public String getName(){return name;}
   
   public void setSurname(String sur) {surname=sur;}
   public String getSurname() {return surname;}
   
   public void setTelephone(String tel) {telephone=tel;}
   public String getTelephone() {return telephone;}
   
   public void setLogin(String l) { login =l;}
   public String getLogin() { return login;}
   
   public void setPass(String p) {password = p;}
   public String getPass() {return password;}

   public void setCredit(String c) {creditCard=c;}
   public String getCredit() {return creditCard; }
   
   public void setSVC(String s) {svc = s;}
   public String getSVC() {return svc;}
   
   public void setImage(Image i) {image=i;}
   public Image getImage(){return image;}
   
    
}
