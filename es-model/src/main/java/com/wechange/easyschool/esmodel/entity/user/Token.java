package com.wechange.easyschool.esmodel.entity.user;

import com.wechange.easyschool.esmodel.entity.AbstractEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import org.springframework.data.annotation.Id;

@Document(collection="token")
public class Token extends AbstractEntity {
    @Id
    private String id;

    private String token;

    private EnumTokenType tokenType;

    private EnumTokenStatus tokenStatus;

    private Long refreshCount; // Spécific au token de refraichissement

    private Instant expiryDate;

 //  @OneToOne
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EnumTokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(EnumTokenType tokenType) {
        this.tokenType = tokenType;
    }

    public EnumTokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public void setTokenStatus(EnumTokenStatus tokenStatus) {
        this.tokenStatus = tokenStatus;
    }

    public Long getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(Long refreshCount) {
        this.refreshCount = refreshCount;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
