package com.shopr.domains.articles;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.shopr.enums.FictionGenre;
import com.shopr.enums.NonFictionSubject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //single = one table for each class in hierarchy (book -> fiction/nonf)
@NamedQueries({ //list of multiple @NamedQuery
        @NamedQuery(name = "allFiction", query = "SELECT f from Fiction f"),
        @NamedQuery(name = "allNonFiction", query = "select nf from NonFiction nf"),
        @NamedQuery(name = "allBooks", query = "select b from Book b"),
        @NamedQuery(name = "fictionById", query = "SELECT f from Fiction f where f.id = :id"),
        @NamedQuery(name = "nonFictionById", query = "SELECT nf from NonFiction nf where nf.id = :id"),
        @NamedQuery(name = "findFictionBySearch", query = "SELECT f from Fiction f where f.id = :id or f.title = :title or f.author = :author or f.contents =:contents or f.price=:price or f.pages=:pages or f.supplierId=:supp or f.fictionGenre=:fgenre"),
        @NamedQuery(name = "findNonFictionBySearch", query = "SELECT nf from NonFiction nf where nf.id=:id or nf.title=:title or nf.author=:author or nf.nonFictionSubject=:nfsubject or nf.price=:price or nf.pages=:pages or nf.supplierId=:supp"),
})
@DiscriminatorColumn(name = "type") //say what to name the column where the discriminator is placed, default is dtype. add discriminatorType to specify (can be STRING, INT or CHAR)
@Entity //mapping from persistent layer (class) to db-layer (table)
public abstract class Book extends Article{ //also abstract, not instantiated in persistent layer

    @Column(length = 100)
    private String author;
    @Column(nullable = false, unique = true) //unique: no two rows with same value
//    @Pattern(regexp = "(?:ISBN(?:-1[03])?:?)?(?=[0-9X]{10}$ | (?=(?:[0-9]+[-]){3})[-0-9X]{13}$ | 97[89][0-9]{10}$ | (?=(?:[0-9]+[-]){4})[-0-9]{17}$)(?:97[89][-]?)?[0-9]{1,5}[-]?[0-9]+[-]?[0-9]+[-]?[0-9X]$", message = "ISBN-format has to be ###-##-####-###-#") // use regexr.com to test regexp patterns!
    private String isbn; // 123-45-6789-123-4
    @Column(nullable = false)
    private Integer pages;


    //3 methods needed to create list<book> with data from subclass
    public FictionGenre getFictionGenre(){
        Fiction fiction = new Fiction();
        return fiction.getFictionGenre();
    }
    public String getContents(){
        Fiction fiction = new Fiction();
        return fiction.getContents();
    }
    public NonFictionSubject getNonFictionSubject() {
        NonFiction nonFiction = new NonFiction();
        return nonFiction.getNonFictionSubject();
    }
    
    public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
