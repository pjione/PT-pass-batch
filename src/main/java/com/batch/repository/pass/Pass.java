package com.batch.repository.pass;

import com.batch.repository.BaseTimeEntity;
import com.batch.repository.packaged.Package;
import com.batch.repository.user.User;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Pass extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passSeq; // id
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user; //유저 참조
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "packageSeq")
    private Package aPackage; //상품 참조

    @Enumerated(EnumType.STRING)
    private PassStatus status; //상태
    private Integer remainingCount; // 남은 이용권 수 null이면 무제한

    private LocalDateTime startedAt;//시작날짜
    private LocalDateTime endedAt; //종료날짜
    private LocalDateTime expiredAt; //만료날짜 null이면 무제한

    @Builder
    public Pass(User user, Package aPackage, PassStatus status, Integer remainingCount, LocalDateTime startedAt, LocalDateTime endedAt, LocalDateTime expiredAt) {
        this.user = user;
        this.aPackage = aPackage;
        this.status = status;
        this.remainingCount = remainingCount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.expiredAt = expiredAt;
    }
}
