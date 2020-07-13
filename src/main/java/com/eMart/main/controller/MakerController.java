package com.eMart.main.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MakerController {

    @PostMapping("/csvupload/{file}")
    public List<String> csvupload(@PathVariable MultipartFile file)
    {
        List<String> ls=new ArrayList<>();

        return ls;
    }
}
