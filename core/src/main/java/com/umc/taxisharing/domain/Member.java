package com.umc.taxisharing.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.umc.taxisharing.domain.common.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	private String name;

	private String nickName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String university;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "member_id")
	private List<BankAccount> bankAccounts = new ArrayList<>();
}

enum Gender {
	MALE,
	FEMALE
}