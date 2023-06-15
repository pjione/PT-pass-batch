package com.batch.repository.booking;

import com.batch.PassPtApplication;
import com.batch.repository.BaseTimeEntity;
import com.batch.repository.packaged.Package;
import com.batch.repository.pass.Pass;
import com.batch.repository.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Booking extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingSeq;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "passSeq")
    private Pass pass;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    private BookingStatus status;
    private boolean usedPass;
    private boolean attended;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime cancelledAt;






}
