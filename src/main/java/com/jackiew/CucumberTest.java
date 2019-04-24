package com.jackiew;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableScheduling
public class CucumberTest {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = CucumberTest.class.getClassLoader().getResourceAsStream("src/test/resources/test.txt");
        System.out.println(System.getProperty("user.dir"));
//        SpringApplication.run(CucumberTest.class, args);
        new CucumberTest().start();
    }

    @Scheduled(fixedDelay = 5000)
    public void start() throws IOException {
        System.out.println(System.getProperty("user.dir"));
//        two ways to specify scenarios to run
//        A: use -n name filter
//        B: use path with line number
//       A: String[] cucumberOptions = {"-g", "com.jackiew","-n","Monday isn't Friday","-n","Tuesday is Not Friday11","classpath:features"};
//       B: String[] cucumberOptions = {"-g", "com.jackiew","classpath:features/test/is_it_friday_yet.feature:4","classpath:features/test/it_friday_yet.feature:59"};
        String[] cucumberOptions = {"-g", "com.jackiew", "-p", "json:target/report.json", "-n", "Monday isn't Friday", "-n", "Tuesday is Not Friday11", "classpath:features"};
        byte status = -1;
        try {
            status = CucumberStaticRunner.startTests(cucumberOptions);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if (status == 0) {
            System.out.println("success");
        } else {
            generateReport();
            parseReport();
        }
    }

    private static void parseReport() {
        try {
            InputStream failureReportStream = Files.newInputStream(Paths.get("target", "cucumber-html-reports", "overview-failures.html"));
            InputStream overviewStream = Files.newInputStream(Paths.get("target", "cucumber-html-reports", "overview-features.html"));
            Document failureOverviewDoc = Jsoup.parse(failureReportStream, StandardCharsets.UTF_8.name(), ".");
            Document overviewDoc = Jsoup.parse(overviewStream, StandardCharsets.UTF_8.name(), ".");
            String PassedSteps = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(2)").text();
            String failedSteps = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(3)").text();
            String skippedSteps = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(4)").text();
            String pendingSteps = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(5)").text();
            String undefinedSteps = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(6)").text();
            String totalSteps = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(7)").text();
            String passedScenarios = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(8)").text();
            String failedScenarios = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(9)").text();
            String totalScenarios = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(10)").text();
            String totalFeatures = overviewDoc.select("#tablesorter > tfoot > tr:nth-child(1) > td:nth-child(12)").text();
            CucumberReport report = new CucumberReport();
            report.setTotalFeatures(totalFeatures);
            report.setPassedScenarios(passedScenarios);
            report.setFailedScenarios(failedScenarios);
            report.setTotalScenarios(totalScenarios);
            report.setPassedSteps(PassedSteps);
            report.setFailedSteps(failedSteps);
            report.setSkippedSteps(skippedSteps);
            report.setPendingSteps(pendingSteps);
            report.setUndefinedSteps(undefinedSteps);
            report.setTotalSteps(totalSteps);
            Elements scenarios = failureOverviewDoc.select(".element");
            for (Element scenario : scenarios) {
                String featureName = scenario.select(".indention > a").text();
                String scenarioName = scenario.select("span.collapsable-control > div > span.name").text();
                String stepName = scenario.select(".step div.brief.failed > span.name").text();
                String cause = scenario.select(".step pre").text();
                report.addScenario(featureName, scenarioName, stepName, cause);
            }
            System.out.println(report);
        } catch (IOException e) {
            System.out.println("cannot find the report");
        }
    }

    private static void generateReport() {
        //something wrong need to find out the errors and send an email
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/report.json");

        String buildNumber = "101";
        String projectName = "Live Demo Project";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);

        configuration.addClassifications("Browser", "Firefox");
        configuration.addClassifications("Branch", "release/1.0");
        configuration.setSortingMethod(SortingMethod.NATURAL);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}
