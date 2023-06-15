package com.batch.repository.packaged;

import com.batch.repository.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Package extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageSeq;
    private String packageName;
    private Integer count;
    private Integer period;

    @Builder
    public Package(String packageName, Integer count, Integer period) {
        this.packageName = packageName;
        this.count = count;
        this.period = period;
    }
}
