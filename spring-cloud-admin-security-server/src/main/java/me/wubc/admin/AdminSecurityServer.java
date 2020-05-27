package me.wubc.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author wbc
 * @date 2020/5/27
 * @desc
 */
@EnableDiscoveryClient
@EnableAdminServer
@SpringBootApplication
public class AdminSecurityServer {

}
