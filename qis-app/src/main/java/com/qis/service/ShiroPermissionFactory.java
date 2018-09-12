package com.qis.service;

import com.xinri.service.ResourceService;
import com.xinri.vo.redis.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    /**
     * 标题、简要说明. <br>
     * 类详细说明.
     * <p>
     * Copyright: Copyright (c) 2017-6-3 下午9:51:02
     * <p>
     *
     * @author agnils@foxmail.com
     * @version 1.0.0
     */
    public class ShiroPermissionFactory extends ShiroFilterFactoryBean {

        public static final String ROLES_STRING = "roles[\"{0}\"]";
        public static final String PREMISSION_STRING = "perms[\"{0}\"]";

        // @javax.annotation.Resource
        // private PermissionService permissionService;

        @javax.annotation.Resource
        private ResourceService resourceService;

        /** 记录配置中的过滤链 */
        public static String filterChainDefinitions = "";//这个要和配置文件中的名称要一样

        /**
         * 初始化设置过滤链
         */
        @Override
        public void setFilterChainDefinitions(String definitions) {
            filterChainDefinitions = definitions;// 记录配置的静态过滤链
            Map<String, String> otherChains = new HashMap<String, String>();
            List<Resource> list = (List) resourceService.getResource();
            for (Resource resource : list) {
                if (StringUtils.isNotBlank(resource.getUrl()) && StringUtils.isNotBlank(resource.getCode())) {
                    otherChains.put(resource.getUrl(), MessageFormat.format(PREMISSION_STRING, resource.getCode()));
                }
            }
            // 加载配置默认的过滤链
            Ini ini = new Ini();
            ini.load(filterChainDefinitions);
            Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);

            if (CollectionUtils.isEmpty(section)) {
                section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
            }
            // 加上数据库中过滤链
            section.putAll(otherChains);
            section.put("/**","user");
            setFilterChainDefinitionMap(section);

        }
    }
