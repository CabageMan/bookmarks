package bookmarks;

public class BookMark {

    // The class fields
    public int id;
    public String title, content;

    //The constructor
    public BookMark(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    //Bookmark view method
    @Override
    public String toString() {
        return String.format("ID: %s | Title: %s | Content: %s",
                this.id, this.title, this.content);
    }
}

