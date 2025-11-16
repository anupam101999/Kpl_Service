package com.kpl.registration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiveDataVO {
	@JsonProperty("Team Name")
	private String teamName;
	@JsonProperty("Money Spend")
	private Long moneyspend;
	@JsonProperty("Money Rem")
	private Long moneyRem;
	@JsonProperty("Max Bet")
	private Long maxiumBetAmountOnSinglePlayer;
	@JsonProperty("Overseas Player")
	private Long overSeasplayer;
	@JsonProperty("Local Player")
	private Long localplayer;
}