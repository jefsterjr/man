package org.man.fota.model;

import org.man.fota.model.enums.ConfigurationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    private ConfigurationType type;

    public Configuration(String code, ConfigurationType type) {
        this.code = code;
        this.type = type;
    }

    public Configuration() {
    }


    public Configuration(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ConfigurationType getType() {
        return type;
    }

    public void setType(ConfigurationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Configuration:{" +
                "id:" + id +
                ", code:'" + code + '\'' +
                ", type:'" + type + '\'' +
                '}';
    }
}
