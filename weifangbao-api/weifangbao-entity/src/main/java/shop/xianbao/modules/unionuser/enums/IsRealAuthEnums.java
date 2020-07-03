package shop.xianbao.modules.unionuser.enums;

/**
 * @program: StoreCheckStatus
 * @description: 店铺审核状态枚举
 * @author: yh
 * @create: 2019-03-01 10:18
 **/
public enum IsRealAuthEnums {

    UN_REAL_AUTH("未认证", 0),
    REAL_AUTH("已认证", 1),
  ;

    private String name;

    private Integer key;

    IsRealAuthEnums(String name, Integer key) {
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


