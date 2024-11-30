package com.billing.entities;

import com.billing.models.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Bill {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billDate;
    private Long customerId;

    @OneToMany(mappedBy = "bill")
    private List<ProductItem> productItems;

    @Transient
    private Customer customer;
}
