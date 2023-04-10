package com.shopr.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.shopr.enums.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name="allOrderArticles", query="SELECT oa from OrderArticle oa"),
        @NamedQuery(name="findOAById", query="SELECT oa from OrderArticle oa where oa.id = :id")
})
public class OrderArticle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String creator; //combine author, artist, publisher
    private Double priceSingle;
    private Long amount;
    private Type type;
    private Double priceTotal;

//    @OneToMany(fetch = FetchType.EAGER) //works without entity          default: (fetch = FetchType.LAZY)
//    private Article article;
//    @ManyToOne
//    private Order order;

    public Long getOneAmount(){
        return 1L;
    }

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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Double getPriceSingle() {
		return priceSingle;
	}

	public void setPriceSingle(Double priceSingle) {
		this.priceSingle = priceSingle;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Double getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}

}
