package com.code_cafe.Database;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    public String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_status")
    private String emailStatus;

    @JsonProperty("password")
    private String password;

    @JsonProperty("profile_img")
    private String profileImg;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("school_college_university")
    private String schoolCollegeUniversity;

    @JsonProperty("start_year")
    private String startYear;

    @JsonProperty("end_year")
    private String endYear;

    @JsonProperty("age")
    private String age;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("registration_date")
    private String registrationDate;

    @JsonProperty("meetup_registered")
    private String meetupRegistered;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("following")
    private String following;

    @JsonProperty("interested_domain")
    private String interestedDomain;

    @JsonProperty("interested_subdomain")
    private String interestedSubdomain;

    @JsonProperty("feed_interest")
    private String feedInterest;

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getSchoolCollegeUniversity() {
        return schoolCollegeUniversity;
    }

    public void setSchoolCollegeUniversity(String schoolCollegeUniversity) {
        this.schoolCollegeUniversity = schoolCollegeUniversity;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getMeetupRegistered() {
        return meetupRegistered;
    }

    public void setMeetupRegistered(String meetupRegistered) {
        this.meetupRegistered = meetupRegistered;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getInterestedDomain() {
        return interestedDomain;
    }

    public void setInterestedDomain(String interestedDomain) {
        this.interestedDomain = interestedDomain;
    }

    public String getInterestedSubdomain() {
        return interestedSubdomain;
    }

    public void setInterestedSubdomain(String interestedSubdomain) {
        this.interestedSubdomain = interestedSubdomain;
    }

    public String getFeedInterest() {
        return feedInterest;
    }

    public void setFeedInterest(String feedInterest) {
        this.feedInterest = feedInterest;
    }
}
