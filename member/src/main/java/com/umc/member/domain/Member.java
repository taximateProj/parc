package com.umc.member.domain;


import com.umc.member.domain.common.BaseEntity;
import com.umc.member.domain.enums.Gender;
import com.umc.member.domain.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Id
    private String username;

    private String name;

    private String email;

    private String school;

    private String accountNumber;

    private Gender gender;

    private Role role;




}
