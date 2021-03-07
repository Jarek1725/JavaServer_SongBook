package com.example.server.Entities;

public class Author {
    String authorId;
    String fName;
    String lName;
    String pseudonym;
    String profilePhoto;


    public Author(String authorId, String fName, String lName, String pseudonym, String profilePhoto) {
        this.authorId = authorId;
        this.fName = fName;
        this.lName = lName;
        this.pseudonym = pseudonym;
        this.profilePhoto = profilePhoto;
    }


    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
}
