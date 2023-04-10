package com.shopr.domains.articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

import com.shopr.enums.GameGenre;
import com.shopr.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllGames", query = "select g from Game g"),
        @NamedQuery(name = "findGameById", query = "select g from Game g where g.id = :id"),
        @NamedQuery(name = "findGameBySearch", query = "select g from Game g where g.id=:id or g.title LIKE :title or g.publisher LIKE :publ or g.gameGenre = :genre or g.price=:price or g.minAge = :age or g.supplierId = :supp")
})
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"title", "publisher"})) //rows can only have one combi of title and publisher, so for example only one row with (HHGTTG - Adams)
@Entity
public class Game extends Article{

    @Column(length = 100)
    private String publisher;
//    @Pattern(regexp = "([0-9]){1,2}", message = "Age has to be at least one character, at most two characters")
    @Min(value = 0, message = "Age must be 0 or up")
    @Column(nullable = false)
    private Integer minAge;
    @Enumerated(EnumType.STRING)
    private GameGenre gameGenre;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Type getType() {
        return type.GAME;
    }

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public GameGenre getGameGenre() {
		return gameGenre;
	}

	public void setGameGenre(GameGenre gameGenre) {
		this.gameGenre = gameGenre;
	}

	public void setType(Type type) {
		this.type = type;
	}
    
    
}

