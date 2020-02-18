package com.itcast.demo.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "jd_item")
public class JdItemInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger id;
    private BigInteger spu;
    private BigInteger sku;
    private String title;
    private Double price;
    private String pic;
    private String url;
    private Date created;
    private Date updated;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSpu() {
        return spu;
    }

    public void setSpu(BigInteger spu) {
        this.spu = spu;
    }

    public BigInteger getSku() {
        return sku;
    }

    public void setSku(BigInteger sku) {
        this.sku = sku;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "JdItemInfo{" +
                "id=" + id +
                ", spu=" + spu +
                ", sku=" + sku +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                ", url='" + url + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
