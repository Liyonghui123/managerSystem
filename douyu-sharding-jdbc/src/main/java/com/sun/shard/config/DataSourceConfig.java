package com.sun.shard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @description:
 * @author: Dangerous
 * @time: 2020/3/20 17:47
 */

@Configuration
public class DataSourceConfig {
    @Autowired
    //private Filter statFilter;

    private static final String SHARDING_YML_PATH = "sharding/dataSource.yml";

    /**
     * 构建dataSource
     * 这里没有使用ShardingDataSourceFactory
     * 因为要为durid数据源配置监听Filter
     * @return
     * @throws SQLException
     * @throws IOException
     */
//    @Bean
//    public DataSource dataSource() throws SQLException, IOException {
//        YamlShardingConfiguration config = parse();
//        ShardingRule rule = config.getShardingRule(Collections.<String, DataSource>emptyMap());
//        rule.getDataSourceMap().forEach((k,v)->{
//            DruidDataSource d = (DruidDataSource) v;
//            d.setProxyFilters(Lists.newArrayList(statFilter));
//        });
//        return new ShardingDataSource(rule, config.getShardingRule().getConfigMap(), config.getShardingRule().getProps());
//    }
//
//    /**
//     * 解析yml
//     * @return
//     * @throws IOException
//     * @throws FileNotFoundException
//     * @throws UnsupportedEncodingException
//     */
//    private YamlShardingConfiguration parse() throws IOException, FileNotFoundException, UnsupportedEncodingException {
//        Resource certResource = new ClassPathResource(SHARDING_YML_PATH);
//        try (
//                InputStreamReader inputStreamReader = new InputStreamReader(certResource.getInputStream(), "UTF-8")
//        ) {
//            return new Yaml(new ConstructorYamlShardingConfiguration.class)).loadAs(inputStreamReader, YamlShardingConfiguration.class);
//        }
//    }

}
