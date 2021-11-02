package com.catapi.response.wrapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.catapi.dto.TestQuestionResponsesDTO;

import lombok.Builder;
import lombok.Data;

/**
 * @author rohit.godara
 *
 */

@Builder
@Data
public class TestResponseWrapper implements Serializable {

	private static final long serialVersionUID = 1L;


	private UUID id;

	private String code;

	private UUID userId;

	private Integer status;

	private UUID assessmentId;

	private Float initialAbility;

	private Float finalAbility;

	private LocalDate createdOn;

	private LocalDate modifiedOn;
	private List<TestQuestionResponsesDTO	> questions;

}
