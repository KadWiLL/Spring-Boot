package com.propfile.profile.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;


    private int likes = 0;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", user=" + user +
                ", cDate=" + cDate +
                ", uDate=" + uDate +
                '}';
    }

    private int dislikes = 0;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("posts")
    private User user;

    @CreationTimestamp
    private Date cDate;

    @UpdateTimestamp
    private Date uDate;
}
