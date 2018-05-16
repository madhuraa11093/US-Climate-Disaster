package com.javainuse.swaggertest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
public class DisasterSmryController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/disasters/findAll")
	public String getDisasterSummaries(@RequestParam (required=false) String disasterType,@RequestParam(required=false) String incidentBeginDate,@RequestParam(required=true) String declarationDate) throws IOException {

        String csvFile = "src//main//resources//DisasterDeclarationsSummaries.csv";
        Object file=Files.readAllLines(Paths.get(csvFile)).stream()
        .map(s -> new DisasterSummaries(s.split(",")[0], s.split(",")[1], s.split(",")[2], s.split(",")[3],
                     s.split(",")[4], s.split(",")[5], s.split(",")[6], s.split(",")[7], s.split(",")[8],
                     s.split(",")[9], s.split(",")[10], s.split(",")[10], s.split(",")[12], s.split(",")[13],
                     s.split(",")[14], s.split(",")[15], s.split(",")[16], s.split(",")[17]))
                     .collect(Collectors.toList());

        JSONArray objects = new JSONArray();
        String data=null;
        Gson gson=new Gson();
        objects.add(file);
        data=gson.toJson(objects);
        System.out.println("check "+data.length());
        return data;
 }
}
