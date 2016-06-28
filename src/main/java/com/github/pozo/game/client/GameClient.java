package com.github.pozo.game.client;

import com.github.pozo.game.client.auth.UserRepository;
import com.github.pozo.game.client.auth.UserService;
import com.github.pozo.game.client.resources.UserResource;
import com.github.pozo.game.client.resources.WebExceptionMapper;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by pozo on 2016.06.19..
 */
public class GameClient extends Application<GameClientConfiguration> {

    @Override
    public void initialize(Bootstrap<GameClientConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/fonts", "/fonts", null, "fonts"));
    }

    @Override
    public void run(GameClientConfiguration gameClientConfiguration, Environment environment) throws Exception {
        final UserResource userResource = new UserResource(new UserService(new UserRepository()));

        registerExceptionMappers(environment);
        registerResources(environment, userResource);
    }

    private void registerExceptionMappers(Environment environment) {
        environment.jersey().register(new WebExceptionMapper());
    }

    private void registerResources(Environment environment, UserResource userResource) {
        environment.jersey().register(userResource);
    }
}
