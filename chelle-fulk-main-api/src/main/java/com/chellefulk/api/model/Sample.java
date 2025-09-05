package com.chellefulk.api.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.Data;

@Embeddable
@Data
public class Sample {
	private String name;

	@Lob
	private byte[] audio;
}
