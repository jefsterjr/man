package org.man.fileprocessor.service;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TemporaryFolder;
import org.man.fileprocessor.model.repository.FileRepository;
import org.man.fileprocessor.service.impl.FileProcessorServiceImpl;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FileProcessorTest {

    @Mock
    private FileRepository repository;

    @InjectMocks
    private FileProcessorService fileProcessorService = new FileProcessorServiceImpl();


    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeEach
    void setup() throws IOException {
        folder.create();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testing if is thowing the correct exception when the file was saved")
    public void testFileAlreadySaved() throws IOException {
        File file = folder.newFile("soft_001.csv");
        List<String> filesPath = Collections.singletonList("soft_001.csv");
        Mockito.when(repository.existsByKey(ArgumentMatchers.anyString())).thenReturn(true);
        fileProcessorService.getDataFromFile(filesPath, file.getParentFile().getPath());
    }

    @Test
    @DisplayName("Testing file processing")
    public void testFile() throws IOException {
        File file = folder.newFile("soft_001.csv");
        List<String> filesPath = Collections.singletonList("soft_001.csv");
        Mockito.when(repository.existsByKey(ArgumentMatchers.anyString())).thenReturn(false);
        fileProcessorService.getDataFromFile(filesPath, file.getParentFile().getPath());
    }
}
