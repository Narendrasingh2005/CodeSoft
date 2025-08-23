package com.example.gradecalculator.controller;

import com.example.gradecalculator.model.GradeRequest;
import com.example.gradecalculator.model.GradeResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "*")
public class GradeController {

    @PostMapping("/calculate")
    public GradeResponse calculateGrade(@RequestBody GradeRequest request) {
        int total = request.getMarks().stream().mapToInt(Integer::intValue).sum();
        double average = (double) total / request.getMarks().size();
        String grade = calculateGrade(average);

        return new GradeResponse(total, average, grade);
    }

    private String calculateGrade(double avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }
}
