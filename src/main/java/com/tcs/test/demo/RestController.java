package com.tcs.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.GET;
import java.awt.print.Book;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    //@Autowired
    private RestTemplate restTemplate;

    private String targetUrl ="https://api.github.com/users/";

    @GetMapping("/{user}")
    @ResponseBody
    public Root[] getBook(@PathVariable String user) {
        return findByRepoId(user);
    }

    private Root[]  findByRepoId(String user) {
        restTemplate = new RestTemplate();

        String finalTargetUrl = targetUrl+"/"+user+"/repos";//sivajisaha/repos
        ResponseEntity<Root[]> response = restTemplate.getForEntity(finalTargetUrl, Root[].class);
        System.out.println(response);
        Root[] userArray = response.getBody();
        Arrays.sort(userArray, Comparator.comparing(a -> a.id));

        // To do Need to parse the json itnp list of objects and sort and retrun json response.
        return userArray;
    }
}