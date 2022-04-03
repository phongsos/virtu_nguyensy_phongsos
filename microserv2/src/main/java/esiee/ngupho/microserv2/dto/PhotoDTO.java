package esiee.ngupho.microserv2.dto;

public class PhotoDTO {
    private long id;
    private String owner;
    private String title;
    private String source;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PhotoDTO{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
