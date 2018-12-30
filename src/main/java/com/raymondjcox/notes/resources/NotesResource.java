package com.raymondjcox.notes.resources;

import com.raymondjcox.notes.api.Note;
import com.raymondjcox.notes.api.NoteIndex;
import com.raymondjcox.notes.db.NoteDAO;

import io.dropwizard.jersey.PATCH;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
public class NotesResource {
    private final NoteDAO notesDAO;

    public NotesResource(NoteDAO notesDAO) {
    	this.notesDAO = notesDAO;
    }

    @GET
    @Timed
    public NoteIndex getNoteTitles() {
    	return new NoteIndex(notesDAO.findAllNotes());
    }

    @GET 
    @Path("/{noteId}")
    @Timed
    public Note getNote(@PathParam("noteId") int noteId) {
    	return notesDAO.findNoteById(noteId);
    }
    
    @PATCH
    @Timed
    @Path("/{noteId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Note updateNote(@PathParam("noteId") int noteId, Note note) {
    	notesDAO.update(note.getId(), note.getTitle(), note.getContent());
		return note;
    }

    @DELETE
    @Timed
    @Path("/{noteId}")
    public void deleteNote(@PathParam("noteId") int noteId) {
    	notesDAO.delete(noteId);
    }

    @POST
    @Timed
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Note createNote(Note note) {
    	int id = notesDAO.insert(note.getTitle(), note.getContent());
    	return new Note(id, note.getTitle(), note.getContent());
    }
}