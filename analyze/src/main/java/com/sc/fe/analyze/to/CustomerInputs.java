package com.sc.fe.analyze.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;

/**
 *
 * @author Hemant
 */
@ApiModel(value="CustomerInputs",description="Table Structure of CustomerInputs")
@JsonInclude(Include.NON_EMPTY)
public class CustomerInputs implements Serializable {
	//TODO: delete
}
