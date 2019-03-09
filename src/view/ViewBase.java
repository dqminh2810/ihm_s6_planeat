package view;

public abstract class ViewBase {
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    public static boolean isMaximized = false;
    private String cssBase = "resources/css/styleBase.css";
    private String cssDark = "resources/css/styleDark.css";
    private String cssLight = "resources/css/styleLight.css";
    private String xmlFile;
    private String label;

    ViewBase(String xml, String label){
        this.xmlFile = xml;
        this.label = label;
    }

    public String getCssBase() {
        return cssBase;
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public String getCssDark() {
        return cssDark;
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

    public String getCssLight() {
        return cssLight;
    }
}
