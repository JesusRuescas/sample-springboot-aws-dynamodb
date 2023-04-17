
package com.dynamodb.aws.springboot.springbootawsdynamodb.api;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dynamodb.aws.springboot.springbootawsdynamodb.dto.InlineResponse201;
import com.dynamodb.aws.springboot.springbootawsdynamodb.dto.MetricsPostRequestBody;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Controller
public interface MetricsApi {

	@RequestMapping(method = RequestMethod.POST, value = "/metrics/api", produces = { "application/json" }, consumes = {
			"application/json" })
	ResponseEntity<InlineResponse201> metricsApiPost(
			@Valid @RequestBody(required = false) MetricsPostRequestBody metricsPostRequestBody);
}
