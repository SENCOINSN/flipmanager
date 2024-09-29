package com.feature.flipmanagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "feature_context")
@Getter
@Setter
public class FeatureContext {
    @Id
    private String uuid;
    private String nameFeature;
    private String targetUser; // target for flip
    private String targetGroup; // target for flip
    @ElementCollection
    @CollectionTable(name = "user_groups", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> userGroups=new ArrayList<>();
    private Date createAt=new Date();


    public FeatureContext() {
        this.uuid = java.util.UUID.randomUUID().toString();
    }
}
