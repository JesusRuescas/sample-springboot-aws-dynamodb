package com.dynamodb.aws.springboot.springbootawsdynamodb.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MetricsPostRequestBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("metrics-id")
	private String metricsId;

	@JsonProperty("command")
	private String command;

	@JsonProperty("license-id")
	private String licenseId;

	public MetricsPostRequestBody metricsId(String metricsId) {
		this.metricsId = metricsId;
		return this;
	}

	public String getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(String metricsId) {
		this.metricsId = metricsId;
	}

	public MetricsPostRequestBody command(String command) {
		this.command = command;
		return this;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public MetricsPostRequestBody licenseId(String licenseId) {
		this.licenseId = licenseId;
		return this;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MetricsPostRequestBody metricsPostRequestBody = (MetricsPostRequestBody) o;
		return Objects.equals(this.metricsId, metricsPostRequestBody.metricsId)
				&& Objects.equals(this.command, metricsPostRequestBody.command)
				&& Objects.equals(this.licenseId, metricsPostRequestBody.licenseId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(metricsId, command, licenseId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class MetricsPostRequestBody {\n");
		sb.append("    metricsId: ").append(toIndentedString(metricsId)).append("\n");
		sb.append("    command: ").append(toIndentedString(command)).append("\n");
		sb.append("    licenseId: ").append(toIndentedString(licenseId)).append("\n");
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
