package com.ua.codespace.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Promotion {

    @Id
    Long id;
    String description;
    BigDecimal discount;

    @OneToOne
    @MapsId
    Item item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Promotion promotion = (Promotion) o;

        if (!description.equals(promotion.description)) return false;
        return discount.equals(promotion.discount);

    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + discount.hashCode();
        return result;
    }
}
