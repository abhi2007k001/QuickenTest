package com.dlc.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class bodyContent {

	public static String getRequestBody(String fileName, HashMap<String, String> reqBody) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		String line;
		StringBuffer bodyContent = new StringBuffer();
		String bodyContentString = null;
		while ((line = br.readLine()) != null) {
			bodyContent.append(line.trim());

		}
		try {
			Iterator<Entry<String, String>> it = reqBody.entrySet().iterator();
			bodyContentString = bodyContent.toString();
			while (it.hasNext()) {
				// Retrieves the keys from the map
				Map.Entry<String, String> pairs = (Map.Entry<String, String>) it.next();
				try {
					String KeyString = (String) pairs.getKey();
					String ValueString = (String) pairs.getValue();
					// Replace the placeholders in the json content with values
					// from input Map
					bodyContentString = bodyContentString.replace("@" + KeyString + "@", ValueString);

				} catch (NullPointerException ex) {

				}

			}

		} catch (Exception e) {
		} finally {
			br.close();
		}

		return bodyContentString;

	}

}
