package whz.pti.pizza.demo.security.domain;

import lombok.*;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.security.domain.Role;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends BaseEntity<Long> {

    private String loginName;
    private String passwordHash;
    private Role role = Role.USER;
}
