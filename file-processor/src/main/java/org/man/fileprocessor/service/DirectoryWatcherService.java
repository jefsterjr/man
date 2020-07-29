package org.man.fileprocessor.service;

import org.man.fileprocessor.exception.DirectoryNotFoundException;

import java.io.IOException;

public interface DirectoryWatcherService {

    void watch(String directoryPath) throws IOException, InterruptedException, DirectoryNotFoundException;
}
