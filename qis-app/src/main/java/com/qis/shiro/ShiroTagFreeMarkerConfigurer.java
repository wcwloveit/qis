package com.qis.shiro;

import com.qis.shiro.tag.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @author
 */
public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer{
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException{
        super.afterPropertiesSet();
        this.getConfiguraton().setSharedVariable("shiro", new ShiroTags());
    }i
}
