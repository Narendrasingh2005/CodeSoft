package com.example.gradecalculator.model;

public class GradeResponse {
    private int totalMarks;
    private double averagePercentage;
    private String grade;

    public GradeResponse(int totalMarks, double averagePercentage, String grade) {
        this.totalMarks = totalMarks;
        this.averagePercentage = averagePercentage;
        this.grade = grade;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public double getAveragePercentage() {
        return averagePercentage;
    }

    public String getGrade() {
        return grade;
    }
}
