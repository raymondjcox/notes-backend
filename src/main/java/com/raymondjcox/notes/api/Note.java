package com.raymondjcox.notes.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.jackson.JsonSnakeCase;

@JsonSnakeCase
public class Note {
	@NotNull
	@JsonProperty
	private Integer id;

	@NotNull
	@JsonProperty
	private String title;

	@NotNull
	@JsonProperty
	private String content;
	
	@JsonProperty
	private String updatedAt;

    public Note() {
        // Jackson deserialization
    }

    public Note(int id, String title, String content, String updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    
    public String getUpdatedAt() {
    	return updatedAt;
    }
}