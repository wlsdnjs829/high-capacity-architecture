package com.example.high.capacity.architecture

import java.sql.SQLException
import javax.sql.DataSource
import org.h2.tools.Server
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class H2DBConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Throws(SQLException::class)
    fun dataSource(): DataSource {
        Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9093").start()
        return DataSourceBuilder.create().build()
    }

}