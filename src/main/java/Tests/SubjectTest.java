package Tests;

import Logic.Grade;
import Logic.Subject;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SubjectTest {

    private Subject initialize() {
        ArrayList<Grade> grades = new ArrayList<>();
        grades.add(new Grade(1,1));
        grades.add(new Grade(2,2));
        grades.add(new Grade(3,3));
        grades.add(new Grade(4,4));
        grades.add(new Grade(5,5));
        return new Subject("testSubject", grades);
    }

    @Test
    public void testCalculateMark(){
        Subject testSubject = initialize();
        System.out.println(testSubject.calculateMark());
        assertEquals(3.67, testSubject.calculateMark(), 0.01);
    }

    @Test
    public void testCalculateRequiredGrade(){
        Subject testSubject = initialize();
        System.out.println(testSubject.calculateRequiredGrade(2));
        System.out.println(testSubject.calculateRequiredGrade(2.5));
        System.out.println(testSubject.calculateRequiredGrade(3));
        System.out.println(testSubject.calculateRequiredGrade(3.5));
        System.out.println(testSubject.calculateRequiredGrade(4));
        assertEquals("Grade: 1 weight: 25", testSubject.calculateRequiredGrade(2));
        assertEquals("Grade: 2 weight: 35", testSubject.calculateRequiredGrade(2.5));
        assertEquals("Grade: 1 weight: 5", testSubject.calculateRequiredGrade(3));
        assertEquals("Grade: 1 weight: 1", testSubject.calculateRequiredGrade(3.5));
        assertEquals("Grade: 5 weight: 5", testSubject.calculateRequiredGrade(4));
    }
}