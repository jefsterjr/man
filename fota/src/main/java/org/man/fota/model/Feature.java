package org.man.fota.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "feature")
    private Set<FeatureConfiguration> featureConfigurations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<FeatureConfiguration> getFeatureConfigurations() {
        return featureConfigurations;
    }

    public void setFeatureConfigurations(Set<FeatureConfiguration> featureConfigurations) {
        this.featureConfigurations = featureConfigurations;
    }

}
