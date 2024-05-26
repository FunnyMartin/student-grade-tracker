package Tests;

import Logic.Manager;
import Logic.Subject;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ManagerTest {
    @Test
    public void testAddMark() {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        subjects.add(new Subject("test", new ArrayList<>()));
        manager.addMark("test", 1, -1);
        System.out.println(subjects);
        assertEquals(0, subjects.get(0).gradeArray().size());
        manager.addMark("test", -1, 1);
        System.out.println(subjects);
        assertEquals(0, subjects.get(0).gradeArray().size());
        manager.addMark("test", 0, 1);
        System.out.println(subjects);
        assertEquals(0, subjects.get(0).gradeArray().size());
        manager.addMark("test", 1, 0);
        System.out.println(subjects);
        assertEquals(0, subjects.get(0).gradeArray().size());
        manager.addMark("test", 1, 1);
        System.out.println(subjects);
        assertEquals(1, subjects.get(0).gradeArray().size());
    }

    @Test
    public void testRemoveMark() {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        subjects.add(new Subject("test", new ArrayList<>()));
        manager.addMark("test", 1, 1);
        System.out.println(subjects);
        manager.removeMark("test", 0);
        System.out.println(subjects);
        assertEquals(0, subjects.get(0).gradeArray().size());
        manager.removeMark("test", 0);
    }

    @Test
    public void testAddSubject() {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        manager.addSubject("-");
        System.out.println(subjects);
        assertEquals(0, subjects.size());
        manager.addSubject("a");
        manager.addSubject("a");
        assertEquals(1, subjects.size());
    }

    @Test
    public void testRemoveSubject() {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        manager.addSubject("a");
        manager.addSubject("b");
        manager.addSubject("c");
        manager.removeSubject("");
        assertEquals(3, subjects.size());
        manager.removeSubject("a");
        manager.removeSubject("b");
        manager.removeSubject("c");
        assertEquals(0, subjects.size());
    }


}