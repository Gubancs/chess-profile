/*
 * Copyright 2016 TopClouders Hungary Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.topclouders.chessprofile.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by kokeny on 07/02/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.topclouders.chessprofile.controller"})
public class DispatcherConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.timeZone(TimeZone.getDefault());
        builder.simpleDateFormat("yyyy-MM-dd");
        builder.indentOutput(true).dateFormat(new TimeInMillisDateFormat());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    public static class TimeInMillisDateFormat extends SimpleDateFormat {

        public TimeInMillisDateFormat() {
        }

        @Override
        public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
            if (date != null) {
                toAppendTo.append(date.getTime());
            }
            return toAppendTo;
        }

        @Override
        public Date parse(String source, ParsePosition pos) {
            if (source != null) {
                try {
                    long parseLong = Long.parseLong(source);
                    return new Date(parseLong);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }

    }
}
