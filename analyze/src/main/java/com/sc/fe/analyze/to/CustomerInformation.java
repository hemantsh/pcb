package com.sc.fe.analyze.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Hemant
 */
@JsonInclude(Include.NON_EMPTY)
public class CustomerInformation implements Serializable {

	
	private static final long serialVersionUID = 1L;
    //TODO: remove this class
}
