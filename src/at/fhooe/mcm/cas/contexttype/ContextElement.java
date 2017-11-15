package at.fhooe.mcm.cas.contexttype;


public abstract class ContextElement {
    private int id = 0;
    private String key = "";
    private String type = "";
    private ContextMetaData contextMetaData = null;

    public ContextElement() {
    	
    }
    
    public ContextElement(int id, String key, String type, ContextMetaData contextMetaData) {
        this.id = id;
        this.key = key;
        this.type = type;
        this.contextMetaData = contextMetaData;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContextMetaData(ContextMetaData contextMetaData) {
        this.contextMetaData = contextMetaData;
    }

    public int getId() {

        return id;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public ContextMetaData getContextMetaData() {
        return contextMetaData;
    }

    @Override
    public String toString() {
        return "ContextElement{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", contextMetaData=" + contextMetaData +
                '}';
    }
}
