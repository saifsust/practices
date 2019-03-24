package org.woadec.uralems.models;

public class Home {
    private String iconLinek;
    private String imageLine;
    private String pageLine;
    private String description;
    private String header;
    private String title;

    public Home(String iconLinek, String imageLine, String pageLine, String description, String header, String title) {
        this.iconLinek = iconLinek;
        this.imageLine = imageLine;
        this.pageLine = pageLine;
        this.description = description;
        this.header = header;
        this.title = title;
    }

    public String getIconLinek() {
        return iconLinek;
    }

    public void setIconLinek(String iconLinek) {
        this.iconLinek = iconLinek;
    }

    public String getImageLine() {
        return imageLine;
    }

    public void setImageLine(String imageLine) {
        this.imageLine = imageLine;
    }

    public String getPageLine() {
        return pageLine;
    }

    public void setPageLine(String pageLine) {
        this.pageLine = pageLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Home{" +
                "iconLinek='" + iconLinek + '\'' +
                ", imageLine='" + imageLine + '\'' +
                ", pageLine='" + pageLine + '\'' +
                ", description='" + description + '\'' +
                ", header='" + header + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
