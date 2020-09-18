package com.swiss.feature.toggles.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity extends CreateAndUpdateBaseEntity {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public CustomerEntity(Long id) {
        this.id = id;
    }
}
