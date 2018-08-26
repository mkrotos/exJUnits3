package com.krotos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    double delta = 0.0001;

    @Test
    public void create() {
        Note note = Note.of("Kamil Buła", 3);
        assertEquals("Kamil Buła", note.getName());
        assertEquals(3, note.getGrade(), delta);
    }

    @Test
    public void createNull() {
        assertThrows(IllegalArgumentException.class, () -> Note.of(null, 3.5));
    }

    @Test
    public void createBlankName() {
        assertThrows(IllegalArgumentException.class, () -> Note.of("   ", 3.5));
    }

    @Test
    public void createGradeLessThanMin() {
        assertThrows(IllegalArgumentException.class, () -> Note.of("Bartek", 1));
    }

    @Test
    public void createGradeMoreThanMax() {
        assertThrows(IllegalArgumentException.class, () -> Note.of("Bartek", 7.0));
    }

    @Test
    public void createGradeMin() {
        assertEquals(2, Note.of("Buka", 2).getGrade());
    }

    @Test
    public void createGradeMax() {
        assertEquals(5, Note.of("Buka", 5).getGrade());
    }

}