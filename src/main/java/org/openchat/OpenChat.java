package org.openchat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import static spark.Spark.*;

public class OpenChat {

    private static Logger logger = LoggerFactory.getLogger(OpenChat.class);

    public void start() {
        port(4321);
        enableCORS();
        setLog();
        createRoutes();
    }

    private void createRoutes() {
        get("status", (req, res) -> "OpenChat: OK!");
    }

    public void stop() {
        Spark.stop();
    }

    public void awaitInitialization() {
        Spark.awaitInitialization();
    }

    private void enableCORS() {
        // Enable Cross Origin Resource Sharing.
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }

    private void setLog() {
        before((request, response) -> {
            logger.info("URL request: " + request.requestMethod() + " " + request.uri() + " - headers: " + request.headers());
        });
    }

}
