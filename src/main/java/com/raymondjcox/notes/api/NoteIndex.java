package com.raymondjcox.notes.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteIndex {
    private List<Note> notes;

    public NoteIndex() {
        // Jackson deserialization
    }

    public NoteIndex(List<Note> notes) {
    	this.notes = notes;
    }

    @JsonProperty
    public List<Note> getNotes() {
        return notes;
    }
}