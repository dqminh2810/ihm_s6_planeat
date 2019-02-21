package view;

public abstract class ViewBase {
    private final int WIDTH = 640;
    private final int HEIGHT = 480;
    private String css = "resources/css/style.css";
    private String xmlFile;
    private String label;

    ViewBase(String xml, String label){
        this.xmlFile = xml;
        this.label = label;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public String getCss() {
        return css;
    }

    public String getLabel() {
        return label;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }
}
