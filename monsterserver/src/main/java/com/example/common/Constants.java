package com.example.common;

/**
 * 常用常量类
 * @author zhu
 * @version 1.0
 * @date 2019/12/15 下午9:07
 */
public class Constants {
    /**
     * 限制最多投诉内容长度大小
     */
    public static final Integer COMPLAINT_CONTENT_MAX_LENGTH = 50;
    /**
     * 限制最少投诉内容长度大小
     */
    public static final Integer COMPLAINT_CONTENT_MIN_LENGTH = 10;
    /**
     * 限制最多回复内容长度大小
     */
    public static final Integer COMPLAINT_REPLY_CONTENT_MAX_LENGTH = 50;
    /**
     * 限制最少回复内容长度大小
     */
    public static final Integer COMPLAINT_REPLY_CONTENT_MIN_LENGTH = 10;
    /**
     * 昵称的最小长度
     */
    public static final Integer NICKNAME_MIN_LENGTH = 2;
    /**
     * 昵称的最大长度
     */
    public static final Integer NICKNAME_MAX_LENGTH = 12;
    /**
     * 匹配昵称的正则表达式
     */
    public static final String NICKNAME_REGEX = "^[^ \\n\\t]+$";
    /**
     * 匹配邮箱的正则表达式
     */
    public static final String EMAIL_REGEX = "\\w+@[a-zA-Z0-9]+(.[a-zA-Z]{2,4}){1,2}";

    /**
     * 匹配手机的正则表达式
     */
    public static final String MOBILE_REGEX = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 匹配密码打正则表达式，8～16位，必须同时包含至少一个字母，一个数字，一个特殊字符
     * 特殊字符有：@$!%*#?&_.
     */
    public static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&_.])[A-Za-z\\d$@$!%*#?&_.]{8,16}$";

    /**
     * 邮箱验证码过期时间
     */
    public static final long CODE_EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 邮箱验证回调地址或IP
     */
    public static final String EMAIL_ROLLBACK_ADDRESS = "localhost:8080/";

    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * !!!!注意！！！远端FTP服务器的路径前后不要带 " / " !!!
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * <p>
     * FTP远程服务器头像存放路径
     */
    public static final String REMOTE_AVATAR_PATH = "image/avatar";

    /**
     * 项目本地存储静态图片的目录
     */
    public static final String PROJECT_STATIC_AVATAR_DIR = "static/images/avatar";

    /**
     * 项目本地存储office资源的目录
     */
    public static final String PROJECT_STATIC_OFFICE_DIR = "static/office";

    /**
     * ID匹配正则表达式
     */
    public static final String ID_REGEX = "^[0-9]{1,9}$";

    /**
     * 排除空格正则表达式
     */
    public static final String SPACE_REGEX = "^[^\\s]+";

    /**
     * 未知字段
     */
    public static final String UNKNOWN = "unknown";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * FTP远程服务器题目素材资源存放路径
     */
    public static final String REMOTE_REFERENCE_RESOURCE_PATH = "office/bank/resource/reference";

    /**
     * FTP远程服务器题目答案资源存放路径
     */
    public static final String REMOTE_SOLUTION_RESOURCE_PATH = "office/bank/resource/solution";
}
