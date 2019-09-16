package com.ivanyuyuk.entity;

import javax.persistence.*;

@Entity
@Table(name="product_warehouse")
public class ProductWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "available")
    private Long available;

    @Column(name = "sold")
    private Long sold;

    @OneToOne(mappedBy = "warehouse", cascade ={
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAvailable() {
        return available;
    }

    public void setAvailable(Long available) {
        this.available = available;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }

    public ProductWarehouse(){

    }

    public ProductWarehouse(Long available, Long sold) {
        this.available = available;
        this.sold = sold;
    }

    public void addSold(Long count){
        this.sold+=count;
    }

    public void decreaseAvailable(Long count){
        this.available=this.available-count;
    }

    public void increaseAvailable(Long count){
        this.available+=count;
    }

    @Override
    public String toString() {
        return "ProductWarehouse{" +
                "id=" + id +
                ", available=" + available +
                ", sold=" + sold +
                '}';
    }
}
