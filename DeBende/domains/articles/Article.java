package com.shopr.domains.articles;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass //no entity, wont be mapped in DB, but still exists as class, sends fields to classes that extend from it
public abstract class Article { //abstract, cant be instantiated

    @Id //identifier needs to be in superclass, will be inherited in subclasses that extend from it
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long id;
    private String title;
    @Column(scale = 2, nullable = false, precision = 2) //scale: 2 digits after comma; nullable = false -> cant be null
    private Double price;
    @Column(length = 100, nullable = false) // default length 255, reduced to 100
    private Long supplierId;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}


//    @OneToMany
//    private List<Review> reviewList;



}
