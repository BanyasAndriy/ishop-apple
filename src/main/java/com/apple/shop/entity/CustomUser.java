package com.apple.shop.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class CustomUser {


    @Id
    @GeneratedValue
    private Long idUser;
    private String firstName;
    private String name;
    private String surName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String uuid;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customUser")
    List<Order> orders ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBasket")
    private Basket basket;

   /* @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER,mappedBy = "user")
    List<Review> reviews ;z

*/

    public CustomUser() {
    }


    public CustomUser(String firstName, String name, String surName, String email, String password, String phone) {
        this.firstName = firstName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


    public CustomUser(String email, String password, Role role) {

        this.email = email;
        this.password = password;
        this.role=role;
    }


    public CustomUser(String firstName, String name, String surName, String email, String password, String phone,String uuid) {
        this.firstName = firstName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.uuid=uuid;
    }


    public CustomUser( String name, String password,Role role, String email, String phone) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role=role;
    }

    public CustomUser(String firstName, String name, String surName, String email, String password, String phone,Role role) {
        this.firstName = firstName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role=role;
    }

    public CustomUser(String firstName, String name, String surName, String email, String password, Role role) {


        this.firstName=firstName;
        this.name=name;
        this.surName=surName;
        this.email=email;
        this.password=password;
        this.role=role;

    }

    public CustomUser(String login, String passHash, Role role, String email, String phone, Basket basket) {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Role getRole() {
        return role;
    }


    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
    return this.email;
    }
}
