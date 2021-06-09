package com.wechange.easyschool.esmodel.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wechange.easyschool.esmodel.entity.AbstractEntity;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Document(collection="user")
public class User extends AbstractEntity {
    @Id
    private String id;
    @NotBlank
    private String username;
    private String pseudo;
    @NotBlank
    private String firstName;
    private String lastName;
    @NotBlank
    @Email
    private String email;

    private String password;

    private String refreshToken;

    private boolean activated;


    private String langKey;

    @Size(max = 20)
 //   @Column(name = "activation_key")
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
  //  @Column(name = "reset_key")
    @JsonIgnore
    private String resetKey;

  //  @Column(name = "reset_date")
    private Instant resetDate = null;

    private String imageUrl;

 //   @ElementCollection(fetch = FetchType.EAGER)
 //   @Column(name="roles",nullable = false)
    private List<EnumAuthority> roles = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public List<EnumAuthority> getRoles() {
        return roles;
    }

    public void setRoles(List<EnumAuthority> roles) {
        this.roles = roles;
    }

    public void addRole(EnumAuthority role){
        if(roles==null)
            roles = new ArrayList<>();
        roles.add(role);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
