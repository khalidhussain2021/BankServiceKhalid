package com.hcl.khalid.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse {
	 private String message;
	    private String status;
	    private Integer statusCode;
	    private Object data;
}
