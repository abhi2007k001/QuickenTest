package com.dlc.utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dlc.core.ReadConfig;
import com.dlc.core.bodyContent;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class apiSpecs {

	public static RequestSpecification buildReqSpecs(Map<String, String> headers, String bodyFile,
			Map<String, String> bodyvalues) throws IOException {

		RequestSpecBuilder req = new RequestSpecBuilder();

		req.addHeaders(headers);
		System.out.println(headers);

		String sBody = null;
		HashMap<String, String> reqBody = new HashMap<String, String>();
		reqBody.putAll(bodyvalues);

		String xmlPath = System.getProperty("user.dir") + "/src/main/resources/" + bodyFile;
		sBody = bodyContent.getRequestBody(xmlPath, reqBody);

		System.out.println(sBody);

		req.setBody(sBody);
		bodyvalues.clear();

		return req.build();

	}

	public static RequestSpecification GetReq(Map<String, String> headers) throws IOException {

		RequestSpecBuilder req = new RequestSpecBuilder();

		req.addHeaders(ReadConfig.getHeaders());

		return req.build();

	}

}
