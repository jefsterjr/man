package org.man.fileprocessor.model;

import org.man.fileprocessor.model.enums.FeatureType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "feature_configuration")
public class FeatureConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "configuration_id")
    private Configuration configuration;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private FeatureType type;

    public FeatureConfiguration() {
    }

    public FeatureConfiguration(Feature feature, Configuration configuration, FeatureType type) {
        this.feature = feature;
        this.configuration = configuration;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public FeatureType getType() {
        return type;
    }

    public void setType(FeatureType type) {
        this.type = type;
    }
}
