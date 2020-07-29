package org.man.fileprocessor.model.repository;

import org.man.fileprocessor.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {


    Boolean existsByKey(String key);
}
