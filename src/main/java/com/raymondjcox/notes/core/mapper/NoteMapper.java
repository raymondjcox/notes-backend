package com.raymondjcox.notes.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.raymondjcox.notes.api.Note;

public class NoteMapper implements RowMapper<Note> {
	public NoteMapper() {
	}
	@Override
	public Note map(ResultSet rs, StatementContext sc) throws SQLException {
		return new Note(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getString("UPDATED_AT"));
	}
}