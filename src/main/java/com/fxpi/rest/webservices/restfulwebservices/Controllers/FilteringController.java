package com.fxpi.rest.webservices.restfulwebservices.Controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/dynamic/filtering")
    public MappingJacksonValue dynamicFiltering() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        //dynamic way of filtering
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept( "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue jacksonMappingProvider = new MappingJacksonValue(someBean);
        jacksonMappingProvider.setFilters(filters);
        return jacksonMappingProvider;
    }
}
