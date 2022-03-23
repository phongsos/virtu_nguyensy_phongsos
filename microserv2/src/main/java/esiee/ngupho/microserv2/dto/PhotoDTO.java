package esiee.ngupho.microserv2.dto;

public class PhotoDTO {
    private long id;
    private String owner;
    private String title;

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

    @Override
    public String toString() {
        return "PhotoDTO{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
