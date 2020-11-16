package me.catring.demo.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import me.catring.demo.domain.JacksonView;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "FoodComment")
public class FoodComment {
    private int foodCommentId;

    @JsonView(JacksonView.CommentView.class)
    private String title;

    @JsonView(JacksonView.CommentView.class)
    private String content;

    @JsonView(JacksonView.CommentView.class)
    private Timestamp commentTime;

    @JsonView(JacksonView.CommentView.class)
    private Food foodByFoodId;

    @JsonView(JacksonView.CommentView.class)
    private User userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "food_comment_id", nullable = false)
    public int getFoodCommentId() {
        return foodCommentId;
    }
    public void setFoodCommentId(int foodCommentId) {
        this.foodCommentId = foodCommentId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "comment_time", nullable = false)
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @ManyToOne
    @JoinColumn(name = "food_id", referencedColumnName = "food_id", nullable = false)
    public Food getFoodByFoodId() {
        return foodByFoodId;
    }
    public void setFoodByFoodId(Food foodByfoodId) {
        this.foodByFoodId = foodByfoodId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodComment that = (FoodComment) o;

        if (foodCommentId != that.foodCommentId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = foodCommentId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "food Comment: " + getFoodCommentId() + ", " + getFoodByFoodId().getFoodTitle() + ", " + getUserByUserId().getUsername();
    }


}
