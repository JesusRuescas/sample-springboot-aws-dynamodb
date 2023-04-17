package com.dynamodb.aws.springboot.springbootawsdynamodb.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InlineResponse201 implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("request-id")
	private String requestId;

	public InlineResponse201 requestId(String requestId) {
		this.requestId = requestId;
		return this;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InlineResponse201 inlineResponse201 = (InlineResponse201) o;
		return Objects.equals(this.requestId, inlineResponse201.requestId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(requestId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse201 {\n");
		sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
