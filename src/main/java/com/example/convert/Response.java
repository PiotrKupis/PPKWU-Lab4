package com.example.convert;

public class Response {

    private String uppercase;
    private String lowercase;
    private String numbers;
    private String specialChars;
    private String combination;

    public Response() {
    }

    public String getUppercase() {
        return uppercase;
    }

    public void setUppercase(String uppercase) {
        this.uppercase = uppercase;
    }

    public String getLowercase() {
        return lowercase;
    }

    public void setLowercase(String lowercase) {
        this.lowercase = lowercase;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getSpecialChars() {
        return specialChars;
    }

    public void setSpecialChars(String specialChars) {
        this.specialChars = specialChars;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Override
    public String toString() {
        return "Response{" +
            "uppercase='" + uppercase + '\'' +
            ", lowercase='" + lowercase + '\'' +
            ", numbers='" + numbers + '\'' +
            ", specialChars='" + specialChars + '\'' +
            ", combination='" + combination + '\'' +
            '}';
    }
}
