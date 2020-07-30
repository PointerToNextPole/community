package com.ptr2nextpole.community.cache;

import com.ptr2nextpole.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();
        
        TagDTO frontEnd = new TagDTO();
        frontEnd.setCategoryName("前端");
        frontEnd.setTags(Arrays.asList("javascript", "前端", "vue.js", "css", "html",
                "html5", "node.js", "react.js", "jquery", "css3", "es6", "typescript",
                "chrome", "npm", "bootstrap", "sassless", "chrome-devtools", "angular",
                "firefox", "coffeescript", "safari", "postman", "postcss", "fiddler",
                "yarn", "webkit", "firebug", "edge"));
        tagDTOS.add(frontEnd);

        TagDTO backEnd = new TagDTO();
        backEnd.setCategoryName("后端");
        backEnd.setTags(Arrays.asList("php", "java", "node.js", "python", "c++", "c",
                "golang", "spring", "后端", "springboot", "django", "flask", "c#",
                "swoole", "ruby", "asp.net", "ruby-on-rails", "scala", "rust", "lavarel",
                "爬虫"));
        tagDTOS.add(backEnd);

        TagDTO mobileEnd = new TagDTO();
         mobileEnd.setCategoryName("移动端");
         mobileEnd.setTags(Arrays.asList("java", "android", "ios", "objective-c", "小程序",
                 "react-native", "swift", "xcode", "android-studio", "webapp", "flutter",
                 "kotlin"));
        tagDTOS.add(mobileEnd);

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "sql", "mongodb", "数据库", "json",
                "elasticsearch", "nosql", "memcached", "postgresql", "sqlit", "emariadb"));
        tagDTOS.add(db);

        TagDTO operateMaintain = new TagDTO();
        operateMaintain.setCategoryName("运维");
        operateMaintain.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "centos",
                "ubuntu", "服务器", "运维", "负载均衡", "ssh", "容器", "vagrant", "jenkins",
                "devops", "debian", "fabric"));
        tagDTOS.add(operateMaintain);

        TagDTO AI = new TagDTO();
        AI.setCategoryName("人工智能");
        AI.setTags(Arrays.asList("算法", "机器学习", "人工智能", "深度学习", "数据挖掘", "tensorflow",
                "神经网络", "人脸识别", "图像识别", "自然语言处理", "机器人", "pytorch", "自动驾驶"));
        tagDTOS.add(AI);

        TagDTO tool = new TagDTO();
        tool.setCategoryName("工具");
        tool.setTags(Arrays.asList("git", "github", "macos", "visual-studio-code", "windows",
                "vim", "sublime-text", "intellij-idea", "phpstorm", "eclipse", "webstorm",
                "编辑器", "svn", "visual-studio", "pycharm", "emacs"));
        tagDTOS.add(tool);

        TagDTO others = new TagDTO();
        others.setCategoryName("其他");
        others.setTags(Arrays.asList("程序员", "http", "https", "安全", "websocket", "restful",
                "xss", "区块链", "graphql", "csrf", "rpc", "比特币", "以太坊", "udp", "智能合约"));
        tagDTOS.add(others);
        
        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}