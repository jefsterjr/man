package org.man.fileprocessor;

import org.man.fileprocessor.model.repository.ConfigurationRepository;
import org.man.fileprocessor.model.repository.FeatureRepository;
import org.man.fileprocessor.service.impl.DirectoryWatcherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FileProcessorApplication implements CommandLineRunner {

    @Value("${directory.path}")
    private String directoryPath;

    @Autowired
    private FeatureRepository repository;

    @Autowired
    private ConfigurationRepository configurationRepository;

    public static void main(String[] args) {
        new SpringApplicationBuilder(FileProcessorApplication.class)
                .profiles("dev", "prod")
                .run(args);
    }

    @Bean
    public DirectoryWatcherServiceImpl getDirectoryWatcher() {
        return new DirectoryWatcherServiceImpl();
    }

    @Override
    public void run(String... args) throws Exception {
        getDirectoryWatcher().watch(directoryPath);
    }
}
