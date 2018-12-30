package com.raymondjcox.notes.db;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.raymondjcox.notes.api.Note;
import com.raymondjcox.notes.core.mapper.NoteMapper;

public interface NoteDAO {

	@SqlUpdate("insert into notes (title, content) values(:title, :content)")
	@GetGeneratedKeys
	int insert(@Bind("title") String title, @Bind("content") String content);
	
	@SqlUpdate("update notes set content = :content, title = :title where id = :id")
	void update(@Bind("id") int id, @Bind("title") String title, @Bind("content") String content);
	
	@SqlUpdate("delete from notes where id = :id")
	void delete(@Bind("id") int id);
	
	@SqlQuery("select * from notes where id = :id")
	@RegisterRowMapper(NoteMapper.class)
	Note findNoteById(@Bind("id") int id);
	
	@SqlQuery("select * from notes order by updated_at DESC, title")
	@RegisterRowMapper(NoteMapper.class)
	List<Note> findAllNotes();
}