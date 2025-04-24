package ca.ontariohealth.pcis.utils.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.ontariohealth.pcis.utils.tool.JsonToolkit;

@RestController
@RequestMapping("/token")
public class TokenController {

    //GET request to controller root path /token
    @GetMapping
    public String onDefaultToken() {
    	
    	Map<String, String> pathsMap = new LinkedHashMap<String, String>();
    	pathsMap.put("/token HTTP GET", "get context");
    	pathsMap.put("/token/sign HTTP POST", "sign a token with provided information");    	
    	pathsMap.put("/token/update HTTP POST", "re-sign a token with provided information");
        return JsonToolkit.toJsonContent(pathsMap);
    }	
	
}
