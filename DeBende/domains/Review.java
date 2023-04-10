package com.shopr.domains;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    @Pattern(regexp = "^(10|[0-9]{1,2})$") // use regexr.com to test regexp patterns!         , message = "Score should be between 0 and 10, maximum of two digits, no comma's or letters"
    private int score;

    @Column(nullable = false)
    private String message;
//
//    @ManyToOne
//    private User user;



}
