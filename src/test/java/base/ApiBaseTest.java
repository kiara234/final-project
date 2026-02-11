package base;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiBaseTest {

    private final StringWriter apiLogWriter = new StringWriter();
    private final PrintStream apiLogStream = new PrintStream(
            new OutputStream() {
                @Override
                public void write(int b) {
                    apiLogWriter.write(b);
                }

                @Override
                public void write(byte[] b, int off, int len) {
                    apiLogWriter.write(new String(b, off, len, StandardCharsets.UTF_8));
                }
            }, true, StandardCharsets.UTF_8
    );

    @BeforeClass
    public void setupApiLogging() {
        RestAssured.filters(
                new RequestLoggingFilter(apiLogStream),
                new ResponseLoggingFilter(apiLogStream),
                new Filter() {
                    @Override
                    public Response filter(
                            FilterableRequestSpecification requestSpec,
                            FilterableResponseSpecification responseSpec,
                            FilterContext ctx) {

                        long start = System.currentTimeMillis();
                        Response response = ctx.next(requestSpec, responseSpec);
                        long duration = System.currentTimeMillis() - start;

                        logApiCallToFile(requestSpec, response, duration);
                        return response;
                    }
                }
        );
    }

    private void logApiCallToFile(
            FilterableRequestSpecification requestSpec,
            Response response,
            long durationMs) {

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String method = requestSpec.getMethod();
        String uri = requestSpec.getURI();
        int status = response.getStatusCode();

        StringBuilder log = new StringBuilder();
        log.append("=".repeat(60)).append("\n");
        log.append("TIMESTAMP : ").append(timestamp).append("\n");
        log.append("REQUEST   : ").append(method).append(" ").append(uri).append("\n");

        log.append("REQ HEADERS:\n");
        requestSpec.getHeaders().forEach(h ->
                log.append("  ").append(h.getName()).append(": ").append(h.getValue()).append("\n"));

        if (requestSpec.getBody() != null) {
            log.append("REQ BODY:\n  ").append(requestSpec.getBody().toString()).append("\n");
        }

        log.append("-".repeat(60)).append("\n");
        log.append("STATUS    : ").append(status).append("\n");
        log.append("DURATION  : ").append(durationMs).append("ms\n");

        log.append("RES HEADERS:\n");
        response.getHeaders().forEach(h ->
                log.append("  ").append(h.getName()).append(": ").append(h.getValue()).append("\n"));

        String resBody = response.getBody().asString();
        if (resBody != null && !resBody.isBlank()) {
            log.append("RES BODY:\n  ").append(resBody).append("\n");
        }

        log.append("=".repeat(60)).append("\n\n");

        try {
            String fileName = String.format("target/logs/api/%s_%s_%s.log", method, status, timestamp);
            FileUtils.writeStringToFile(new File(fileName), log.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to write API log file: " + e.getMessage());
        }
    }

    @AfterMethod
    public void attachApiLogsOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            attachApiLogsToAllure(result.getName());
        }
        apiLogWriter.getBuffer().setLength(0);
    }

    private void attachApiLogsToAllure(String testName) {
        String logs = apiLogWriter.toString();
        if (!logs.isBlank()) {
            Allure.addAttachment(
                    "API Logs - " + testName,
                    "text/plain",
                    new ByteArrayInputStream(logs.getBytes(StandardCharsets.UTF_8)),
                    "log"
            );
        }
    }

    @AfterClass
    public void teardownApiLogging() {
        RestAssured.reset();
    }
}