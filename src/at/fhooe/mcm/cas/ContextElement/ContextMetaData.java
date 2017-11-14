package at.fhooe.mcm.cas;

import java.util.Date;

public class ContextMetaData {
    private ContextPosition position = null;
    private String originator = "";
    private String version = "";
    private String description = "";
    private String sourceType = "";
    private Date temporalInformation = null;

    public ContextMetaData(ContextPosition position, String originator,
                           String version, String description, String sourceType,
                           Date temporalInformation) {
        this.position = position;
        this.originator = originator;
        this.version = version;
        this.description = description;
        this.sourceType = sourceType;
        this.temporalInformation = temporalInformation;
    }

    public ContextPosition getPosition() {
        return position;
    }

    public String getOriginator() {
        return originator;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceType() {
        return sourceType;
    }

    public Date getTemporalInformation() {
        return temporalInformation;
    }

    public void setPosition(ContextPosition position) {
        this.position = position;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setTemporalInformation(Date temporalInformation) {
        this.temporalInformation = temporalInformation;
    }

    @Override
    public String toString() {
        return "ContextMetaData{" +
                "position=" + position +
                ", originator='" + originator + '\'' +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", temporalInformation=" + temporalInformation +
                '}';
    }
}
