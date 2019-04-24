package com.jackiew;

import java.util.ArrayList;
import java.util.List;

public class CucumberReport {
    private String totalFeatures;
    private String passedScenarios;
    private String failedScenarios;
    private String totalScenarios;
    private String PassedSteps;
    private String failedSteps;
    private String skippedSteps;
    private String pendingSteps;
    private String undefinedSteps;
    private String totalSteps;
    private List<FailureScenario> scenarios = new ArrayList<>();

    public String getTotalFeatures() {
        return totalFeatures;
    }

    public void addScenario(String featureName, String ScenarioName, String stepName, String cause) {
        scenarios.add(new FailureScenario(featureName, ScenarioName, stepName, cause));
    }

    public void setTotalFeatures(String totalFeatures) {
        this.totalFeatures = totalFeatures;
    }

    public String getPassedScenarios() {
        return passedScenarios;
    }

    public void setPassedScenarios(String passedScenarios) {
        this.passedScenarios = passedScenarios;
    }

    public String getFailedScenarios() {
        return failedScenarios;
    }

    public void setFailedScenarios(String failedScenarios) {
        this.failedScenarios = failedScenarios;
    }

    public String getTotalScenarios() {
        return totalScenarios;
    }

    public void setTotalScenarios(String totalScenarios) {
        this.totalScenarios = totalScenarios;
    }

    public String getPassedSteps() {
        return PassedSteps;
    }

    public void setPassedSteps(String passedSteps) {
        PassedSteps = passedSteps;
    }

    public String getFailedSteps() {
        return failedSteps;
    }

    public void setFailedSteps(String failedSteps) {
        this.failedSteps = failedSteps;
    }

    public String getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(String totalSteps) {
        this.totalSteps = totalSteps;
    }

    public List<FailureScenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<FailureScenario> scenarios) {
        this.scenarios = scenarios;
    }

    public String getSkippedSteps() {
        return skippedSteps;
    }

    public void setSkippedSteps(String skippedSteps) {
        this.skippedSteps = skippedSteps;
    }

    public String getPendingSteps() {
        return pendingSteps;
    }

    public void setPendingSteps(String pendingSteps) {
        this.pendingSteps = pendingSteps;
    }

    public String getUndefinedSteps() {
        return undefinedSteps;
    }

    public void setUndefinedSteps(String undefinedSteps) {
        this.undefinedSteps = undefinedSteps;
    }
}
