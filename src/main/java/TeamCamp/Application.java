package TeamCamp;

import TeamCamp.demo.common.properties.AppProperties;
import TeamCamp.demo.common.properties.CacheProperties;
import TeamCamp.demo.common.s3.AwsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableConfigurationProperties(value = {AppProperties.class, CacheProperties.class, AwsProperties.class})
public class Application {

    public static final String APPLICATION_LOCATIONS = "spring.config.locations="
            + "classpath:application.properties"
            + "/app/config/application-real1.properties"
            + "/app/config/application-real2.properties";


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .properties(APPLICATION_LOCATIONS)
                .run();
    }

}