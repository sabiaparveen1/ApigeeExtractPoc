package com.example.apigeeExtract;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Service {
    public ArrayList<String> basepaths;
    public ConfigurationVersion configurationVersion;
    public String contextInfo;
    public long createdAt;
    public String createdBy;
    public String description;
    public String displayName;
    public EntityMetaDataAsProperties entityMetaDataAsProperties;
    public long lastModifiedAt;
    public String lastModifiedBy;
    public String manifestVersion;
    public String name;
    public ArrayList<String> policies;
    public ArrayList<String> proxies;
    public ArrayList<String> proxyEndpoints;
    public ResourceFiles resourceFiles;
    public ArrayList<String> resources;
    public String revision;
    public ArrayList<Object> sharedFlows;
    public String spec;
    public ArrayList<String> targetEndpoints;
    public ArrayList<Object> targetServers;
    public ArrayList<String> targets;
    public String type;
    public String hasExtensiblePolicy;

    public class ConfigurationVersion {
        public int majorVersion;
        public int minorVersion;
    }

    public class EntityMetaDataAsProperties {
        public String bundle_type;
        public String lastModifiedBy;
        public String createdBy;
        public String lastModifiedAt;
        public String subType;
        public String createdAt;
    }

    public class ResourceFile {
        public String name;
        public String type;
    }

    public class ResourceFiles {
        public ArrayList<ResourceFile> resourceFile;
    }
}
