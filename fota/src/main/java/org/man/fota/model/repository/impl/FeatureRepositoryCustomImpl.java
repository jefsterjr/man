package org.man.fota.model.repository.impl;

import org.man.fota.model.Feature;
import org.man.fota.model.enums.FeatureType;
import org.man.fota.model.repository.FeatureRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class FeatureRepositoryCustomImpl implements FeatureRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    private Query query;

    @Override
    public Set<Feature> findIncompatiblesFeaturesByUUID(final UUID uuidVin) {
        query = entityManager.createNativeQuery(getQuery(), Feature.class);
        query.setParameter(1, uuidVin);
        query.setParameter(2, uuidVin);
        return (Set<Feature>) new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Feature> findInstallableFeaturesByUUID(final UUID uuidVin) {
        final String sql = "select distinct f.id, f.name  from man.feature f left join (" + getQuery() + ") fv on f.id = fv.id where fv.id is null";
        query = entityManager.createNativeQuery(sql, Feature.class);
        query.setParameter(1, uuidVin);
        query.setParameter(2, uuidVin);
        Set<Feature> features = new HashSet<Feature>(query.getResultList());
        return features;
    }

    private String getQuery() {
        final String subQuery = "select c.id from man.vehicle v " +
                " inner join man.vehicle_configuration vc on v.id = vc.vehicle_id" +
                " inner join man.configuration c on vc.configuration_id = c.id " +
                " where v.uuid_vin = ? ";
        return "select distinct f.id, f.name from man.feature f " +
                " inner join man.feature_configuration fc on f.id = fc.feature_id and fc.type = '" + FeatureType.PRESENT + "' " +
                " inner join man.feature_configuration fcn on f.id = fcn.feature_id and fcn.type = '" + FeatureType.NOT_PRESENT + "' " +
                " inner join man.configuration c on fc.configuration_id = c.id " +
                " inner join man.configuration cn on fcn.configuration_id = cn.id " +
                " where c.id not in (" + subQuery + ") and cn.id in (" + subQuery + ")";
    }


}
