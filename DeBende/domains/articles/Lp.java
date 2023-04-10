package com.shopr.domains.articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.shopr.enums.LpGenre;
import com.shopr.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title", "artist"})) //tells this table to only have one combination of a certain title (from Article) and a certain artist (from Lp)
@NamedQueries({
        @NamedQuery(name = "findAllLp", query = "select l from Lp l"),
        @NamedQuery(name = "findLpById", query = "SELECT l from Lp l where l.id = :id"),
        @NamedQuery(name = "findLpBySearch", query = "select lp from Lp lp where lp.id=:id or lp.title LIKE :title or lp.artist LIKE :artist or lp.lpGenre = :genre or lp.price=:price or lp.supplierId = :supp")
})
@Entity
public class Lp extends Article {

    public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public LpGenre getLpGenre() {
		return lpGenre;
	}

	public void setLpGenre(LpGenre lpGenre) {
		this.lpGenre = lpGenre;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Column(length = 100, nullable = false)
    private String artist;
    @Enumerated(EnumType.STRING)
    private LpGenre lpGenre;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Type getType() {
        return type.LP;
    }

}

