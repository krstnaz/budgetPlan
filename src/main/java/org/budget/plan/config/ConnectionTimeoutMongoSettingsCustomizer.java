package org.budget.plan.config;

import com.mongodb.MongoClientSettings;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;

import java.util.concurrent.TimeUnit;

public class ConnectionTimeoutMongoSettingsCustomizer implements MongoClientSettingsBuilderCustomizer {
    private final Integer timeoutInSeconds;

    public ConnectionTimeoutMongoSettingsCustomizer(Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    @Override
    public void customize(MongoClientSettings.Builder clientSettingsBuilder) {
        if(timeoutInSeconds != null) {
            clientSettingsBuilder.applyToClusterSettings(builder -> builder.serverSelectionTimeout(timeoutInSeconds, TimeUnit.SECONDS));
            clientSettingsBuilder.applyToSocketSettings(builder -> builder.connectTimeout(timeoutInSeconds, TimeUnit.SECONDS));
        }
    }
}
