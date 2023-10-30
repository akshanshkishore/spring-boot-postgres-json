package com.am.spring.jpa.postgresql.model;

import javax.persistence.*;

@Entity
@Table(name = "json_journal")
public class JsonJournal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "json_code")
	private String jsoncode;

	@Column(name = "json_text")
	private String jsontext;

	public JsonJournal() {

	}

	public JsonJournal(String jsoncode, String jsontext) {
		this.jsoncode = jsoncode;
		this.jsontext = jsontext;
	}

	public String getJsoncode() {
		return jsoncode;
	}

	public void setJsoncode(String jsoncode) {
		this.jsoncode = jsoncode;
	}

	public String getJsontext() {
		return jsontext;
	}

	public void setJsontext(String jsontext) {
		this.jsontext = jsontext;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", jsoncode=" + jsoncode + ", jsontext=" + jsontext + "]";
	}

}
