package com.oliveoa.pojo;

public class CompanyInfo {
    private String username;

    private String password;

    private String fullname;

    private String telephone;

    private String fax;

    private String zipcode;

    private String address;

    private String website;

    private String email;

    private String introduction;

    public CompanyInfo(String username, String password, String fullname, String telephone, String fax, String zipcode, String address, String website, String email, String introduction) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.telephone = telephone;
        this.fax = fax;
        this.zipcode = zipcode;
        this.address = address;
        this.website = website;
        this.email = email;
        this.introduction = introduction;
    }

    public CompanyInfo() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}