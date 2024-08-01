package com.umc.member.domain;


import com.umc.member.domain.common.BaseEntity;
import com.umc.member.domain.enums.Gender;
import com.umc.member.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 내가 사용하는 db에게 맡김
    private Long id;

    private String name;

    private String email;

    private String school;

    private String accountNumber;

    private Gender gender;

    private Role role;




}
