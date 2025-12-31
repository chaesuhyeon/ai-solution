package sweetk.solution.test.user.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import sweetk.solution.test.global.model.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
@Builder
@Entity
@Getter
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;
}
