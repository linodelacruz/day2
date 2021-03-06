package edu.acc.j2ee.hubbub;

import edu.acc.j2ee.validation.ValidatedDomain;
import edu.acc.j2ee.validation.ValidationException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Post extends ValidatedDomain implements Serializable {
    private User author;
    private String content;
    private LocalDateTime posted;
    private int id;

    public Post() {
    }

    public Post(User author, String content, LocalDateTime posted, int id) {
        this.author = author;
        setContent(content);
        this.posted = posted;
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&apos;")
                .replace("\"", "&quo;")
                .replace("`", "&#96;")
                .trim();
    }

    public LocalDateTime getPosted() {
        return posted;
    }

    public void setPosted(LocalDateTime posted) {
        this.posted = posted;
    }
    
    public int getId() { return id; }
    
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" + "author=" + author + ", content=" + content + ", posted=" + posted + ", id=" + '}';
    }

    @Override
    protected void validate() throws ValidationException {
        if (content.length() < 1)
            addError("content", content, "must have at least one character");
        if (content.length() > 200)
            addError("content", content, "cannot exceed 200 characters");
        if (hasErrors()) throw new ValidationException(getErrors());
    }    
}
