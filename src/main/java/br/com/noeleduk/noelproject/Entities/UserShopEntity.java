package br.com.noeleduk.noelproject.Entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "user_shop")
public class UserShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "created_at")
    private Date createdAt;
}