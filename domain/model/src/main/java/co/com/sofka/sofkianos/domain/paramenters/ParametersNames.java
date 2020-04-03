package co.com.sofka.sofkianos.domain.paramenters;


public enum ParametersNames {

    HOURS_TYPES("hoursTypes"),
    COLLABORATORS_DOMAINS("collaboratorsDomains"),
    TYPE_SERVICES("typeServices");

    private String parameterName;

    public String getParameterName() {
        return parameterName;
    }

    ParametersNames(String parameterName) {
        this.parameterName = parameterName;
    }
}
