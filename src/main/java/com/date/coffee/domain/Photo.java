package com.date.coffee.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Photo {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Lob // 대용량 데이터 타입으로 지정 (이미지, 비디오 등등)
    private byte[] photoUrl;

    @CreationTimestamp
    private LocalDateTime localDateTime;
}
