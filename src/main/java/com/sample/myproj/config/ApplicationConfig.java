package com.sample.myproj.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="app-config")
public class ApplicationConfig {

	private String imageUploadConfigPath;
	private String virtualImageConfigPath;
	private String imageUploadPathPrefix;
	private String virtualImageURLPrefix;

	public String getImageUploadConfigPath() {
		return imageUploadConfigPath;
	}

	public void setImageUploadConfigPath(String imageUploadConfigPath) {
		this.imageUploadConfigPath = imageUploadConfigPath;
	}

	public String getVirtualImageConfigPath() {
		return virtualImageConfigPath;
	}

	public void setVirtualImageConfigPath(String virtualImageConfigPath) {
		this.virtualImageConfigPath = virtualImageConfigPath;
	}

	public String getImageUploadPathPrefix() {
		return imageUploadPathPrefix;
	}

	public void setImageUploadPathPrefix(String imageUploadPathPrefix) {
		this.imageUploadPathPrefix = imageUploadPathPrefix;
	}

	public String getVirtualImageURLPrefix() {
		return virtualImageURLPrefix;
	}

	public void setVirtualImageURLPrefix(String virtualImageURLPrefix) {
		this.virtualImageURLPrefix = virtualImageURLPrefix;
	}

}
