package com.ESEO_Server_TWIC.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ville {
	private String codeINSEECommune;
	private String nomCommune;
	private String codePostalCommune;
	private String latitudeCommune;
	private String longitudeCommune;
}
