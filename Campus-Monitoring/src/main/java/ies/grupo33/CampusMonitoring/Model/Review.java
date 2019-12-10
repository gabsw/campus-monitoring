package ies.grupo33.CampusMonitoring.Model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "review", schema = "campus_monitoring")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username cannot be null or whitespace")
    private String username;
    @Column(name = "date_time", nullable = false)
    @NotNull(message = "DateTime cannot be null")
    @PastOrPresent(message = "DateTime must be in the past or correspond to present.")
    private LocalDateTime dateTime;
    @Column(name = "local_name", nullable = false)
    @NotNull(message = "Local name cannot be null")
    private String localName;
    @Column(name = "rating", nullable = false)
    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be greater than 5")
    private Integer rating;
    @Column(name = "content", nullable = false)
    @Size(min = 1, max = 1000, message
            = "Content must be between 1 and 1000 characters")
    @NotBlank(message = "Content cannot be null or whitespace")
    private String content;

    public Review() {
    }

    public Review(Long id, String username, LocalDateTime dateTime, String localName, int rating, String content) {
        this.id = id;
        this.username = username;
        this.dateTime = dateTime;
        this.localName = localName;
        this.rating = rating;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", dateTime=" + dateTime +
                ", localName='" + localName + '\'' +
                ", rating=" + rating +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return username.equals(review.username) &&
                dateTime.equals(review.dateTime) &&
                localName.equals(review.localName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, dateTime, localName);
    }
}
