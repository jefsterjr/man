package org.man.fileprocessor.service;

import javax.transaction.Transactional;
import java.util.List;

public interface FileProcessorService {

    @Transactional
    void getDataFromFile(List<String> files, String directoryPath);
}
