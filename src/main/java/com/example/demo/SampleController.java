package com.example.demo;

import com.example.demo.repository.SampleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    public SampleController(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    /**
     * dynamoDB repository
     */
    SampleRepository sampleRepository;

    /**
     * get value found by key.
     * @param key dynamoDB partition key (hash key)
     * @return value value
     */
    @GetMapping("/dynamo/{key}")
    @ResponseBody
    public String getValue(@PathVariable("key") String key) {
        return sampleRepository.findById(key).orElse(new Entity("failKey", "failValue")).getValue();
    }
}
