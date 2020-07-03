package shop.xianbao.modules.fastNavigation.enums;

/**
 * @program: NavigationLayoutTypeEnum
 * @description: 申请类型默认 0个人  1企业
 * @author: yh
 * @create: 2019-03-01 13:46
 **/
public enum NavigationLayoutTypeEnum {

    TYPE_0("无", 0),
    TYPE_1("1x4", 1),
    TYPE_2("3x4", 2),
    TYPE_3("2x5", 3),
    TYPE_4("2x5", 4),
    ;

    private String name;

    private Integer key;

    NavigationLayoutTypeEnum(String name, Integer key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }}
