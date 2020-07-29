package org.man.fota.model;

import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid_vin")
    private UUID uuidVin;

    @Column
    private String vin;

    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "vehicle_configuration",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "configuration_id"))
    private Set<Configuration> configurations;

    public Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuidVin() {
        return uuidVin;
    }

    public void setUuidVin(UUID uuidVin) {
        this.uuidVin = uuidVin;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Set<Configuration> getConfigurations() {
        return configurations;
    }

    public void addConfiguration(Configuration configuration) {
        if (this.id != null) Hibernate.initialize(configurations);
        if (this.configurations == null) configurations = new HashSet<>();
        configurations.add(configuration);
    }


    public void setConfigurations(Set<Configuration> configurations) {
        this.configurations = configurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;

        Vehicle vehicle = (Vehicle) o;

        return getVin().equals(vehicle.getVin());
    }

    @Override
    public int hashCode() {
        return getVin().hashCode();
    }

    @Override
    public String toString() {
        return "Vehicle : {" +
                "id:" + id +
                ", uuid_vin:" + uuidVin +
                ", vin:'" + vin + '\'' +
                "], configurations:[" + configurations +
                "]}";
    }
}
