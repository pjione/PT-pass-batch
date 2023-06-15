package com.batch.repository.user;

import com.batch.repository.BaseTimeEntity;
import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class User extends BaseTimeEntity implements Persistable<String> {
    @Id
    @Column(name = "user_id")
    private String id;
    private String userName;
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;
    private String phone;
    private String meta;

    @Builder
    public User(String id, String userName, UserStatus status, String phone, String meta) {
        this.id = id;
        this.userName = userName;
        this.status = status;
        this.phone = phone;
        this.meta = meta;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
