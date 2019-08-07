package com.ltts;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ltts.Constants;
import com.ltts.TelemetryDataPoint;
import com.microsoft.azure.iothub.DeviceClient;
import com.microsoft.azure.iothub.IotHubClientProtocol;

public class SmartMetersBase {

	private final static Logger logger = Logger.getLogger(SmartMetersBase.class);
	private static String connString = "HostName=myiothub66.azure-devices.net;DeviceId=smartmeter1;SharedAccessKey=66G7a/patDY5qlor+au8LWgh8vKLUq/Kh69cqNlYa1g=";
// static String connString = "HostName=smartmeteriothub.azure-devices.net;DeviceId=smartmeter4;SharedAccessKey=MTPKs4HJC16OBcjowaE359pkQ31tXPOPJfOJkOENZqw=";
	private static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;

	public static void main(String args[]) throws IOException, URISyntaxException {
		logger.trace("Application started");
		SmartMetersBase objApp = new SmartMetersBase();

		Map<String, String> objSimulatorMap = new ConcurrentHashMap<String, String>();
		// TODO
//		if(args.length > 0 && args[0] == "generate" ) {
		Path objResourcePath = Paths.get(Constants.CONSUMPTION_FOLDER);

		try (DeviceClient client = new DeviceClient(connString, protocol);) {
	//	try{
			client.open();
		//	MessageSender sender = new MessageSender(client);
			try (Stream<Path> objResourceList = Files.list(objResourcePath);) {
				objResourceList.forEach(objResourceName -> {
					String strFileName = objResourceName.getFileName().toString();
					try (Reader objReader = Files.newBufferedReader(
//							Paths.get(objApp.getFileFromResources(Constants.CONSUMPTION_FOLDER + "\\" + strFileName)));
							Paths.get(Constants.CONSUMPTION_FOLDER + "\\" + strFileName));							
							CSVParser objCSVParser = new CSVParser(objReader,
									CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
						String oldMacid = "";
						for (CSVRecord objCSVRecord : objCSVParser) {
							String strMacid = objCSVRecord.get(Constants.MACID);
							String strEnergy = objCSVRecord.get(Constants.ENERGY_CONSUMPTION);
							String strTimestamp = objCSVRecord.get(Constants.TIMESTAMPVAL);
							strEnergy = strEnergy.trim();
							strEnergy = (strEnergy == null || strEnergy.toLowerCase().equals("null")) ? "0" : strEnergy;							
							TelemetryDataPoint objTelemetryData = new TelemetryDataPoint(strMacid,
									new BigDecimal(strEnergy), strTimestamp);
						//	sender.sendData(objTelemetryData);

							objSimulatorMap.put(strMacid, strFileName);
						//	if(oldMacid.equalsIgnoreCase(strMacid)) {
						//		logger.debug("About to open and close the conn");
						//		client.close();
						//		client.open();
						//	}
						//	oldMacid = strMacid;
						}
					} catch (Exception e) {
						logger.error("Error occurred while sending message", e);
					}
				});
			}
		}

		Path path = Paths.get(objApp.getFileFromResources(Constants.MAP_FILE));
		try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) {
			JSONObject objJson = new JSONObject(objSimulatorMap);
			writer.write(objJson.toString());
		//	writer.write(objSimulatorMap);
		} catch (IOException ex) {
			logger.error("Error in writing to the file", ex);
		}

//		logger.debug("objSimulatorMap-tostring" + objSimulatorMap.size());
//		} else {
//			try (Reader objReader = Files.newBufferedReader(
//					Paths.get(objApp.getFileFromResources(Constants.DEVICEMAP_FILE)));
//					JsonReader objJsonReader = new JsonReader(objReader);					
//						) {
//				objJsonReader.beginObject();
//				while(objJsonReader.hasNext()) {					
//					System.out.println(objJsonReader.nextName() + ":" +objJsonReader.nextString());
//				}
//				objJsonReader.endObject();				
//			} catch (Exception e) {
//				logger.error("Error occurred while sending message", e);
//			}			
//		}

		logger.trace("Application completed");
	}

	private URI getFileFromResources(String fileName) throws URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			return resource.toURI();
		}
	}

}
