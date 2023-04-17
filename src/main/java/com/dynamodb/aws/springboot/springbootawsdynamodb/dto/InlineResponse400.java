package com.dynamodb.aws.springboot.springbootawsdynamodb.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InlineResponse400 implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("message")
	private String message;

	public InlineResponse400 message(String message) {
		this.message = message;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InlineResponse400 inlineResponse400 = (InlineResponse400) o;
		return Objects.equals(this.message, inlineResponse400.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse400 {\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
