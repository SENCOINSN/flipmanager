package com.feature.flipmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_feature_flip")
@Getter
@Setter
public class FeatureFlip {
    @Id
    private String  uuid;
    @Column(unique = true)
    private String nameFeature;
    private boolean activate = false;
    private Date createAt=new Date();
    private String descriptionFeature;

    public FeatureFlip() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }
}
