package site.Ulbame.BE.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TB_USER")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(unique = true, nullable = false)
    private String userId;
    @NotNull
    private String userPw;
    @Column(unique = true, nullable = false)
    private String userName;
}