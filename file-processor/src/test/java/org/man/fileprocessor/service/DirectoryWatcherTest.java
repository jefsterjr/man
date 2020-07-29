package org.man.fileprocessor.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TemporaryFolder;
import org.man.fileprocessor.exception.DirectoryNotFoundException;
import org.man.fileprocessor.service.impl.DirectoryWatcherServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class DirectoryWatcherTest {

    @InjectMocks
    private DirectoryWatcherService directoryWatcherService = new DirectoryWatcherServiceImpl();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeEach
    void setup() throws IOException {
        folder.create();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Testing if is thowing the correct exception when the directory is not found")
    public void testDirectoryNotFound() {
        Assert.assertThrows(DirectoryNotFoundException.class, () -> directoryWatcherService.watch("test"));
    }
}
