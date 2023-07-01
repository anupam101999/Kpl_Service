package com.kpl.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveDataVO {
	private String teamName;
	private Long overSeasplayer;
	private Long localplayer;
	private Long moneyspend;
	private Long moneyRem;
}