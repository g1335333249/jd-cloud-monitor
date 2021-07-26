package com.g1335333249.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/29 19:36
 * @Description:
 * @Modified By:
 */
@Configuration
public class RestTemplateConfig {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Bean("supportOctetStreamRestTemplate")
    public RestTemplate supportOctetStreamRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;

//        RestTemplate restTemplate = new RestTemplate(new HttpsClientRequestFactory());
//        FormHttpMessageConverter fc = new FormHttpMessageConverter();
//        StringHttpMessageConverter s = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        List<HttpMessageConverter<?>> partConverters = new ArrayList<>();
//        partConverters.add(s);
//        partConverters.add(new ResourceHttpMessageConverter());
//        fc.setPartConverters(partConverters);
//        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
//        restTemplate.getMessageConverters().addAll(Arrays.asList(fc, mappingJackson2HttpMessageConverter));
//        DefaultUriBuilderFactory uriFactory = new DefaultUriBuilderFactory();
//        uriFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
//        restTemplate.setUriTemplateHandler(uriFactory);
//        return restTemplate;
    }
}
