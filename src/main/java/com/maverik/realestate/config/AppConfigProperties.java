/**
 * 
 */
package com.maverik.realestate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.maverik.maverikannotations.sonar.SonarClassExclusion;

/**
 * @author jorge
 *
 */

@SonarClassExclusion
@Configuration
@PropertySource("classpath:../properties/maverik.properties")
public class AppConfigProperties {

}
