package com.jackiew;

public class FailureScenario {
    private String featureName;
    private String scenarioName;
    private String stepName;
    private String cause;

    public FailureScenario(String featureName, String scenarioName, String stepName, String cause) {
        this.featureName = featureName;
        this.scenarioName = scenarioName;
        this.stepName = stepName;
        this.cause = cause;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
