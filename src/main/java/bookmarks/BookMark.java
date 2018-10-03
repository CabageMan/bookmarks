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
        return String.format("<li><b>ID:</b> %s </br><b>Title:</b> %s </br><b>Content:</b> %s </br></li>",
                this.id, this.title, this.content);
    }
}

