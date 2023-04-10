package com.shopr.domains.articles;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.shopr.enums.FictionGenre;
import com.shopr.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("FICTION")
@Entity
public class Fiction extends Book {

    @Enumerated(EnumType.STRING)
    private FictionGenre fictionGenre;
    private String contents; //default length =225
    private String title; //default length =225

    public Type getType(){
        return Type.FICTION;
    }

	public FictionGenre getFictionGenre() {
		return fictionGenre;
	}

	public void setFictionGenre(FictionGenre fictionGenre) {
		this.fictionGenre = fictionGenre;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


}
