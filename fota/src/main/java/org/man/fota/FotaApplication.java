package org.man.fota;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "org.man.fota")
public class FotaApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(FotaApplication.class)
                .profiles("dev", "prod")
                .run(args);
    }

}
