package com.ystu.neutralesprojekt.classes;

import java.util.ArrayList;

public class Data {
    private static Data data = null;

    public static Data getInstance() {
        if(data == null) {
            data = new Data();
        }
        return data;
    }

    private Data() {
        initData();
    }

    ArrayList<Cookie> cookies = new ArrayList<Cookie>();
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Zakaz> zakazs = new ArrayList<Zakaz>();

    //получить id покупателя по логину и паролю
    public  long getIdPersonByLogPas(String log, String pass) {
        for (User userLogPass : users){
            if ((userLogPass.getName().equals(log)) && (userLogPass.getPass().equals(pass)) ){
                return userLogPass.getId();
            }
        }
        return 0;
    }

    //проверка на правильность логина и пароля
    public  boolean getLogin(String log, String pass) {
        for (User userLogPass : users){
            if ((userLogPass.getName().equals(log)) && (userLogPass.getPass().equals(pass))){
                return false;
            }
        }
        return true;
    }

    //получить печеньки
    public ArrayList<Cookie> getCookies() {
        return cookies;
    }

    //получить печеньку по ее id
    public Cookie getCookieById(long id) {
        for (Cookie cookie : cookies){
            if (cookie.getId() == id){
                return cookie;
            }
        }
        return null;
    }

    //установить заказ
    public void setZakazs(ArrayList<Zakaz> zakazs) {
        this.zakazs = zakazs;
    }

    //получить заказ по id прользователя
    public Zakaz getZakazByPerson(long idUser) {
        for (Zakaz or : zakazs){
            if (or.getUser_id() == idUser){
                return or;
            }
        }
        return new Zakaz();
    }


    public void initData() {
        users.add(new User(1,"a","1"));
        users.add(new User(2,"b","2"));
        users.add(new User(3,"v","3"));

        cookies.add(new Cookie(1,"Черепашка","img/1.png",50));
        cookies.add(new Cookie(2,"Шахматы","img/2.png",25));
        cookies.add(new Cookie(3,"Джем","img/3.png",15));
        cookies.add(new Cookie(4,"Чокопай","img/4.png",25));
    }

}
