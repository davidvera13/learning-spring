package spring.learning.aop.domain;

import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Account {
    private String name;
    private String level;
}
