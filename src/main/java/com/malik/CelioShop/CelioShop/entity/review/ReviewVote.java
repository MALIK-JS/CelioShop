package com.malik.CelioShop.CelioShop.entity.review;

import com.malik.CelioShop.CelioShop.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "ReviewVote")
public class ReviewVote {

    private static final int vote_up = 1;
    private static final int vote_down = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer votes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;


    public void vote_up(){
        this.votes =  vote_up;
    }
    public void vote_down(){
        this.votes = vote_down;
    }

    @Transient
    public boolean isUpvoted() {
        return this.votes == vote_up;
    }

    @Transient
    public boolean isDownvoted() {
        return this.votes == vote_down;
    }
}
