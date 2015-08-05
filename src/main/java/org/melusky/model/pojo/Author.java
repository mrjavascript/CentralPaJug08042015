package org.melusky.model.pojo;

import com.mysema.query.annotations.QueryEntity;

import java.util.Date;

/**
 * Created by mmelusky on 8/4/2015.
 */
@QueryEntity
public class Author {

    private String authorName;
    private Date dateCreated;

    public Author(String authorName, Date dateCreated) {
        this.authorName = authorName;
        this.dateCreated = dateCreated;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
