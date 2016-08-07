package com.lodz360;

/**
 * Created by michalina on 31/07/16.
 */
public class User {
    private String name;
    private int age;
    private float weight;
    private float height;

    public User(String name, int age, float weight, float height) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height / 100;
    }


    public String toString() {
        return name + " " + " " + age + " " + weight + " " + height;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public float getBmi() {
        return weight / (height * height);
    }

    public  String checkbmi() throws BMIException {
        if (getBmi() > 50) {
            throw new BMIToHighException();
        } else if (getBmi() >= 30) {
            return "You're in the obese range! You have a huge problem, you must watch your diet and start exercising";
        } else if (getBmi() >= 25) {
            return "You're in the overweight range! You should watch your diet and start exercises";
        } else if (getBmi() >= 18.5) {
            return "You're in the healthy weight range. It's good to hear you're taking care of yourself";
        } else if (getBmi() > 5) {
            return "You're in the underweight range. You could use more good calories";
        } else {
            throw new BMIToLowException();
        }

    }

}
