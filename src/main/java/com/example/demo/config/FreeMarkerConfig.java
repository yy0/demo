package com.example.demo.config;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void freeMarkerConfigurer() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("decimalRound", decimalRoundMethod());
        freeMarkerConfigurer.setFreemarkerVariables(variables);
        Properties settings = new Properties();
        settings.put("template_update_delay", 0);
        settings.put("default_encoding", "UTF-8");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
    }

    @Bean
    public DecimalRoundMethod decimalRoundMethod(){
        return new DecimalRoundMethod();
    }

    public class DecimalRoundMethod implements TemplateMethodModelEx {

        @Override
        public Object exec(List args) throws TemplateModelException {
            if (CollectionUtils.isEmpty(args)) {
                throw new TemplateModelException("方法参数个数不正确，必需为2个");
            }
            if (null == args.get(0)) {
                return args.get(0);
            }
            int count = Integer.parseInt(args.get(1).toString());
            return new BigDecimal(args.get(0).toString()).setScale(count, BigDecimal.ROUND_HALF_UP);
        }
    }

}