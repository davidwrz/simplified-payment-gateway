package io.starfish.simplifiedpaymentgateway.modules.user.exists.domain;

import jakarta.persistence.*;

@Entity(name = "existsUser")
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public String getName() {
        return name;
    }
}
